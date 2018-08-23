package com.cmazxiaoma.spring;

import com.cmazxiaoma.spring.bean.SpringTest2;
import com.cmazxiaoma.spring.bean.SpringTest3;
import com.cmazxiaoma.spring.bean.SpringTestBean;
import com.cmazxiaoma.spring.compoent.SpringTest4;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import javax.swing.*;
import java.io.IOException;
import java.util.Set;

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

        // 使用默认的filter,只能扫描到被@Compoent,@Service,@Repository,@Controller注解的类
        boolean usedDefaultFilter = false;
        String basePackPage = "com.cmazxiaoma.spring.bean";

        ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider
                = new ClassPathScanningCandidateComponentProvider(usedDefaultFilter);

        // 确定此过滤器是否与给定元数据描述的类匹配
        TypeFilter typeFilter = new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                // 返回具体类，既不是接口，也不是抽象类
                return metadataReader.getClassMetadata().isConcrete();
            }
        };

        classPathScanningCandidateComponentProvider.addIncludeFilter(typeFilter);

        Set<BeanDefinition> beanDefinitionSet = classPathScanningCandidateComponentProvider
                .findCandidateComponents(basePackPage);

        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            // beanName一般由BeanNameGenerator,
            // Spring自带的AnnotationBeanNameGenerator,DefaultBeanNameGenerator,
            // RepositoryBeanNameGenerator实现这个接口
            String beanName = beanDefinition.getBeanClassName();

            // 返回这个bean的属性
            MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();

            if (beanName.equalsIgnoreCase(SpringTest2.class.getCanonicalName())) {
                mutablePropertyValues.addPropertyValue("name", "github");
            }
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.configurableListableBeanFactory = beanFactory;

//        BeanDefinition springTest3BeanDefinition = beanFactory.getBeanDefinition(SpringTest3.class.getCanonicalName());
        //SpringTest3 springTest3 = (SpringTest3) beanFactory.getBean(SpringTest3.class.getCanonicalName());
        //SpringTest4 springTest4 = (SpringTest4) beanFactory.getBean(SpringTest4.class);
    }
}
