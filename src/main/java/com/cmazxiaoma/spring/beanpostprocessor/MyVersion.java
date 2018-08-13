package com.cmazxiaoma.spring.beanpostprocessor;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 13:07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface MyVersion {

    String value() default "";
}
