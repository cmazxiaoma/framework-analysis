package com.cmazxiaoma.spring.compoent;

import com.cmazxiaoma.spring.CommonSpringTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 9:24
 */
@Component
public class SpringTest4 extends CommonSpringTest {

    @Value("${springTest4.name}")
    private String name;

    @Override
    public String toString() {
        return "SpringTest4{" +
                "name='" + name + '\'' +
                '}';
    }
}
