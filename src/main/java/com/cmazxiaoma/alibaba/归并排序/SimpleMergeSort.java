package com.cmazxiaoma.alibaba.归并排序;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/2 17:32
 */
public class SimpleMergeSort {

    public static int a[] = {1, 3, 5, 7, 9, 11};
    public static int b[] = {2, 4, 6, 8, 10, 12, 14};

    public static int[] sort() {
        int sizeA = a.length;
        int sizeB = b.length;
        int sizeC = sizeA + sizeB;
        int[] c = new int[sizeC];

        int aIndex = 0, bIndex = 0, cIndex = 0;

        while (aIndex < sizeA && bIndex < sizeB) {
            if (a[aIndex] < b[bIndex]) {
                c[cIndex++] = a[aIndex++];
            } else {
                c[cIndex++] = b[bIndex++];
            }
        }


        while (aIndex < sizeA) {
            c[cIndex++] = a[aIndex++];
        }

        while (bIndex < sizeB) {
            c[cIndex++] = b[bIndex++];
        }

        return c;
    }

    public static void print(int[] c) {
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }

    public static void main(String[] args) {
        print(sort());
    }
}
