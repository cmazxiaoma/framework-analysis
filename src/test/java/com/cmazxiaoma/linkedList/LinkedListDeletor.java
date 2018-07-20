package com.cmazxiaoma.linkedList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/20 9:24
 */
public class LinkedListDeletor {

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        Node head = creator.createdLinkedList(Arrays.asList(2, 1, 2, 3, 2, 5));

        LinkedListDeletor deletor = new LinkedListDeletor();
        Node newHead = deletor.deleteSpecificElement(head, 2);

        //2, 1, 2, 3, 2, 5
        Node.prinfLinkedList(newHead);

        //null
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(new ArrayList<Integer>()), 2));

        //1, 2, 3, 2, 5
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(1, 2, 3, 2, 5)), 2));

        //1, 2, 3, 2, 2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(1, 2, 3, 2, 2)), 2));

        //1, 2, 3, 2, 2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(1, 2, 3, 2, 2)), 2));

        //2, 2, 3, 2, 2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(2, 2, 3, 2, 2)), 1));

        //2, 2, 2, 2, 2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(2, 2, 2, 2, 2)), 2));
        //2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(2)), 2));

        //2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(Arrays.asList(2)), 1));

        //2
        Node.prinfLinkedList(deletor.deleteSpecificElement(creator.createdLinkedList(new ArrayList<Integer>()), 1));
    }

    /**
     * delete linked list all element
     * @param node
     * @return
     */
    public Node delete(Node node) {
        Node head = node;

        while (head != null) {
            head.setNext(null);
            head = head.getNext();
        }

        return head;
    }

//    /**
//     * Linked list is {1, 2, 3, 2, 5}
//     * you should delelte all 2 element
//     */
//    public Node deleteSpecificElement(Node node, int elementValue) {
//        Node preNode = null;
//        Node curNode = node;
//
//        if (node == null) {
//            return null;
//        }
//
//        while (curNode != null) {
//            int value = curNode.getValue();
//
//            if (value == elementValue) {
//                preNode.setNext(curNode.getNext());
//            }
//
//            preNode = curNode;
//            curNode = curNode.getNext();
//        }
//
//        return node;
//    }

    public Node deleteSpecificElement(Node head, int elementValue) {

        while (head != null && head.getValue() == elementValue) {
            head = head.getNext();
        }

        if (head == null) {
            return null;
        }

        Node pre = head;

        while (pre.getNext() != null) {

            if (pre.getNext().getValue() == elementValue) {
                pre.setNext(pre.getNext().getNext());
            } else {
                pre = pre.getNext();
            }
        }

        return head;
    }
}