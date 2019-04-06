package com.cmazxiaoma.hibernate;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.MyCommonDao;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/20 17:59
 */
public class MyCommonDaoTest extends InitSpringTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MyCommonDao myCommonDao;

    @Test
    public void test() {
        SessionFactory sessionFactory = (SessionFactory)
                applicationContext.getBean("sessionFactory");
        System.out.println(sessionFactory);
        System.out.println(myCommonDao.list());
    }
}
