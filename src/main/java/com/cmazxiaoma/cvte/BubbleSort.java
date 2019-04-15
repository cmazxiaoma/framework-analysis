package com.cmazxiaoma.cvte;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/15 21:27
 */
public class BubbleSort {

    private static int[] array = {4,2,1,4,67,656};

    public static void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void sort0() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public static void swap(int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    public static void sort1() {
        for (int i = array.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        sort0();
        display();
        System.out.println("===================================");
        sort1();
        display();
    }
}
