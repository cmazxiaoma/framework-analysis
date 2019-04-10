package com.cmazxiaoma.spring.inject;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/29 23:01
 */
@Component
public class MyFactoryBean implements FactoryBean<User>, InitializingBean {

    private User user;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.user = buildUser();
    }

    private User buildUser() {
        return new User(1L, "cmazxiaoma");
    }

    @Override
    public User getObject() throws Exception {
        return this.user;
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public String toString() {
        return "MyFactoryBean{}";
    }
}
