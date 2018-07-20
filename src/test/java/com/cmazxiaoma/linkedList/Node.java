package com.cmazxiaoma.linkedList;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/20 9:10
 */
public class Node {
    private Node next;
    private final int value;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public Node(int value) {
        super();
        this.value = value;
        this.next = null;
    }

    public static void prinfLinkedList(Node head) {

        while (head != null) {
            System.out.print(head.getValue() + " ");
            head = head.getNext();
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return "Node [next=" + next + ", value=" + value + "]";
    }
}
