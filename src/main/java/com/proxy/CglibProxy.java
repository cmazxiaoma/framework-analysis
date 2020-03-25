package com.proxy;

import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/29 17:35
 */
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(obj.getClass().getSuperclass().getSimpleName());
        System.out.println(MessageFormat.format("代理子类:{0},method:{1},args:{2},proxy:{3}", obj.getClass().getSimpleName()

                , method.getDeclaringClass().getSimpleName(), args.toString(), proxy.getSuperName()));
        return proxy.invokeSuper(obj, args);
    }

}
