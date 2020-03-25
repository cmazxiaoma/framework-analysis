package com.cmazxiaoma.alibaba.贪心算法;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: 贪心算法 找硬币问题
 * @date 2020/2/14 22:18
 */

/**
 * 当n>=5时, 我们尽可能多剪长度为3的绳子
 *  当剩下的绳子长度为4 把绳子剪成两段
 *  长度为2的绳子
 * 当n>=5的时候, 2(n-2)>n, 3(n-3)>n, 3(n-3) >2(n-2)
 * 当n=4的时候,2*2>1*3  2(n-2)>3(n-3)
 */
public class TestTanxin {

    public static void main(String[] args) {
        System.out.println(cal(5));
        System.out.println(cal(6));
        System.out.println(cal(7));
        System.out.println(cal(8));
    }

    public static int cal(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int var3 = length / 3;

        if (length - 3 * var3 == 1) {
            var3 -= 1;
        }
        int var2 = (length - 3 * var3) / 2;
        return (int) (Math.pow(3, var3) * Math.pow(2, var2));
    }
}
