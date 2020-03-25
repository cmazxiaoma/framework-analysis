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

//        fruitList.add(new Fruit());
//        fruitList.add(new Thing());

        fruitList.add(null);
        fruitList.contains(new Apple());
        fruitList.indexOf(new Apple());

        Fruit fruit = fruitList.get(0);
        Orange orange = (Orange) fruitList.get(0);

        System.out.println("fruit=" + fruit);
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
class Thing {}
class Fruit extends Thing {}
class Apple extends Fruit {}
class Orange extends Fruit {}
class Apple1 extends Apple {}


/**
 *  <? extends E>
 *      add:不允许添加任何元素
 *      get:可以获取元素，但是必须使用E来接收元素
 *
 *  <? super E>
 *      add:可以添加E和E的子类元素
 *      get:可以获取元素，但是类的信息丢失了，所以返回只能使用Object引用来接收。
 *      如果需要自己类型需要强制转换。
 */
