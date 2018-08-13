package com.cmazxiaoma.spring.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 13:28
 */
@Component
public class MyVersionBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MyInjected.class)) {
                if (!field.getType().isInterface()) {
                    throw new BeanCreationException("MyInjected field must be" +
                            "declared as an interface:" + field.getName()
                            + "@Class:" + clazz.getCanonicalName());
                }

                try {
                    this.handleRoutingInjected(field, bean, field.getType());
                } catch (IllegalAccessException e) {
                    throw new BeanCreationException("Exception thrown when handleRoutingInjected");
                }
            }
        }
        return bean;
    }

    private void handleRoutingInjected(Field field, Object bean, Class clazz) throws IllegalAccessException {
        Map<String, Object> candidateBeans = this.applicationContext.getBeansOfType(clazz);
        field.setAccessible(true);

        if (candidateBeans.size() == 1) {
            field.set(bean, candidateBeans.values().iterator().next());
        } else if (candidateBeans.size() == 2) {
            Object proxy = MyVersionBeanProxyFactory.createProxy(clazz, candidateBeans);
            field.set(bean, proxy);
        } else {
            throw new IllegalArgumentException("find more than two bean for type:" + clazz);
        }
    }
}
