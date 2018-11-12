package com.cmazxiaoma.controller;

import com.cmazxiaoma.hibernate.ISchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/9 18:00
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    private ISchoolDao schoolDao;

    @GetMapping("/test")
    public void test() {
        System.out.println("thread test");
        new Thread(new Runnable() {
            @Override
            public void run() {
                schoolDao.findAll();
            }
        }).start();
    }
}
