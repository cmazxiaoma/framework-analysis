package com.cmazxiaoma.spring;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.spring.loop.LoopReferenceA;
import com.cmazxiaoma.spring.loop.LoopReferenceB;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/14 15:46
 */
public class LoopReferenceTest extends InitSpringTest {

    @Autowired
    private LoopReferenceA loopReferenceA;

    @Autowired
    private LoopReferenceB loopReferenceB;

    @Test
    public void test() {
        System.out.println(loopReferenceA);
        System.out.println(loopReferenceB);
    }
}
