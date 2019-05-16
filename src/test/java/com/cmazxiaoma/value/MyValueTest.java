package com.cmazxiaoma.value;

import com.cmazxiaoma.InitSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/16 10:40
 */
public class MyValueTest extends InitSpringTest {

    @Autowired
    private ValueConfig valueConfig;

    @Test
    public void test() {
        System.out.println(valueConfig.getList().toString());
    }
}
