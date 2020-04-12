package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.reversal;

import com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.LRU.MyNode;
import com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.palindromicString.MyLink;

/**
 * 单链表方式实现翻转单链表
 * 思路：需要添加一个前节点用于辅助
 * 将当前节点的下一节点指向前节点，将前节点和当前节点都往后移动。
 * @author freshbin
 * @date 2020/4/12 15:34
 */
public class MyLinkReversal {
    public static MyNode reversalLink(MyNode node) {
        if(node == null || node.next == null) {
            return node;
        }

        MyNode preNode = null;
        MyNode currentNode = node;
        MyNode nextNode = null;

        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }

        return preNode;
    }

    public static void main(String[] arg) {
        String s0 = "a";
        String s1 = "b";
        String s2 = "c";
        String s3 = "d";

        MyLink myLink = new MyLink();
        myLink.addLink(s0);
        myLink.addLink(s1);
        myLink.addLink(s2);
        myLink.addLink(s3);
        System.out.print("原链表数据:");
        MyNode oldLink = myLink.firstNode;
        while(oldLink != null) {
            System.out.print(oldLink.token + "->");
            oldLink = oldLink.next;
        }

        System.out.println();
        MyNode myNode = reversalLink(myLink.firstNode);
        System.out.print("新链表数据:");
        while(myNode != null) {
            System.out.print(myNode.token + "->");
            myNode = myNode.next;
        }
    }
}
