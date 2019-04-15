package com.cmazxiaoma.cvte;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/15 11:51
 */
public class MergeSort {
    private static int[] array = {4,2,1,4,67,656};


    public static void recMergeSort(int[] workSpace, int lowBound, int upperBound) {
        if (lowBound < upperBound) {
            int mid = (lowBound + upperBound) / 2;
            recMergeSort(workSpace, lowBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);
            mergeSort(workSpace, lowBound, mid + 1, upperBound);
        }
    }

    public static void mergeSort(int[] workSpace, int lowStr, int highStr, int upperBound) {
        int j = 0;
        int mid = highStr - 1;
        int lowBound = lowStr;
        int n = upperBound - lowBound + 1;

        while (lowStr <= mid && highStr <= upperBound) {
            if (array[lowStr] < array[highStr]) {
                workSpace[j++] = array[lowStr++];
            } else {
                workSpace[j++] = array[highStr++];
            }
        }

        while (lowStr <= mid) {
            workSpace[j++] = array[lowStr++];
        }

        while (highStr <= upperBound) {
            workSpace[j++] = array[highStr++];
        }

        for (j = 0; j < n; j++) {
            array[j + lowBound] = workSpace[j];
        }
    }

    public static void main(String[] args){
        recMergeSort(new int[array.length], 0, array.length - 1);
        display();
    }

    public static void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
