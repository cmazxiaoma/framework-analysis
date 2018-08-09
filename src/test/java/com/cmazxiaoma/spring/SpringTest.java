package com.cmazxiaoma.spring;

import com.cmazxiaoma.BaseTest;
import com.cmazxiaoma.spring.bean.SpringTest3;
import com.cmazxiaoma.spring.bean.SpringTestBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 16:16
 */
public class SpringTest extends BaseTest {

    @Autowired
    private SpringTest3 springTest3;

    @Autowired
    private SpringTestBean springTestBean;

    @Test
    public void test() {
        System.out.println("springTest3=" + springTest3);
    }

    @Test
    public void test1() {
        System.out.println("springTestBean=" + springTestBean);
    }

}
