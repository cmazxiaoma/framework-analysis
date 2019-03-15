package com.cmazxiaoma.alibaba.归并排序;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/15 11:02
 */
public class Merge {

    private static int[] array = {4,2,1,4,67,656};

    public static void recMergeSort(int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {

            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workSpace, lowerBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    public static void merge(int[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (array[lowPtr] < array[highPtr]) {
                workSpace[j++] = array[lowPtr++];
            } else {
                workSpace[j++] = array[highPtr++];
            }
        }

        while (lowPtr <= mid) {
            workSpace[j++] = array[lowPtr++];
        }

        while (highPtr <= upperBound) {
            workSpace[j++] = array[highPtr++];
        }

        for (j = 0; j < n; j++) {
            array[lowerBound + j] = workSpace[j];
        }
    }

    public static void main(String[] args) {
        recMergeSort(new int[array.length], 0, array.length - 1);
        display();
    }

    public static void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
