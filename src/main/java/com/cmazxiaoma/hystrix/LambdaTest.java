package com.cmazxiaoma.hystrix;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 22:05
 */
public class LambdaTest {

    public static void main(String[] args) {
        List<Integer> idList = Lists.newArrayList();
        idList.add(0);
        idList.add(0);
        idList.add(0);

        List<Integer> newList = idList.stream().filter(item -> !Objects.equals(item, 0))
                .collect(Collectors.toList());
        System.out.println(newList);
    }
}
