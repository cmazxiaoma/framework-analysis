package com.cmazxiaoma.alibaba;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/28 15:14
 */
public class JiechengTest {

    /**
     * 是否是2的阶乘
     * @param n
     * @return
     */
    public static boolean isJieCheng(Long n) {
        return (n & n - 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isJieCheng(1L));
        System.out.println(isJieCheng(2L));
        System.out.println(isJieCheng(3L));
        System.out.println(isJieCheng(4L));
        System.out.println(isJieCheng(8L));
        System.out.println(isJieCheng(16L));
        System.out.println(isJieCheng(32L));
        System.out.println(isJieCheng(64L));
    }


}
