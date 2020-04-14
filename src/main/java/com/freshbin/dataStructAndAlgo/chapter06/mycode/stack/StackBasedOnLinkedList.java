package com.freshbin.dataStructAndAlgo.chapter06.mycode.stack;

/**
 * 使用链表实现栈
 * @author freshbin
 * @date 2020/4/14 9:45
 */
public class StackBasedOnLinkedList {
    private Node headNode;

    public StackBasedOnLinkedList() {
    }


    public StackBasedOnLinkedList(String value) {
        this.headNode = new Node(value);
    }

    public Node getTop() {
        return this.headNode;
    }

    public void push(String value) {
        Node newNode = new Node(value);
        if(this.headNode == null) {
            this.headNode = newNode;
            return;
        }

        newNode.next = this.headNode;
        this.headNode = newNode;
    }

    public String pop() {
        if(this.headNode == null) {
            System.out.println("栈空!");
            return null;
        }

        String returnValue = this.headNode.data;
        this.headNode = this.headNode.next;
        return returnValue;
    }

    public void clear() {
        this.headNode = null;
    }

    public class Node {
        private String data;
        private Node next;

        public Node(String value) {
            this.data = value;
            this.next = null;
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

    public static void main(String[] arg) {
        String[] paramDatas = new String[4];
        for(int i = 0; i < paramDatas.length; i++) {
            paramDatas[i] = "param" + i;
        }

        StackBasedOnLinkedList stackBasedOnLinkedList = new StackBasedOnLinkedList();
        System.out.println("出栈数据：" + stackBasedOnLinkedList.pop());
        stackBasedOnLinkedList.push("test");
        System.out.println("出栈数据：" + stackBasedOnLinkedList.pop());
        System.out.println("出栈数据：" + stackBasedOnLinkedList.pop());

        for(int i = 0; i < paramDatas.length; i++) {
            stackBasedOnLinkedList.push("test" + i);
        }

        System.out.print("所有数据出栈，数据：");
        String value = stackBasedOnLinkedList.pop();
        while(value != null) {
            System.out.print(value + " ");
            value = stackBasedOnLinkedList.pop();
        }
    }
}
