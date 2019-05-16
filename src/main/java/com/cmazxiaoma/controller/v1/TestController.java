package com.cmazxiaoma.controller.v1;

import com.cmazxiaoma.controller.v1.BaseController;
import com.cmazxiaoma.elasticsearch.ResultVo;
import com.cmazxiaoma.elasticsearch.ResultVoGenerator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
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

    private static Set<Thread> threadList = new HashSet<>();
    private static Set<HttpServletRequest> requestList = new HashSet<>();

    @GetMapping("/1")
    public ResultVo test1() throws InterruptedException {
//        System.out.println("thread.id=" + Thread.currentThread().getId());
//        System.out.println("thread.name=" + Thread.currentThread().getName());

//        ServletRequestAttributes servletRequestAttributes =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
//
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        TimeUnit.SECONDS.sleep(5);

        ServletRequestAttributes servletRequestAttributes =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        Map<String, String> resultMap = new HashMap<>(2);
        resultMap.put("request.hashCode", httpServletRequest.hashCode() + "");
        resultMap.put("name", httpServletRequest.getParameter("name") + "");
        resultMap.put("request", httpServletRequest.toString());
        resultMap.put("thread", Thread.currentThread().toString());
        return ResultVoGenerator.genSuccessResult(resultMap);
    }

    @GetMapping("/2")
    public ResultVo test2() throws InterruptedException, IllegalAccessException, IntrospectionException {
//        System.out.println("thread.id=" + Thread.currentThread().getId());
//        System.out.println("thread.name=" + Thread.currentThread().getName());

        ServletRequestAttributes servletRequestAttributes =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        requestList.add(httpServletRequest);

        Field field = ReflectionUtils.findField(RequestContextHolder.class, "requestAttributesHolder");
        ReflectionUtils.makeAccessible(field);
        Class<?> clazz = RequestContextHolder.class.getClass();
        ThreadLocal<RequestAttributes> requestAttributesHolder = (ThreadLocal<RequestAttributes>) field.get(clazz);

        Class threadClass = Thread.currentThread().getClass();
        Field threadLocalsField = ReflectionUtils.findField(threadClass, "threadLocals");
        ReflectionUtils.makeAccessible(threadLocalsField);

        threadList.add(Thread.currentThread());
        Object threadLocals = threadLocalsField.get(Thread.currentThread());

        Map<String, String> resultMap = new HashMap<>(2);
        resultMap.put("request.hashCode", httpServletRequest.hashCode() + "");
        resultMap.put("name", httpServletRequest.getParameter("name") + "");
        resultMap.put("request", httpServletRequest.toString());
        resultMap.put("requestAttributesHolder", requestAttributesHolder.toString());
        resultMap.put("thread", Thread.currentThread().toString());
        resultMap.put("threadList", threadList.toString());

        return ResultVoGenerator.genSuccessResult(resultMap);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

//    @InitBinder("user")
//    public void initUser(WebDataBinder binder) {
//        binder.setFieldDefaultPrefix("user.");
//
//    }
//
//    @InitBinder("admin")
//    public void initAdmin(WebDataBinder binder) {
//        binder.setFieldDefaultPrefix("admin.");
//    }
}
