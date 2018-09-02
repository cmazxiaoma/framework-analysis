package com.cmazxiaoma.alibaba;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/15 19:07
 */
public class QuickSortTest {

    public static int[] array = {3123, 43, 4234, 123, 13, 5434, 13, 124, 2112, 10, 5, 1, 0, 424234};

    public static void main(String[] args) {
//        sortByRecursion(0, array.length - 1);

//        sortByRecursion2(0, array.length - 1);

          insertSort();

//        bubbleSort();

//        selectionSort();

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 优化版快速排序
     * @param start
     * @param end
     */
    public static void sortByRecursion(int start, int end) {
        if (start < end) {
            int index = getIndex(start, end);
            sortByRecursion(start, index - 1);
            sortByRecursion(index + 1, end);
        }
    }

    public static int getIndex(int start, int end) {
        int index = start;
        int max = array[end];

        for (int i = start; i < end; i++) {
            if (array[i] <= max) {
                swap(index, i);
                index++;
            }
        }
        swap(index, end);
        return index;
    }

    public static void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * 复杂版快速排序
     * @param start
     * @param end
     */
    public static void sortByRecursion2(int start, int end) {

        if (start < end) {
            int startTemp = start;
            int endTemp = end;

            int standard = array[startTemp];

            while (startTemp < endTemp) {
                // 从右边找出比基础元素小的元素，跳出循环
                while (startTemp < endTemp && array[endTemp] >= standard) {
                    endTemp--;
                }

                if (startTemp < endTemp) {
                    array[startTemp++] = array[endTemp];
                }

                // 从左边找出比基准元素大的元素，跳出循环
                while (startTemp < endTemp && array[startTemp] <= standard) {
                    startTemp++;
                }

                if (startTemp < endTemp) {
                    array[endTemp--] = array[startTemp];
                }
            }
            array[startTemp] = standard;
            sortByRecursion2(start, startTemp - 1);
            sortByRecursion2(startTemp + 1, end);
        }

    }

    /**
     * 插入排序
     */
    public static void insertSort() {

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

    /**
     * 冒泡排序
     */
    public static void bubbleSort() {
        for (int i = 0; i < array.length - 1;i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public static void selectionSort() {
        for (int i = 0; i < array.length; i++) {

            int minIndex = i;

            for (int j = i; j < array.length; j++) {

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(minIndex, i);
            }

        }
    }

}
