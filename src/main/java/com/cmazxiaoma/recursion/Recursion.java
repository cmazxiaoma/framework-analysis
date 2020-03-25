package com.cmazxiaoma.recursion;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/6/5 9:02
 */
@Component
@Repository
public class Recursion {

    /**
     * 对递归的理解在于放弃你对理解和跟踪递归的企图。
     * 只理解递归两层之间的交接,以及递归终结的条件。
     * @param args
     */
    public static void main(String[] args) {
        //累加
        System.out.println("addCommon:" + addCommon(10));
        System.out.println("addTail:" + addTail(10, 1));

        //累乘
        System.out.println("multiplyCommon:" + multiplyCommon(10));
        System.out.println("multiplyTail:" + multiplyTail(10, 1));

        //斐波那契数列
        System.out.println("feiboCommon:" + feiboCommon(10));
        System.out.println("feiboTail:" + feiboTail(10, 0, 1));
        System.out.println("feiboWhile:" + feiboWhile(10));

        //汉诺塔
        hanoiMove(3, "A", "B", "C");
    }

    /**
     * 累加普通递归
     * @param n
     * @return
     */
    public static int addCommon(int n) {
        if (n == 1) {
            return 1;
        }

        return n + addCommon(n - 1);
    }

    /**
     * 累加尾部递归
     * @param n
     * @param sum
     * @return
     */
    public static int addTail(int n, int sum) {
        if (n == 1) {
            return sum;
        }

        return addTail(n - 1, n + sum);
    }

    /**
     * 累乘普通递归
     * @param n
     * @return
     */
    public static int multiplyCommon(int n) {
        if (n == 1) {
            return 1;
        }
        return n * multiplyCommon(n - 1);
    }

    /**
     * 累乘尾递归
     * @param n
     * @param sum
     * @return
     */
    public static int multiplyTail(int n, int sum) {
        if (n == 1) {
            return sum;
        }
        return multiplyTail(n - 1, n * sum);
    }

    /**
     * 斐波那契数列普通递归
     * @param n
     * @return
     */
    public static int feiboCommon(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

//        if (n <= 2) {
//            return 1;
//        }

        return feiboCommon(n - 1) + feiboCommon(n - 2);
    }

    /**
     * 斐波那契数列尾递归
     * @param n
     * @param a
     * @param b
     * @return
     */
    public static int feiboTail(int n, int a, int b) {
        if (n == 0) {
            return a;
        }
        return feiboTail(n - 1, b, a + b);
    }

    /**
     * 斐波那契数列循环
     * @param n
     * @return
     */
    public static int feiboWhile(int n) {
        int a = 1;
        int b = 1;
        int c = 1;
        int temp;

        while (n > 2) {
            c = a + b;
            temp = a;
            a = b;
            b = temp + b;
            n--;
        }

        return c;
    }

    /**
     * 汉诺塔
     * @param n
     * @param from
     * @param buffer
     * @param to
     */
    public static void hanoiMove(int n, String from, String buffer, String to) {
        if (n == 1) {
            System.out.println("move " + "from:" + from + "----->to:" + to);
        } else {
            hanoiMove(n - 1, from, to, buffer);
            hanoiMove(1, from, buffer, to);
            hanoiMove(n - 1, buffer, from, to);
        }
    }
}
