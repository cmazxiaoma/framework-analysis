package com.cmazxiaoma.spring.beanpostprocessor;

import org.springframework.stereotype.Service;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 15:53
 */
@Service
public class AbServiceImplV2 implements IAbService {

    @Override
    public void create() {
        System.out.println("create v2");
    }

    @Override
    public void retrieve() {
        System.out.println("retrieve v2");
    }

    @Override
    public void update() {
        System.out.println("update v2");
    }

    @Override
    public void delete() {
        System.out.println("delete v2");
    }

}
