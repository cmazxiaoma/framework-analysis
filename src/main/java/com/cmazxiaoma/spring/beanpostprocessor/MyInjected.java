package com.cmazxiaoma.spring.beanpostprocessor;

import java.lang.annotation.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 13:05
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyInjected {
}
