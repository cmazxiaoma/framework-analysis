package com.cmazxiaoma.cvte;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/15 21:58
 */
public class QuickSort {

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

    public static void sort0(int start, int end) {
        if (start < end) {
            int startTemp = start;
            int endTemp = end;
            int standrad = array[start];

            while (startTemp < endTemp) {

                while (startTemp < endTemp && array[endTemp] >= standrad) {
                    endTemp --;
                }

                if (startTemp < endTemp) {
                    array[startTemp++] = array[endTemp];
                }

                while (startTemp < endTemp && array[startTemp] <= standrad) {
                    startTemp ++;
                }

                if (startTemp < endTemp) {
                    array[endTemp--] = array[startTemp];
                }
            }
            array[startTemp] = standrad;
            sort2(start, startTemp - 1);
            sort2(startTemp + 1, end);
        }
    }

    public static void sort2(int start, int end) {
        if (start < end) {
            int index = sort2GetIndex(start, end);
            sort2(start, index - 1);
            sort2(index + 1, end);
        }
    }

    public static int sort2GetIndex(int start, int end) {
        int max = array[end];
        int index = start;

        for (int i = start; i < end; i++) {
            if (array[i] <= max) {
                swap(i, index);
                index++;
            }
        }
        swap(index, end);
        return index;
    }

    public static void main(String[] args) {
        sort0(0, array.length - 1);
        display();
        System.out.println("==============================");
        sort2(0, array.length - 1);
        display();
    }
}
