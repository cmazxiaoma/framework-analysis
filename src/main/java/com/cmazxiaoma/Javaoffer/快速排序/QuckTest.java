package com.cmazxiaoma.Javaoffer.快速排序;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/18 22:56
 */
public class QuckTest {

    public static int[] array = {3123, 43, 4234, 123, 13, 5434, 13, 124, 2112, 10, 5, 1, 0, 424234};

    public static void sort(int start, int end) {
        if (start < end) {
            int left = start;
            int right = end;
            int standrad = array[left];

            while (left < right) {
                while (left < right && array[right] >= standrad) {
                    right --;
                }

                if (left < right) {
                    array[left ++] = array[right];
                }

                while (left < right && array[left] <= standrad) {
                    left ++;
                }

                if (left < right) {
                    array[right --] = array[left];
                }
            }

            array[left] = standrad;
            sort(start, left - 1);
            sort(left + 1, end);
        }
    }

    public static void main(String[] args) {

        sort(0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
