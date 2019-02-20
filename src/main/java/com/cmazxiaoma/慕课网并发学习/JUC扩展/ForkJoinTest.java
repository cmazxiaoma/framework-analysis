package com.cmazxiaoma.慕课网并发学习.JUC扩展;

import java.util.concurrent.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/16 23:06
 */
public class ForkJoinTest extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任何足够小就计算任务
        boolean canCompute = (end - start) <= threshold;

        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值, 就分裂成2个子任务计算
            int middle = (start + end) / 2;

            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待2个任务执行结束后合并结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;

        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTest forkJoinTest = new ForkJoinTest(1, 100);

        Future<Integer> result = forkJoinPool.submit(forkJoinTest);

        System.out.println("result:" + result.get());

        /**
         * 1-----ForkJoinPool 使用submit 或 invoke 提交的区别：invoke是同步执行，
         *      调用之后需要等待任务完成，才能执行后面的代码；submit是异步执行，
         *      只有在Future调用get的时候会阻塞。
           2-----这里继承的是RecursiveTask，还可以继承RecursiveAction。
                前者适用于有返回值的场景，而后者适合于没有返回值的场景
           3-----其实这里执行子任务调用fork方法并不是最佳的选择，最佳的选择是invokeAll方法。

         */
    }
}
