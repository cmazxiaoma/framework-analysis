package com.cmazxiaoma.spring.inject;

import com.cmazxiaoma.InitSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/26 11:55
 */
public class InjectTest extends InitSpringTest {

//    @Autowired
//    private MyBaseDao myBaseDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test1() {
        MyBaseDao myBaseDao = applicationContext.getBean(MyBaseDao.class);
        System.out.println(myBaseDao.getTemplate());
    }

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Test
    public void test2() {
        MyBaseDao myBaseDao = applicationContext.getBean(MyBaseDao.class);

        if (defaultListableBeanFactory.containsSingleton("mySessionFactory")
                || defaultListableBeanFactory.containsBeanDefinition("mySessionFactory")) {
            Boolean flag1 = BeanFactoryUtils.isFactoryDereference("mySessionFactory") ;
            Boolean flag2 = defaultListableBeanFactory.isFactoryBean("mySessionFactory");
            Boolean flag3 = (!BeanFactoryUtils.isFactoryDereference("mySessionFactory")
                    || defaultListableBeanFactory.isFactoryBean("mySessionFactory"));

            System.out.println("=====>flag1=" + flag1);
            System.out.println("=====>flag2=" + flag2);
            System.out.println("=====>flag3=" + flag3);
        }
    }
}
