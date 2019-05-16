package com.cmazxiaoma.spring.inject;

import com.cmazxiaoma.spring.bean.SpringTest3;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_NAME;
import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_TYPE;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/27 10:21
 */
@Component
public class AutowiredModeBeanDefinitionRegistryPostProcessor
        implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //添加这一句是生成代理类的class文件，前提是你需要在工程根目录下创建com/sun/proxy目录
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        String[] beanDefinitionNames = registry.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);

            if (beanDefinition instanceof AbstractBeanDefinition) {
                AbstractBeanDefinition hibernateDaoSupportBeanDefinition = (AbstractBeanDefinition)
                        beanDefinition;

                if (beanDefinitionName.contains("Dao")) {
                    if (hibernateDaoSupportBeanDefinition.getAutowireMode()
                            == AbstractBeanDefinition.AUTOWIRE_NO) {
                        hibernateDaoSupportBeanDefinition.setAutowireMode(AUTOWIRE_BY_NAME);
                    }
                }
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        RootBeanDefinition bmd = (RootBeanDefinition) beanFactory.getMergedBeanDefinition("myBaseDao");
//
//        if (bmd != null) {
//            if (bmd.getAutowireMode() == AbstractBeanDefinition.AUTOWIRE_NO) {
//                bmd.setAutowireMode(AUTOWIRE_BY_NAME);
//            }
//        }
    }
}
