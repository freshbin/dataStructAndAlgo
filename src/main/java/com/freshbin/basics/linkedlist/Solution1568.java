package com.freshbin.basics.linkedlist;

/**
 * 面试题18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 *
 * 示例 1:
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 *
 * 示例 2:
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * @author freshbin
 * @date 2020/5/2 15:43
 */
public class Solution1568 {
    /**
     * 思路：遍历链表，如果当前节点的下一节点值与目标值一致，那么将当前节点的指针指向该节点的下下一节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode currentNode = head;
        if(currentNode == null) {
            return null;
        }
        if(currentNode.val == val) {
            return currentNode.next;
        }
        while(currentNode.next != null) {
            if(currentNode.next.val == val) {
                currentNode.next = currentNode.next.next;
            }
            currentNode = currentNode.next;
        }
        return head;
    }
}
