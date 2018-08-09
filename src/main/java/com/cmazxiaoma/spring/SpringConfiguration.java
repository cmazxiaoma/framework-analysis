package com.cmazxiaoma.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 16:59
 */
@Configuration
public class SpringConfiguration {

    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }

}
