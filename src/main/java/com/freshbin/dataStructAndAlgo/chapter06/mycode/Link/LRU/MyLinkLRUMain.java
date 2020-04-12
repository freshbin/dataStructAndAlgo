package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.LRU;

/**
 * @author freshbin
 * @date 2020/4/11 21:36
 */
public class MyLinkLRUMain {
    public static void main(String[] arg) {
        String token01 = "token01";
        String token02 = "token02";
        String token03 = "token03";
        String token04 = "token04";

        MyLinkLRU myLinkLRU = MyLinkLRU.getMyLinkLRU();
        myLinkLRU.useCacheByToken(token01);
        myLinkLRU.useCacheByToken(token01);

        MyNode node = myLinkLRU.getFirstNode();
        System.out.print("缓存中的值：");
        while (node != null) {
            System.out.print(node.token + "->");
            node = node.next;
        }

        System.out.println();
        myLinkLRU.useCacheByToken(token02);
        myLinkLRU.useCacheByToken(token03);
        myLinkLRU.useCacheByToken(token04);
        myLinkLRU.useCacheByToken(token02);

        MyNode node02 = myLinkLRU.getFirstNode();
        System.out.print("缓存中的值：");
        while (node02 != null) {
            System.out.print(node02.token + "->");
            node02 = node02.next;
        }
    }
}
