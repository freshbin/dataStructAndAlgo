package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.palindromicString;

import com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.LRU.MyNode;

/**
 * 使用链表实现LRU缓存淘汰策略
 * @author freshbin
 * @date 2020/4/11 21:24
 */
public class MyLink {
    public MyNode firstNode;

    public MyLink() {

    }

    /**
     * 每次都从头结点插入
     * @param value
     * @return
     */
    public MyNode addLink(String value) {
        MyNode myNode = new MyNode(value);
        MyNode oldFirstNode = this.firstNode;
        this.firstNode = myNode;
        this.firstNode.next = oldFirstNode;
        return this.firstNode;
    }

    /**
     * 每次都从尾部写入
     * @param value
     * @return
     */
    public MyNode addTailLink(String value) {
        MyNode myNode = new MyNode(value);
        if(this.firstNode == null) {
            this.firstNode = myNode;
            return this.firstNode;
        }

        MyNode nextNode = this.firstNode;
        while(nextNode != null) {
            if(nextNode.next == null) {
                break;
            }
            nextNode = nextNode.next;
        }

        nextNode.next = myNode.next;
        nextNode.next = myNode;

        return this.firstNode;
    }

    public MyNode getFirstNode() {
        return this.firstNode;
    }

    public void setFirstNode(MyNode firstNode) {
        this.firstNode = firstNode;
    }
}
