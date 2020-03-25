package com.cmazxiaoma.alibaba.贪心算法;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: 动态规划 找硬币问题
 * @date 2020/2/14 22:18
 */

/**
 * 给你一根长度为n的绳子，请把绳子剪成m段，
 * 请问最终每段绳子长度的乘积最大值是多少？例如，当绳子的长度为8时，我们剪成3,3,2三段，最大乘积是18。
 */

/**
 * 求一个问题的最优解
 * 我们把长度为n的绳子剪成若干段后的乘积记为f(n),假设我们第一刀剪在了长度为i的位置(0<i<n),
 * 于是绳子剪成了长度分别为i和n-i的两段。
 * 我们要想得到最优解f(n),那么同样要用最优的方法把这2段分别剪成若干段，使得他们各自剪出的
 * 每段绳子的长度乘积最大。
 *
 * 我们把大问题分解成若干个小问题，这些小问题之间还会有相互重叠的更小的问题嘛
 *
 * 假设绳子最初的长度是10,我们剪成了4和6的两段
 * 那么f(4)和f(6)就成了f(10)的子问题。
 *
 * 我们将长度为4的绳子均分为长度为2的绳子，
 * 那么f(2)就是f(4)的子问题。
 *
 * 长度为6的绳子分并存储起来为2和4
 * f(2)和f(4)是f(6)的子问题。
 *
 * 我们注意到 f(2)是f(4)和f(6)公共的子问题
 */

/**
 * 状态转移方程 f(n) = max{f(i)*f(n-i)} (0 <= i < n)
 * f(0) = 0
 * f(1) = 1
 * f(2) = 1
 * f(3) = 2
 *
 * f(4) = 4
 * f(5) = 6
 * f(6) = 9
 * f(7) = 12
 *
 * 以我举的例子f(8)=18为例
 * f(1)f(7) = 12
 * f(2)f(6) = 9
 * f(3)f(5) = 12
 * f(4)f(4) = 16
 * 得出最优解16 并不能算出最优解18
 *
 * 尝试看看是否可以分析出比较合理得状态转移方程
 * f(8) = 18 = 2*3*3 = f(5) * (8 - 5)
 * 存在f(n) = f(i) * (n -i)
 *
 * 再看f(4) 如果使用f(i)f(n-i) 只能得出f(1)f(4-1)=2
 *  如果使用f(n)=f(i)*(n-i) = f(1) * 3 = 3
 *  于是就可以推出存在f(n) = i*(n-i)  f(4)=2*2=4
 */


public class TestDongtai {

    public static void main(String[] args) {
        System.out.println(cal(8));
    }

    public static int cal(int length) {
        if (length <= 0) {
            return 0;
        }
        int[] products = new int[length + 1];
        products[1] = 1;

        for (int i = 2; i <= length; i++) {
            for (int j = 1; j < i ; j++) {
                products[i] =
                        Math.max(products[i],
                                Math.max(j * (i - j), products[j] * (i - j))
                        );
            }
        }

        return products[length];
    }

}
