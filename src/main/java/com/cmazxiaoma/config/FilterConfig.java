package com.cmazxiaoma.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/4 9:49
 */
@Configuration
public class FilterConfig {

    /**
     * 解决hibernate懒加载出现的no session问题
     * @return
     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new OpenSessionInViewFilter());
//        filterRegistrationBean.addInitParameter("urlPatterns", "/*");
//        return filterRegistrationBean;
//    }

    /**
     * 解决jpa 懒加载出现的no session问题
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new OpenEntityManagerInViewFilter());
        filterRegistrationBean.addInitParameter("urlPatterns", "/*");
        return filterRegistrationBean;
    }
}
