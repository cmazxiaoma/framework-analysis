package com.cmazxiaoma.spring.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/14 15:42
 */
@Component
public class LoopReferenceA {

    @Autowired
    private LoopReferenceB loopReferenceB;

//    public LoopReferenceA(@Autowired LoopReferenceB loopReferenceB) {
//        this.loopReferenceB = loopReferenceB;
//    }
}
