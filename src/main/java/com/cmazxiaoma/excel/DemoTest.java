package com.cmazxiaoma.excel;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/24 10:01
 */
public class DemoTest {

    public static void main(String[] args) {
        List<Integer> resultList = Lists.newArrayList();

        for (int i = 1; i <=66; i++) {
            resultList.add(i);
        }

        System.out.println(resultList.size());

        System.out.println(resultList.subList(0, 65));
    }

}
