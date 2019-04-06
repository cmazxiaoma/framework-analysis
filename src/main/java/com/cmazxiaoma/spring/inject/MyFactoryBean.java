package com.cmazxiaoma.spring.inject;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/29 23:01
 */
@Component
public class MyFactoryBean implements FactoryBean<String> {

    @Override
    public String getObject() throws Exception {
        return new String("MyFactoryBean getObject");
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
