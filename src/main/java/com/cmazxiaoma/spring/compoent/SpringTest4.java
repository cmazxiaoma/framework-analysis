package com.cmazxiaoma.spring.compoent;

import com.cmazxiaoma.spring.CommonSpringTest;
import com.cmazxiaoma.spring.bean.SpringTest1;
import com.cmazxiaoma.spring.bean.SpringTest2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 9:24
 */
@Component
public class SpringTest4 {

    @Value("${springTest4.name}")
    private String name;

    @Autowired
    private SpringTest1 springTest1;

    @Autowired
    private SpringTest2 springTest2;

    @Override
    public String toString() {
        return "SpringTest4{" +
                "name='" + name + '\'' +
                ", springTest1=" + springTest1 +
                ", springTest2=" + springTest2 +
                '}';
    }
}
