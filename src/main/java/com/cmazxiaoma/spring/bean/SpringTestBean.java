package com.cmazxiaoma.spring.bean;

import com.cmazxiaoma.spring.lazy.Hero;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 20:01
 */
public class SpringTestBean implements BeanNameAware, BeanFactoryAware {

    private BeanFactory beanFactory;

    private String beanName;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
//        System.out.println("beanFactory=" + this.beanFactory);

//        Object object = beanFactory.getBean(SpringTest3.class);
//        System.out.println("SpringTest3=" + object);

//        Object hero = beanFactory.getBean(Hero.class);
//        System.out.println("hero=" + hero);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
//        System.out.println("beanName=" + this.beanName);
    }

    @Override
    public String toString() {
        return "SpringTestBean{" +
                "beanName='" + beanName + '\'' +
                '}';
    }
}
