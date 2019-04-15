package com.cmazxiaoma.cvte;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/15 21:43
 */
public class SelectSort {

    private static int[] array = {4,2,1,4,67,656};

    public static void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void sort() {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[minIndex];

            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(minIndex, i);
            }
        }
    }

    public static void swap(int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    public static void main(String[] args) {
        sort();
        display();
    }
}
