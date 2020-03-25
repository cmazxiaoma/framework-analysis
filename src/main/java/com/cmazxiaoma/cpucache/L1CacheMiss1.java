package com.cmazxiaoma.cpucache;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/13 15:13
 */
public class L1CacheMiss1 {
    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 62;

    private static long[][] longs;

    /**
     * 7516230000
     *  605615600
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Thread.sleep(10000);
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 0L;
            }
        }
        System.out.println("starting....");

        final long start = System.nanoTime();
        long sum = 0L;
        for (int r = 0; r < RUNS; r++) {
//            for (int j = 0; j < DIMENSION_2; j++) {
//                for (int i = 0; i < DIMENSION_1; i++) {
//                    sum += longs[i][j];
//                }
//            }

            for (int i = 0; i < DIMENSION_1; i++) {
            				for (int j = 0; j < DIMENSION_2; j++) {
            					sum += longs[i][j];
            				}
            			}
        }

        System.out.println(sum);
        System.out.println("duration = " + (System.nanoTime() - start));
    }
}
