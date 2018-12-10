package com.cmazxiaoma.gc;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/4 18:08
 */
public class RuntimeConsantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuffer("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuffer("ja1").append("va").toString();
        System.out.println(str2.intern() == str2);

    }
}
