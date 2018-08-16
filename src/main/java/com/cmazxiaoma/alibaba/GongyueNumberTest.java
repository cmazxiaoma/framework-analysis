package com.cmazxiaoma.alibaba;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/15 14:21
 */
public class GongyueNumberTest {

    public static int number1 = 42864222;

    public static int number2 = 82468264;

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        int result1 = gcd1(number1, number2);
        System.out.println("result1=" + result1 + ",耗时=" + (System.currentTimeMillis() - start1) + "ms");

        long start2 = System.currentTimeMillis();
        int result2 = gcd2(number1, number2);
        System.out.println("result2=" + result2 + ",耗时=" + (System.currentTimeMillis() - start2) + "ms");

        long start3 = System.currentTimeMillis();
        int result3 = gcd3(number1, number2);
        System.out.println("result3=" + result3 + ",耗时=" + (System.currentTimeMillis() - start3) + "ms");

        long start4 = System.currentTimeMillis();
        int result4 = gcd4(number1, number2);
        System.out.println("result4=" + result4 + ",耗时=" + (System.currentTimeMillis() - start4) + "ms");
    }

    /**
     * 暴力破解法
     */
    public static int gcd1(int number1, int number2) {
        int small = number1 >= number2 ? number2 : number1;
        int big = number1 >= number2 ? number1 : number2;

        if (big % small == 0) {
            return small;
        }

        int number = 1;

        // small / 2 缩小范围
        for (int i = 2; i <= small / 2; i++) {
            if (small % i == 0 && big % i == 0) {
                number = i;
            }
        }

        return number;
    }

    /**
     * 辗转相除法
     */
    public static int gcd2(int number1, int number2) {
        int small = number1 >= number2 ? number2 : number1;
        int big = number1 >= number2 ? number1 : number2;

        if (big % small == 0) {
            return small;
        }

        return gcd2(small, big % small);
    }

    /**
     * 更相损减术
     * @param number1
     * @param number2
     * @return
     */
    public static int gcd3(int number1, int number2) {
        int small = number1 >= number2 ? number2 : number1;
        int big = number1 >= number2 ? number1 : number2;

        if (small == big) {
            return small;
        }

        return gcd3(small, big - small);
    }

    /**
     * 移位法 优化更相损减法
     * @return
     */
    public static int gcd4(int number1, int number2) {
        int small = number1 >= number2 ? number2 : number1;
        int big = number1 >= number2 ? number1 : number2;

        if (small == big) {
            return small;
        }

        if ((small&1) == 0 && (big&1) == 0) {
            return gcd4(small >> 1, big >> 1) << 1;
        } else if ((small&1) == 1 && (big&1) == 0) {
            return gcd4(small, big >>1);
        } else if ((small&1) == 0 && (big&1) == 1) {
            return gcd4(small >> 1, big);
        } else {
            return gcd4(small, big - small);
        }
    }
}
