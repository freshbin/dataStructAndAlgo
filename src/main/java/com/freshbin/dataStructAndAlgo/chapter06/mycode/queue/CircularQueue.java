package com.freshbin.dataStructAndAlgo.chapter06.mycode.queue;

/**
 * 数组实现循环队列
 *
 * 队列满的条件为：(tail+1)%size=head
 * 队列空的条件为：head=tail
 * tail为当前尾结点索引，head为当前头结点索引，size为队列大小
 * @author freshbin
 * @date 2020/4/14 17:51
 */
public class CircularQueue {
    private Integer size;
    private String[] dataArray;
    private Integer head;
    private Integer tail;

    public CircularQueue() {
        this(3);
    }

    public CircularQueue(int maxSize) {
        this.size = maxSize+1;
        dataArray = new String[this.size];
        head = 0;
        tail = 0;
    }

    public void enquue(String value) {
        if((tail + 1) % size == head) {
            System.out.println("队列满");
            return;
        }

        dataArray[tail] = value;
        tail = (tail + 1) % size;
    }

    public String dequeue() {
        if(tail == head) {
            System.out.println("队空！");
            return null;
        }

        String returnValue = dataArray[head];
        head = (head + 1) % size;
        return returnValue;
    }

    public static void main(String[] arg) {
        CircularQueue circularQueue = new CircularQueue();
        circularQueue.dequeue();
        for(int i = 0; i < 4; i++) {
            circularQueue.enquue("queue" + i);
        }

        System.out.println("出队列元素：");
        String dequeueData = circularQueue.dequeue();
        while (dequeueData != null) {
            System.out.print(dequeueData + " ");
            dequeueData = circularQueue.dequeue();
        }
    }
}
