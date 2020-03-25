package com.cmazxiaoma.jdk8;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/11 9:31
 */
public class Jdk8MapTest {


    public static void main(String[] args) {
        Map<String, String>  resultMap = new ConcurrentHashMap<>();

        resultMap.put("majinlan", "vue");

        String result = resultMap.compute("cmazxiaoma", new BiFunction<String, String, String>() {

            @Override
            public String apply(String s, String s2) {
                System.out.println("s:" + s);
                System.out.println("s2:" + s2);
                return "java";
            }
        });


        System.out.println(result);
        System.out.println(resultMap);

        result = resultMap.compute("cmazxiaoma", new BiFunction<String, String, String>() {

            @Override
            public String apply(String s, String s2) {
                System.out.println("s:" + s);
                System.out.println("s2:" + s2);
                return null;
            }
        });

        System.out.println(result);
        System.out.println(resultMap);





        result = resultMap.computeIfAbsent("cmazxiaoma", new Function<String, String>() {
            @Override
            public String apply(String s) {
                System.out.println("s:" + s);
                return null;
            }
        });

        System.out.println(result);


        resultMap.computeIfAbsent("majinlan", new Function<String, String>() {
            @Override
            public String apply(String s) {
                System.out.println("s:" + s);
                return null;
            }
        });

        System.out.println(result);




        result = resultMap.computeIfPresent("cmazxiaoma", new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                System.out.println("s:" + s);
                System.out.println("s2:" + s2);
                return null;
            }
        });

        System.out.println(result);



        result = resultMap.computeIfPresent("majinlan", new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                System.out.println("s:" + s);
                System.out.println("s2:" + s2);
                return null;
            }
        });

        System.out.println("map:" + resultMap);
        System.out.println("result:" + result);
    }
}
