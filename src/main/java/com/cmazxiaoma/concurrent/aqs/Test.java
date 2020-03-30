package com.cmazxiaoma.concurrent.aqs;

import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/11 10:08
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        long start = System.currentTimeMillis();
        Method NO_METHOD_FOUND = ClassUtils.getMethodIfAvailable(System.class, "currentTimeMillis");
        ReflectionUtils.makeAccessible(NO_METHOD_FOUND);
        Constructor<System> constructor = System.class.getDeclaredConstructor();
        ReflectionUtils.makeAccessible(constructor);
        System system = constructor.newInstance();
        System.out.println(NO_METHOD_FOUND.invoke(system, null));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        long start1 = System.currentTimeMillis();
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }
}
