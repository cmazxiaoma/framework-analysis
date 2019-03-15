package com.cmazxiaoma.alibaba.归并排序;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/15 10:44
 */
public class MergeTest {

    private static int[] arrayA = { 23, 47, 81, 95 };
    private static int[] arrayB = { 7, 14, 39, 55, 62, 74 };
    private static int[] arrayC = new int[10];

    public static void merge() {
        int aIndex = 0,bIndex = 0,cIndex = 0;
        int aSize = arrayA.length;
        int bSize = arrayB.length;

        while (aIndex < aSize && bIndex < bSize) {
            if (arrayA[aIndex] < arrayB[bIndex]) {
                arrayC[cIndex ++] = arrayA[aIndex++];
            } else {
                arrayC[cIndex ++] = arrayB[bIndex ++];
            }
        }

        while (aIndex < aSize) {
            arrayC[cIndex ++] = arrayA[aIndex++];
        }

        while (bIndex < bSize) {
            arrayC[cIndex++] = arrayB[bIndex++];
        }

        for (int i = 0; i < arrayC.length; i++) {
            System.out.println(arrayC[i]);
        }
    }

    public static void main(String[] args) {
        merge();
    }
}

