package com.cmazxiaoma.spring;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/10 14:47
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationContext.applicationContext = applicationContext;
    }

    public static <T> T getBeanByType(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

}
