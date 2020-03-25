package com.cmazxiaoma.concurrent.aqs;

import org.springframework.util.ReflectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/20 13:43
 */
public class MyThreadList {

    private volatile Node head = null;
    private static final Unsafe unsafe;
    private static final long headOffset;

    static {
        try {
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            ReflectionUtils.makeAccessible(constructor);
            unsafe = constructor.newInstance();
            headOffset = unsafe.objectFieldOffset
                    (MyThreadList.class.getDeclaredField("head"));

        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * Node pred = tail;
     if (pred != null) {
     node.prev = pred;
     if (compareAndSetTail(pred, node)) {
     pred.next = node;
     return node;
     }
     }
     * for (;;) {
     Node t = tail;
     if (t == null) { // Must initialize
     if (compareAndSetHead(new Node()))
     tail = head;
     } else {
     node.prev = t;
     if (compareAndSetTail(t, node)) {
     t.next = node;
     return t;
     }
     }
     }
     * @param thread
     * @return
     */
    public boolean insert(Thread thread) {
        Node node = new Node(thread);

        for (;;) {

            Node first = getHead();

            node.setNext(first);

            if (unsafe.compareAndSwapObject(this, headOffset, first, node)) {
                return first == null;
            }
        }
    }

    public Thread pop() {
        Node first = null;

        for (;;) {
            first = getHead();
            Node next = null;

            if (first != null) {
                next = first.getNext();
            }

            if (unsafe.compareAndSwapObject(this, headOffset, first, next)) {
                break;
            }
        }

        return first == null ? null: first.getThread();
    }

    public Node getHead() {
        return this.head;
    }

    private static class Node {

        private volatile Node next;

        private volatile Thread thread;

        public Node(Thread thread) {
            this.thread = thread;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }
    }
}
