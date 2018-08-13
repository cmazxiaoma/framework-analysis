package com.cmazxiaoma.spring.beanpostprocessor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 13:42
 */
public class MyVersionBeanProxyFactory {

    public static Object createProxy(Class targetClass, Map<String, Object> beans) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(targetClass);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(targetClass, beans));
        return proxyFactory.getProxy();
    }

    private static class VersionRoutingMethodInterceptor implements MethodInterceptor {

        private String classVersion;

        private Object versionOneBean;

        private Object versionTwoBean;

        public VersionRoutingMethodInterceptor(Class targetClass, Map<String, Object> beans) {
            String interfaceName = targetClass.getSimpleName();

            if (targetClass.isAnnotationPresent(MyVersion.class)) {
                classVersion = ((MyVersion) targetClass.getAnnotation(MyVersion.class)).value();
            }
            this.versionOneBean = beans.get(this.buildBeanName(interfaceName, "V1"));
            this.versionTwoBean = beans.get(this.buildBeanName(interfaceName, "V2"));
        }

        private String buildBeanName(String interfaceName, String version) {
            return StringUtils.uncapitalize(new StringBuffer()
                    .append(interfaceName.substring(1, interfaceName.length()))
                    .append("Impl")
                    .append(version)
                    .toString());
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method method = invocation.getMethod();
            String version = null;

            // 以注解在类上的MyVersion为主，以注解在method上的MyVersion为辅,
            // 都没有MyVersion,则默认为V1
            if (!StringUtils.isEmpty(classVersion)) {
                version = classVersion;
            } else {
                if (method.isAnnotationPresent(MyVersion.class)) {
                    version = method.getAnnotation(MyVersion.class).value();
                }
            }

            if (StringUtils.isEmpty(version)) {
                version = "V1";
            }
            return invocation.getMethod().invoke(getTargetBeanByVersion(version), invocation.getArguments());
        }

        public Object getTargetBeanByVersion(String version) {
            boolean isVersionOneFlag;

            if ("V1".equalsIgnoreCase(version)) {
                isVersionOneFlag = true;
            } else if ("V2".equalsIgnoreCase(version)) {
                isVersionOneFlag = false;
            } else {
                isVersionOneFlag = false;
            }

            return isVersionOneFlag ? versionOneBean : versionTwoBean;
        }
    }
}
