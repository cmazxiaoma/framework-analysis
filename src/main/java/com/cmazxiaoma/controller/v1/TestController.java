package com.cmazxiaoma.controller.v1;

import com.cmazxiaoma.controller.v1.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 13:31
 */
@RequestMapping("/test")
@RestController
public class TestController extends BaseController {

    @GetMapping("/1")
    public void test1() throws InterruptedException {
//        System.out.println("thread.id=" + Thread.currentThread().getId());
//        System.out.println("thread.name=" + Thread.currentThread().getName());

//        ServletRequestAttributes servletRequestAttributes =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
//
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        TimeUnit.SECONDS.sleep(10);

//        System.out.println("base.request=" + request);
        System.out.println("base.request.name=" + request.getParameter("name"));
    }

    @GetMapping("/2")
    public void test2() throws InterruptedException {
//        System.out.println("thread.id=" + Thread.currentThread().getId());
//        System.out.println("thread.name=" + Thread.currentThread().getName());

//        ServletRequestAttributes servletRequestAttributes =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
//
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

//        System.out.println("base.request=" + request);
        System.out.println("base.request.name=" + request.getParameter("name"));

    }
}
