package com.cmazxiaoma.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/19 15:17
 */
public class Test1 {

    public static void main(String[] args) {
        List<String> ab = new ArrayList<>();
        ab.add("aaa");
        ab.add("aaa");
        ab.add("bbbb");
        System.out.println(ab.parallelStream().map(p -> p).collect(Collectors.toList()).toString());
    }
}
