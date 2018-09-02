package com.cmazxiaoma.concurrent.node;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/2 18:30
 */
public class NodeTest {

    public static void main(String[] args) {
        Node node = new Node(Thread.currentThread(), new Node(Thread.currentThread(), 1));
        node.nextWaiter = new Node(Thread.currentThread(), -1);

        Node next = node.nextWaiter;
        node.nextWaiter = null;

        System.out.println(next);
    }

    static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;
        static final int CANCELLED =  1;
        static final int SIGNAL    = -1;
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;
        volatile int waitStatus;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;
        Node nextWaiter;

        final boolean isShared() {
            return nextWaiter == SHARED;
        }


        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }

}
