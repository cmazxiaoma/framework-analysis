package com.cmazxiaoma.spring.inject;

import com.cmazxiaoma.InitSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/26 11:55
 */
public class InjectTest extends InitSpringTest {

    @Autowired
    private MyBaseDao myBaseDao;

    @Test
    public void test1() {
        System.out.println(myBaseDao.getTemplate());
    }
}
