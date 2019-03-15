package com.cmazxiaoma.alibaba.贪心算法;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/2 16:34
 */

/**
 * 贪心算法是指：在每一步求解的步骤中，它要求"贪婪"的选择最佳操作，
 * 并希望通过一系列的最佳选择，能够产生一个问题(全局)的最优解。
 */
public class CoinChange {
    private static int[] values = {1, 2, 5, 10, 20, 50, 100};
    private static int[] counts = {3, 1, 2, 1, 1, 3, 5};


    public static void main(String[] args) {
        print(cal(442));
    }

    public static int[] cal(int money) {
        int[] result = new int[values.length];

        for (int i = values.length - 1; i >= 0; i--) {
            int c = min(money / values[i], counts[i]);
            money = money - c * values[i];
            result[i] = c;
        }
        return result;
    }

    public static int min(int x, int y) {
        return x > y ? y : x;
    }

    public static void print(int[] result) {
        for (int i = values.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                System.out.println("需要面额为"
                + values[i] + "的人民币的" + result[i] + "张");
            }
        }
    }

}
