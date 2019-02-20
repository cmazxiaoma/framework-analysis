package com.cmazxiaoma.慕课网并发学习.线程安全策略;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/14 7:14
 */
public class VectorTest {

    private static Vector<Long> vector = new Vector<>();

    public static void main(String[] args) {

        for (;;) {
            for (int i = 0; i < 10; i++) {
                vector.add(i + 1L);
            }

            Thread getThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            });

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Iterator<Long> iterator = vector.iterator();
                    while (iterator.hasNext()) {
                        Long cell = iterator.next();
                        iterator.remove();
                    }
                }
            });
            getThread.start();
            removeThread.start();
        }
    }
}
