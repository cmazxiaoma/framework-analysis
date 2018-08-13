package com.cmazxiaoma.beanpostprocessor;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.spring.beanpostprocessor.IAbService;
import com.cmazxiaoma.spring.beanpostprocessor.MyInjected;
import org.junit.Test;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 14:32
 */
public class MyVersionBeanPostProcessorTest extends InitSpringTest {

    @MyInjected
    private IAbService abService;

    @Test
    public void test() {
        abService.create();
        abService.retrieve();
        abService.update();
        abService.delete();
    }

}
