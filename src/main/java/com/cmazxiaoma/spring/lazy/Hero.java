package com.cmazxiaoma.spring.lazy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/9 14:14
 */
@Component
@Lazy
public class Hero {

    public void qSkill() {
        System.out.println("q skill");
    }

    public void wSkill() {
        System.out.println("w skill");
    }

    public void eSkill() {
        System.out.println("e skill");
    }

    public void rSkill() {
        System.out.println("r skill");
    }

}
