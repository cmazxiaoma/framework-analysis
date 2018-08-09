package com.cmazxiaoma.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 16:13
 */
@Component
public class SpringTest1 {

    private String name = "springTest1";

    @Override
    public String toString() {
        return "SpringTest1{" +
                "name='" + name + '\'' +
                '}';
    }

}
