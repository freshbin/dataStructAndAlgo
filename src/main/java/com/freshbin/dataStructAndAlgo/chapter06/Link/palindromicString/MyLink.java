package com.freshbin.dataStructAndAlgo.chapter06.Link.palindromicString;

import com.freshbin.dataStructAndAlgo.chapter06.Link.LRU.MyNode;

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

    public MyNode getFirstNode() {
        return this.firstNode;
    }

    public void setFirstNode(MyNode firstNode) {
        this.firstNode = firstNode;
    }

}
