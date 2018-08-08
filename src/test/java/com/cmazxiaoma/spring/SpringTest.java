package com.cmazxiaoma.spring;

import com.cmazxiaoma.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 16:16
 */
public class SpringTest extends BaseTest {

    @Test
    public void test() {
        SpringTest3 springTest3 = new SpringTest3();
        System.out.println("springTest3=" + springTest3);
    }
}
