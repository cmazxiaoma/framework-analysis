package com.cmazxiaoma.extendsuper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: <? extends class> <? super class>
 * @date 2018/7/10 11:54
 */
public class extendSuper {

    public static void main(String[] args) {
        List<? extends Fruit> fruitList = new ArrayList<>();
//        fruitList.add(new Apple());
//        fruitList.add(new Orange());

        fruitList.add(null);
        fruitList.contains(new Apple());
        fruitList.indexOf(new Apple());

        Apple apple = (Apple) fruitList.get(0);
        Orange orange = (Orange) fruitList.get(0);

        System.out.println("apple=" + apple);
        System.out.println("orange=" + orange);

        List<? super Apple> fruitList1 = new ArrayList<>();
        fruitList1.add(new Apple());
        fruitList1.add(new Apple1());
//        fruitList1.add(new Fruit());

        Apple apple1 = (Apple) fruitList1.get(0);
        Apple apple2 = (Apple) fruitList1.get(0);
        System.out.println("apple1=" + apple1);
        System.out.println("apple2=" + apple2);
    }
}

class Fruit {}
class Apple extends Fruit {}
class Orange extends Fruit {}
class Apple1 extends Apple {}
