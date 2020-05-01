package com.freshbin.dailyTopic.month5.one;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author freshbin
 * @date 2020/5/1 9:11
 */
public class Solution {
    private ListNode pHead;

    public Solution() {
    }

    /**
     * 思路：遍历两个链表，比较两个数的大小，
     * 如果链表1小于等于链表2，那么将链表1的数放入新链表，同时链表1的指针后移，
     * 否则把链表2的数放入新链表，同时链表2的指针后移
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        int val1 = l1.val;
        int val2 = l2.val;
        if(val1 <= val2) {
            pHead = new ListNode(val1);
            l1 = l1.next;
        } else {
            pHead = new ListNode(val2);
            l2 = l2.next;
        }

        ListNode currentNode = pHead;
        ListNode nextNode = null;
        while(l1 != null && l2 != null) {
            val1 = l1.val;
            val2 = l2.val;
            int miniVal = 0;
            if(val1 <= val2) {
                miniVal = val1;
                l1 = l1.next;
            } else {
                miniVal = val2;
                l2 = l2.next;
            }
            nextNode = new ListNode(miniVal);
            currentNode.next = nextNode;
            currentNode = nextNode;
        }

        while(l1 != null) {
            nextNode = new ListNode(l1.val);
            currentNode.next = nextNode;
            currentNode = nextNode;
            l1 = l1.next;
        }
        while(l2 != null) {
            nextNode = new ListNode(l2.val);
            currentNode.next = nextNode;
            currentNode = nextNode;
            l2 = l2.next;
        }

        return pHead;
    }

    /**
     * 优化1
     * 直接构造新链表的头结点
     * 如果有个链表还没有遍历完，直接把新链表的next指向该链表即可
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsOptimize(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode nextNode = preHead;
        while(l1 != null && l2 != null) {
            int val1 = l1.val;
            int val2 = l2.val;

            if(val1 <= val2) {
                nextNode.next = l1;
                l1 = l1.next;
            } else {
                nextNode.next = l2;
                l2 = l2.next;
            }
            nextNode = nextNode.next;
        }

        nextNode.next = l1 != null ? l1 : l2;

        return preHead.next;
    }

    public static void main(String[] arg) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);

        solution.mergeTwoListsOptimize(l1, l2);
    }
}
