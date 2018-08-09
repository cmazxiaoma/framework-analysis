package com.cmazxiaoma.spring;

import com.cmazxiaoma.spring.bean.SpringTest1;
import com.cmazxiaoma.spring.bean.SpringTest2;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 16:15
 */
public abstract class CommonSpringTest {

    @Autowired
    private SpringTest1 springTest1;

    @Autowired
    private SpringTest2 springTest2;

    @Override
    public String toString() {
        return "CommonSpringTest{" +
                "springTest1=" + springTest1 +
                ", springTest2=" + springTest2 +
                '}';
    }
}
