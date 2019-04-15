package com.cmazxiaoma.cvte;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/15 21:53
 */
public class InsertSort {

    private static int[] array = {4,2,1,4,67,656};

    public static void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void swap(int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    public static void sort0() {
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int j = i - 1;

            while (j >= 0 && value <= array[j]) {

                array[j + 1] = array[j];
                j --;
            }

            array[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        sort0();
        display();
    }
}
