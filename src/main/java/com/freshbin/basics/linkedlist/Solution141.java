package com.freshbin.basics.linkedlist;

import java.util.HashSet;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * @author freshbin
 * @date 2020/5/3 14:37
 */
public class Solution141 {

    /**
     * 思路：遍历链表，用散列表保存遍历过的值
     * 当链表值与散列表值一致，说明出现环，退出
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        HashSet hashSet = new HashSet();
        while(head != null) {
            if(hashSet.contains(head)) {
                return true;
            }
            hashSet.add(head);
            head = head.next;
        }

        return false;
    }

    /**
     * 快慢指针法，当快指针与慢指针相等，说明有环
     * @param head
     * @return
     */
    public boolean hasCycle01(ListNode head) {
        if(head == null) {
            return false;
        }

        ListNode slowNode = head;
        ListNode fastNode = head.next;
        if(slowNode == fastNode) {
            return true;
        }
        while(fastNode != null && fastNode.next != null) {
            if(slowNode == fastNode) {
                return true;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        return false;
    }
}
