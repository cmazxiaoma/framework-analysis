package com.cmazxiaoma.string;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/18 22:13
 */
public class StringTest {

    public static void main(String[] args) throws IllegalAccessException {
        String s = new String("hello world");
        Field field = ReflectionUtils.findField(String.class, "value");
        ReflectionUtils.makeAccessible(field);
        char[] value = (char[]) field.get(s);
        value[5] = '_';
        System.out.println(s);
    }
}
