package com.cmazxiaoma;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/20 17:58
 */
@Component
@Transactional
public class MyCommonDao extends HibernateDaoSupport {

//    @Autowired
//    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
//        super.setSessionFactory(sessionFactory);
//    }

    public String list() {
        this.getSessionFactory().getCurrentSession();
        return "success";
    }
}
