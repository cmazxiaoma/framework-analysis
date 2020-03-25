package com.cmazxiaoma.alibaba;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/12/30 17:35
 */
public class Test3 {

    public static void main(String[] args) {
        method4();
    }

    public static void method4() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(int i : list){
            System.out.println(i);
            if(i == 3){
                list.remove((Object)3);
            }
        }
    }

    public static void method1() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {

            if("1".equals(temp)){
                a.remove(temp);
            }
        }
    }

    public static void method2() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if("2".equals(temp)){
                a.remove(temp);
            }
        }
    }


    public static void method3() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            System.out.println(temp);
            if ("2".equals(temp)) {
                a.add("3");
                a.remove("2");
            }
        }
        System.out.println(a);
    }


}
