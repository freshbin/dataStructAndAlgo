package com.freshbin.basics.linkedlist;

/**
 * 面试题24. 反转链表
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author freshbin
 * @date 2020/5/2 14:53
 */
public class Solution1573 {
    /**
     * 思路：遍历链表，用一个辅助链表将当前链表对象放入辅助链表
     * 辅助链表当前对象的next指针指向上一个对象, 同时将链表与辅助链表指针都一直往后移动
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode listNode = new ListNode(head.val);
        ListNode currentNode = head.next;
        while(currentNode != null) {
            ListNode next = currentNode.next;
            ListNode preNode = listNode;
            currentNode.next = preNode;
            listNode = currentNode;
            currentNode = next;
        }
        return listNode;
    }

    public static void main(String[] arg) {
        Solution1573 solution1573 = new Solution1573();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        solution1573.reverseList(listNode);
    }
}
