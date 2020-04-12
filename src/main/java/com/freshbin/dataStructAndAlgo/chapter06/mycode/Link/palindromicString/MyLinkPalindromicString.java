package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.palindromicString;

import com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.LRU.MyNode;

/**
 * 使用数组实现回文字符串校验
 *
 * 思路：
 * 1、首先将字符串按顺序写进链表1，再把字符串按逆序即从length-1到0的顺序写进链表2
 * 2、遍历链表1，从头结点遍历到中节点。
 * 使用currentIndex从0开始记录当前遍历的索引，使用middleIndex记录中节点的索引
 * 遍历方法内比较链表1和链表2的值是否一致，不一致则退出循环，表示不是回文数，否则是回文数。
 * 遍历一次currentIndex就+1，middeleIndex的值为(0+length)/2，length为字符串长度
 *
 * @author freshbin
 * @date 2020/4/12 11:28
 */
public class MyLinkPalindromicString {


    public static Boolean isPalindromicString(String target) {
        int currentIndex = 0;
        int middeleIndex = (target.length()) / 2;

        MyLink myNode01 = initMyLink01(target);
        MyLink myNode02 = initMyLink02(target);

        displayNode(myNode01);
        displayNode(myNode02);

        MyNode firstNode01 = myNode01.getFirstNode();
        MyNode firstNode02 = myNode02.getFirstNode();
        while(currentIndex < middeleIndex && firstNode01 != null) {
            if(!firstNode01.token.equals(firstNode02.token)) {
                return false;
            }

            firstNode01 = firstNode01.next;
            firstNode02 = firstNode02.next;
            currentIndex++;
        }
        return true;
    }

    private static void displayNode(MyLink myNode) {
        System.out.print("链表内容：");
        MyNode firstNode = myNode.getFirstNode();
        while(firstNode != null) {
            System.out.print(firstNode.token + "->");
            firstNode = firstNode.next;
        }
        System.out.println();
    }

    /**
     * 字符串逆序写入链表
     * @param target
     * @return
     */
    private static MyLink initMyLink02(String target) {
        MyLink myLink = new MyLink();
        // 因为该链表每次都是从头结点插入，所以这里字符串从后到前进行插入
        for(int i = 0; i < target.length(); i++) {
            myLink.addLink(String.valueOf(target.charAt(i)));
        }

        return myLink;
    }

    /**
     * 字符串顺序写入链表
     * @param target
     * @return
     */
    private static MyLink initMyLink01(String target) {
        MyLink myLink = new MyLink();
        // 因为该链表每次都是从头结点插入，所以这里字符串从后到前进行插入
        for(int i = target.length() - 1; i >= 0; i--) {
            myLink.addLink(String.valueOf(target.charAt(i)));
        }

        return myLink;
    }

    public static void main(String[] arg) {
        String s1 = "a";
        String s2 = "ab";
        String s3 = "abc";
        String s4 = "abcba";
        String s5 = "abcbabcba";

        System.out.println("a:" + isPalindromicString(s1));
        System.out.println("ab:" + isPalindromicString(s2));
        System.out.println("abc:" + isPalindromicString(s3));
        System.out.println("abcba:" + isPalindromicString(s4));
        System.out.println("abcbabcba:" + isPalindromicString(s5));
    }
}
