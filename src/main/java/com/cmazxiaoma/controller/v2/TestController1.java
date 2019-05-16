package com.cmazxiaoma.controller.v2;

import com.cmazxiaoma.elasticsearch.ResultVo;
import com.cmazxiaoma.elasticsearch.ResultVoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 16:19
 */
@RequestMapping("/test")
@RestController
public class TestController1 extends BaseController1 {

    private static Set<HttpServletRequest> requestList = new HashSet<>();

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/3")
    public ResultVo test3() throws IllegalAccessException {
        Field field = ReflectionUtils.findField(RequestContextHolder.class, "requestAttributesHolder");
        ReflectionUtils.makeAccessible(field);
        Class<?> clazz = RequestContextHolder.class.getClass();
        ThreadLocal<RequestAttributes> requestAttributesHolder = (ThreadLocal<RequestAttributes>) field.get(clazz);

        Class threadClass = Thread.currentThread().getClass();
        Field threadLocalsField = ReflectionUtils.findField(threadClass, "threadLocals");
        ReflectionUtils.makeAccessible(threadLocalsField);

        Object threadLocals = threadLocalsField.get(Thread.currentThread());

        Map<String, String> resultMap = new HashMap<>(2);
        resultMap.put("request.hashCode", request.hashCode() + "");
        resultMap.put("request", request.toString());
        resultMap.put("requestAttributesHolder", requestAttributesHolder.toString());
        resultMap.put("name", request.getParameter("name"));
        resultMap.put("thread", Thread.currentThread().toString());
        return ResultVoGenerator.genSuccessResult(resultMap);
    }

    @GetMapping("/4")
    public ResultVo test4(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Map<String, String> resultMap = new HashMap<>(2);
        resultMap.put("httpServletRequest", httpServletRequest.toString());
        resultMap.put("httpServletRequest.hashCode", httpServletRequest.hashCode() + "");
        resultMap.put("name", httpServletRequest.getParameter("name"));
        resultMap.put("thread", Thread.currentThread().toString());
        return ResultVoGenerator.genSuccessResult(resultMap);
    }

    @GetMapping("/5")
    public ResultVo test5(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) {
        Map<String, String> resultMap = new HashMap<>(2);
        requestList.add(httpServletRequest);
        resultMap.put("httpServletRequest", httpServletRequest.toString());
        resultMap.put("httpServletRequest.hashCode", httpServletRequest.hashCode() + "");
        resultMap.put("name", httpServletRequest.getParameter("name"));
        resultMap.put("thread", Thread.currentThread().toString());
        resultMap.put("requestList", requestList.toString());
        return ResultVoGenerator.genSuccessResult(resultMap);
    }
}
