package com.cmazxiaoma.cvte;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/15 21:07
 */
public class BinarySort {

    private static int[] array = {4,2,1,4,67,656};

    public static void sort() {
        for (int i = 0; i < array.length; i++) {
            int searchValue = array[i];
            int start = 0;
            int end = i - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                int value = array[mid];

                if (searchValue > value) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            for (int k = i; k > start; k--) {
                array[k] = array[k - 1];
            }

            array[start] = searchValue;
        }
    }

    public static void main(String[] args) {
        sort();
        display();
    }

    public static void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
