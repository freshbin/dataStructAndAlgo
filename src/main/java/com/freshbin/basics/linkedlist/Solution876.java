package com.freshbin.basics.linkedlist;

/**
 * 876. 链表的中间结点
 *
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * @author freshbin
 * @date 2020/5/3 14:21
 */
public class Solution876 {
    /**
     * 思路：用两个指针遍历链表，快指针每次都两步，慢指针每次走一步，
     * 如果快指针下一节点或者下下一节点为空，那么就说明快指针走到尽头了
     * 这时候慢指针的下一指针就是中间节点
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        if(head.next.next == null) {
            return head.next;
        }
        ListNode slowNode = head;
        ListNode fastNode = head.next;

        while(fastNode.next != null && fastNode.next.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }

        // 慢指针的下一节点就是中间节点
        return slowNode.next;
    }

    public static void main(String[] arg) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        Solution876 solution876 = new Solution876();
        System.out.println(solution876.middleNode(listNode).val);
    }
}
