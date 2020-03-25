package com.cmazxiaoma.jdk8;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/24 20:42
 */
public class Test {

    public static void main(String[] args) {
        String s = " ";

        System.out.println(StringUtils.isNotBlank(s));
        System.out.println(!StringUtils.isBlank(s));
        System.out.println(!StringUtils.isEmpty(s));
    }
}
