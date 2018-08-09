package com.cmazxiaoma.spring;

import com.cmazxiaoma.spring.bean.SpringTest3;
import com.cmazxiaoma.spring.bean.SpringTestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 15:10
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    private BeanDefinitionRegistry beanDefinitionRegistry;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.beanDefinitionRegistry = registry;
//        RootBeanDefinition springTestBean = new RootBeanDefinition(SpringTestBean.class);
//        registry.registerBeanDefinition("springTestBean", springTestBean);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.configurableListableBeanFactory = beanFactory;
//        Object spirngTest1 = beanFactory.getBean("springTest1");
//        Object springTest2 = beanFactory.getBean("springTest2");
//        Object springTest3 = beanFactory.getBean("springTest3");
    }
}
