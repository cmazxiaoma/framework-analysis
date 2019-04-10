package com.cmazxiaoma.spring.inject;

import com.cmazxiaoma.InitSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/26 11:55
 */
public class InjectTest extends InitSpringTest {

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

            // 判断一个bean是否是FactoryBean
            Boolean flag1 = BeanFactoryUtils.isFactoryDereference("mySessionFactory") ;
            Boolean flag2 = defaultListableBeanFactory.isFactoryBean("mySessionFactory");
            Boolean flag3 = (!BeanFactoryUtils.isFactoryDereference("mySessionFactory")
                    || defaultListableBeanFactory.isFactoryBean("mySessionFactory"));

            System.out.println("=====>flag1=" + flag1);
            System.out.println("=====>flag2=" + flag2);
            System.out.println("=====>flag3=" + flag3);

            // false false true
        }
    }

    @Test
    public void test3() {
        if (defaultListableBeanFactory.containsSingleton("myFactoryBean")
                || defaultListableBeanFactory.containsBeanDefinition("myFactoryBean")) {

            // 判断一个bean是否是FactoryBean
            Boolean flag1 = BeanFactoryUtils.isFactoryDereference("&myFactoryBean");

            Boolean flag2 = defaultListableBeanFactory.isFactoryBean("myFactoryBean");
            Boolean flag3 = (!BeanFactoryUtils.isFactoryDereference("myFactoryBean")

                    || defaultListableBeanFactory.isFactoryBean("myFactoryBean"));

            System.out.println("=====>flag1=" + flag1);
            System.out.println("=====>flag2=" + flag2);
            System.out.println("=====>flag3=" + flag3);

            // false true true
        }
    }


    @Autowired
    private MyFactoryBean autowiredMyFactoryBean;

    @Resource(name = "myFactoryBean")
    private User resourceMyFactoryBean;

    @Test
    public void test4() throws Exception {
        MyFactoryBean myFactoryBean = (MyFactoryBean) applicationContext.getBean("&myFactoryBean");

        User user = (User) applicationContext.getBean("myFactoryBean");

        System.out.println(myFactoryBean);
        System.out.println(user);

        System.out.println(autowiredMyFactoryBean);
        System.out.println(resourceMyFactoryBean);
    }

    @Autowired
    private MyBaseDao myBaseDao;

    @Test
    public void test5() {
        System.out.println("template:" + myBaseDao.getTemplate());
    }
}
