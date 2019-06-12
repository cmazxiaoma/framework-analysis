package com.cmazxiaoma.hystrix;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/29 16:31
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(Objects.equals(String.valueOf(12), "12"));

//        List<Integer> list = null;
//        List<Integer> list2 = list.stream().limit(100).collect(Collectors.toList());
//        System.out.println(list2);

        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));

        // true
        System.out.println(!Boolean.FALSE.equals(null));
        // true
        System.out.println(!Boolean.FALSE.equals(true));
        // false
        System.out.println(!Boolean.FALSE.equals(false));
    }
}
