package com.freshbin.dataStructAndAlgo.chapter06.mycode.queue;

/**
 * 链表实现队列
 * @author freshbin
 * @date 2020/4/14 17:32
 */
public class QueueBasedOnLinkedList {
    private Node headNode;
    private Node tailNode;

    public QueueBasedOnLinkedList() {
    }

    public void enqueue(String value) {
        System.out.println("准备加入队列的数据：" + value);
        Node newNode = new Node(value);
        if(tailNode == null) {
            tailNode = newNode;
            headNode = newNode;
            return;
        }
        tailNode.next = newNode;
        tailNode = tailNode.next;
    }

    public String dequeue() {
        if(headNode == null) {
            System.out.println("队列为空");
            return null;
        }

        String returnData = headNode.data;
        headNode = headNode.next;
        System.out.println("准备出队的数据：" + returnData);
        return returnData;
    }

    public static void main(String[] arg) {
        String a = "a";
        String b = "b";
        QueueBasedOnLinkedList queueBasedOnLinkedList = new QueueBasedOnLinkedList();
        queueBasedOnLinkedList.dequeue();
        queueBasedOnLinkedList.enqueue(a);
        queueBasedOnLinkedList.enqueue(b);
        queueBasedOnLinkedList.dequeue();
        queueBasedOnLinkedList.dequeue();
        queueBasedOnLinkedList.dequeue();
    }

    public class Node {
        private String data;
        private Node next;

        public Node() {

        }

        public Node(String value) {
            this.data = value;
        }

        public String getData() {
            return this.data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
