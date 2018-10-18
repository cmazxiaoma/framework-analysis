package com.cmazxiaoma.concurrent.blockingqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/27 18:11
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        List<PriorityTask> priorityTaskList = new ArrayList<>();

        priorityTaskList.add(new PriorityTask(5));
        priorityTaskList.add(new PriorityTask(4));
        priorityTaskList.add(new PriorityTask(3));
        priorityTaskList.add(new PriorityTask(2));
        priorityTaskList.add(new PriorityTask(1));

        Collections.sort(priorityTaskList);
        System.out.println(priorityTaskList.toString());

        PriorityBlockingQueue<PriorityTask> blockingQueue = new PriorityBlockingQueue<>(5);
        blockingQueue.put(new PriorityTask(5));
        blockingQueue.put(new PriorityTask(3));
        blockingQueue.put(new PriorityTask(4));
        blockingQueue.put(new PriorityTask(1));
        blockingQueue.put(new PriorityTask(2));


        System.out.println("take:" + blockingQueue.take());
        System.out.println("take:" + blockingQueue.take());
        System.out.println("take:" + blockingQueue.take());
        System.out.println("take:" + blockingQueue.take());
        System.out.println("take:" + blockingQueue.take());
        System.out.println("take:" + blockingQueue.take());



        BlockingQueue<PriorityTask> arrayBlockingQueue = new ArrayBlockingQueue<PriorityTask>(1);
        arrayBlockingQueue.put(priorityTaskList.get(0));
//        arrayBlockingQueue.put(priorityTaskList.get(1));
//        arrayBlockingQueue.put(priorityTaskList.get(2));
//        arrayBlockingQueue.put(priorityTaskList.get(3));
//        arrayBlockingQueue.put(priorityTaskList.get(4));

        System.out.println(arrayBlockingQueue.toString());

        BlockingQueue<PriorityTask> linkedBlockingQueue = new LinkedBlockingQueue<>(1);
        linkedBlockingQueue.put(priorityTaskList.get(0));
        linkedBlockingQueue.put(priorityTaskList.get(1));
        linkedBlockingQueue.put(priorityTaskList.get(2));
        linkedBlockingQueue.put(priorityTaskList.get(3));
        linkedBlockingQueue.put(priorityTaskList.get(4));

        System.out.println(linkedBlockingQueue.toString());

    }
}
