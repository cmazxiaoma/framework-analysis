package com.cmazxiaoma.spring.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/14 15:43
 */
@Component
public class LoopReferenceB {

    @Autowired
    private LoopReferenceA loopReferenceA;

}
