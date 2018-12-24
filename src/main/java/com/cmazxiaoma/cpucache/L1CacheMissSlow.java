package com.cmazxiaoma.cpucache;

import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/21 12:57
 */
public class L1CacheMissSlow {

    private static final int time = 10;

    private static final int row = 1024 * 1024;

    private static final int column = 6;

    // long占8个字节
    private static long[][] longs;

    public static void main(String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        longs = new long[row][];

        for (int i = 0; i < row; i++) {
            longs[i] = new long[column];

            for (int j = 0; j < column; j++) {
                longs[i][j] = 0L;
            }
        }
        System.out.println("starting....");

        long sum = 0L;

        for (int t = 0; t < time; t++) {

            final long start = System.nanoTime();

            //slow
            for (int j = 0; j < column; j++) {
                for (int i = 0; i < row; i++) {
                    sum += longs[i][j];
                }
            }

            //fast
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < column; j++) {
//                    sum += longs[i][j];
//                }
//            }
            System.out.println("t=" + t + ",const=" + (System.nanoTime() - start) + "ns");
        }

        TimeUnit.SECONDS.sleep(1);

    }
}
