package com.cmazxiaoma.alibaba.skip;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/2/15 21:04
 */
public class SkipListTest {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        skipList.insert(1);
        skipList.insert(3);
        skipList.insert(4);
        skipList.insert(5);
        skipList.insert(7);
        skipList.insert(8);
        skipList.insert(9);
        skipList.insert(10);
        skipList.insert(13);
        skipList.insert(16);
        skipList.insert(18);

//        for (int i = 3; i <= 1000; i++) {
//            int a = i - 1;
//
//            if ((a&1) == 0) {
//                skipList.insert(i);
//            }
//        }

        skipList.printAll();
//        long start = System.currentTimeMillis();
//        System.out.println(skipList.find(8));
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    public static class SkipList {
        private static final int MAX_LEVEL = 1000;
        private int levelCount = 1;
        private Node head = new Node();
        private ThreadLocalRandom random = ThreadLocalRandom.current();

        public Node find(int value) {
            Node p = head;
            for (int i = levelCount -1; i >= 0; i--) {
                while (p.forwards[i] != null && p.forwards[i].data < value) {
                    p = p.forwards[i];
                }
            }
            if (p.forwards[0] != null && p.forwards[0].data == value) {
                return p.forwards[0];
            }
            return null;

        }

        public void insert(int value) {
            Node p = head;
            int level = randomLevel();
            Node node = new Node();
            node.data = value;
            node.maxLevel = level;

            Node[] update = new Node[level];

            for (int i = level - 1; i >= 0; i--) {
                while (p.forwards[i] != null && p.forwards[i].data < value) {
                    p = p.forwards[i];
                }
                update[i] = p;
            }

            for (int i = 0; i < level; i++) {
                node.forwards[i] = update[i].forwards[i];
                update[i].forwards[i] = node;
            }

            if (levelCount < level) {
                levelCount = level;
            }

        }

        public void delete(int value) {
            Node[] deleteNode = new Node[MAX_LEVEL];
            Node p = head;
            for (int i = levelCount - 1; i >= 0; i--) {
                while (p.forwards[i] != null && p.forwards[i].data < value ) {
                    p = p.forwards[i];
                }
                deleteNode[i] = p;
            }

            if (p.forwards[0] != null && p.forwards[0].data == value) {
                for (int i = levelCount - 1; i >= 0; i--) {
                    if (deleteNode[i] != null && deleteNode[i].forwards[i]
                            .data == value) {
                        deleteNode[i].forwards[i] = deleteNode[i]
                                .forwards[i].forwards[i];
                    }
                }
            }
        }

        public void printAll() {
            Node p = head;
            while (p.forwards[0] != null) {
                System.out.println(p.forwards[0] + "");
                p = p.forwards[0];
            }
            System.out.println("");
        }

        public int randomLevel() {
            int level = 0;
            for (int i = 0; i < MAX_LEVEL; i++) {
                if (random.nextInt() % 2 == 1) {
                    level ++;
                }
            }
            return level;
        }

        class Node {
            private int data;
            private Node[] forwards = new Node[MAX_LEVEL];
            private int maxLevel;

            @Override
            public String toString() {
                return "Node{" +
                        "data=" + data +
                        ", maxLevel=" + maxLevel +
                        '}';
            }
        }
    }
}
