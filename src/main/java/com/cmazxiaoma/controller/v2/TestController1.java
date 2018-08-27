package com.cmazxiaoma.controller.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 16:19
 */
@RequestMapping("/test")
@RestController
public class TestController1 extends BaseController1{

    @GetMapping("/3")
    public void test3() {
//        System.out.println("thread.id=" + Thread.currentThread().getId());
//        System.out.println("thread.name=" + Thread.currentThread().getName());
//
//        ServletRequestAttributes servletRequestAttributes =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
//
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
//
//        System.out.println("base.request=" + request);
        System.out.println("base.request.name=" + request.getParameter("name"));
    }

//    @GetMapping("/4")
//    public void test4(HttpServletRequest httpServletRequest,
//                      HttpServletResponse httpServletResponse) {
//        System.out.println("httpServletRequest.name="
//                + httpServletRequest.getParameter("name"));
//    }
}
