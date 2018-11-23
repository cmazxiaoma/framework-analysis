package com.cmazxiaoma.cpucache;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/23 12:46
 */
public class SizeOfObject {

    static Instrumentation inst;

    public static void premain(String args, Instrumentation instP) {
        inst = instP;
    }

    /**
     * 直接计算当前对象占用空间大小，包括当前类及超类的基本类型实例字段大小、<br></br>
     * 引用类型实例字段引用大小、实例基本类型数组引用本身占用空间大小、实例引用类型数组引用本身占用空间大小;<br></br>
     * 但是不包括超类继承下来的和当前类声明的实例引用字段的对象本身的大小、实例引用数组引用的对象本身的大小 <br></br>
     *
     * @param obj
     * @return
     */
    public static long sizeOf(Object obj) {
        return inst.getObjectSize(obj);
    }

    /**
     * 递归计算当前对象占用空间总大小，包括当前类和超类的实例字段大小以及实例字段引用对象大小
     *
     * @param objP
     * @return
     * @throws IllegalAccessException
     */
    public static long fullSizeOf(Object objP) throws IllegalAccessException {
        Set<Object> visited = new HashSet<Object>();
        Deque<Object> toBeQueue = new ArrayDeque<>();
        toBeQueue.add(objP);
        long size = 0L;
        while (toBeQueue.size() > 0) {
            Object obj = toBeQueue.poll();
            //sizeOf的时候已经计基本类型和引用的长度，包括数组
            size += skipObject(visited, obj) ? 0L : sizeOf(obj);
            Class<?> tmpObjClass = obj.getClass();
            if (tmpObjClass.isArray()) {
                //[I , [F 基本类型名字长度是2
                if (tmpObjClass.getName().length() > 2) {
                    for (int i = 0, len = Array.getLength(obj); i < len; i++) {
                        Object tmp = Array.get(obj, i);
                        if (tmp != null) {
                            //非基本类型需要深度遍历其对象
                            toBeQueue.add(Array.get(obj, i));
                        }
                    }
                }
            } else {
                while (tmpObjClass != null) {
                    Field[] fields = tmpObjClass.getDeclaredFields();
                    for (Field field : fields) {
                        if (Modifier.isStatic(field.getModifiers())   //静态不计
                                || field.getType().isPrimitive()) {    //基本类型不重复计
                            continue;
                        }

                        field.setAccessible(true);
                        Object fieldValue = field.get(obj);
                        if (fieldValue == null) {
                            continue;
                        }
                        toBeQueue.add(fieldValue);
                    }
                    tmpObjClass = tmpObjClass.getSuperclass();
                }
            }
        }
        return size;
    }

    /**
     * String.intern的对象不计；计算过的不计，也避免死循环
     *
     * @param visited
     * @param obj
     * @return
     */
    static boolean skipObject(Set<Object> visited, Object obj) {
        if (obj instanceof String && obj == ((String) obj).intern()) {
            return true;
        }
        return visited.contains(obj);
    }

    public static void main(String[] args) throws IllegalAccessException {
        // 开启-XX:+UseCompressedOops
        System.out.println(sizeOf(new Object()));

        System.out.println(sizeOf(new Integer[0]));
        System.out.println(sizeOf(new Integer[1]));
        System.out.println(sizeOf(new Integer[2]));
        System.out.println(sizeOf(new Integer[3]));
        System.out.println(sizeOf(new Integer[4]));
        System.out.println(sizeOf(new Integer[5]));


        System.out.println(sizeOf(new long[6]));

        long[] longArray = new long[6];
        System.out.println(sizeOf(longArray));

        System.out.println(sizeOf(Integer.valueOf(1)));
        System.out.println(sizeOf(Double.valueOf(1)));

        String s = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println("string=" + sizeOf(s));

        // 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 12 + padding
        System.out.println("c:" + sizeOf(new C()));
        System.out.println(fullSizeOf(new C()));

        // 对象总空间占有: 12 + 4 + 4 + 4 + (16 + ( (12 + 4 + 4 + padding) * 3) + padding) + (16 + ( 6 * 8 ) + padding) + 4 + 4 + (16 + ( 4 * 6) + padding) + padding

        /**
         *  1 => 24 + （16 + 72 + padding) + (64 + padding) + 8 + (40 + padding) + padding
         *  2 => 24 + 88 + 64 + 8 + 40 + padding
         *  3 => 224
         */


        // 对象本身占有空间: 12 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + padding = 48
        System.out.println(ObjectSizeCalculator.getObjectSize(new C()));
    }

    static class B {
        int a;
        int b;
    }

    static class C extends B {
        B[] as = new B[3];

        // 12 + 4 + 4 + (16 + (12 + 4 + 4 + padding) * 3 + padding) + padding

        // 20 + (16 +  72 + padding) + padding

        // 20 + 88 + padding

        // 112
        C() {
            for (int i = 0; i < as.length; i++) {
                as[i] = new B();
            }
        }
    }
}
