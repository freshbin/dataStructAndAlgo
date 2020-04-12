package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.LRU;

/**
 * @author freshbin
 * @date 2020/4/11 21:59
 */
public class MyNode {
    public String token;
    public MyNode next;

    public MyNode(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MyNode getNext() {
        return this.next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "token='" + token + '\'' +
                ", next=" + next +
                '}';
    }
}
