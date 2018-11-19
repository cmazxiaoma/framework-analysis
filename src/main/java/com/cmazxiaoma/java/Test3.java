package com.cmazxiaoma.java;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/16 23:16
 */
public class Test3 {

    public static void main(String[] args) {
        new Leaf();
        new Leaf();
        /*

        Root类的静态初始块
        Mid的静态初始块
        Leaf的静态初始化块
        Root类的普通初始化块
        Root类的无参数的构造器
        Mid的普通初始块
        Mid的无参数的构造器
        Mid的有参数的构造器，参数= 烟火跟我都是越黑越灿烂
        Leaf的普通的初始化块
        Leaf的无参数的构造器

        Root类的普通初始化块
        Root类的无参数的构造器
        Mid的普通初始块
        Mid的无参数的构造器
        Mid的有参数的构造器，参数= 烟火跟我都是越黑越灿烂
        Leaf的普通的初始化块
        Leaf的无参数的构造器
         */
    }

}

class Root {
    static {
        System.out.println("Root类的静态初始块");
    }

    {
        System.out.println("Root类的普通初始化块");
    }

    public Root() {
        System.out.println("Root类的无参数的构造器");
    }
}

class Mid extends Root {
    static {
        System.out.println("Mid的静态初始块");
    }

    {
        System.out.println("Mid的普通初始块");
    }

    public Mid() {
        System.out.println("Mid的无参数的构造器");
    }

    public Mid(String msg) {
        this();
        System.out.println("Mid的有参数的构造器，参数=" + msg);
    }
}

class Leaf extends Mid {
    static {
        System.out.println("Leaf的静态初始化块");
    }

    {
        System.out.println("Leaf的普通的初始化块");
    }

    public Leaf() {
        super("烟火跟我都是越黑越灿烂");
        System.out.println("Leaf的无参数的构造器");
    }

}
