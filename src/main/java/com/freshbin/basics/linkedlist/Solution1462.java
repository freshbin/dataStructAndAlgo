package com.freshbin.basics.linkedlist;

/**
 * 面试题 02.03. 删除中间节点
 *
 * 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。
 *
 * 示例：
 * 输入：单向链表a->b->c->d->e->f中的节点c
 * 结果：不返回任何数据，但该链表变为a->b->d->e->f
 *
 * @author freshbin
 * @date 2020/5/3 11:16
 */
public class Solution1462 {
    /**
     * 思路：用两个指针遍历链表，快指针每次都两步，慢指针每次走一步，
     * 如果快指针下一节点或者下下一节点为空，那么就说明快指针走到尽头了
     * 这时候慢指针的下一指针中间节点
     *
     * 提交之后发现，神他妈删除中间节点，这踏马入参原来是要删除的节点，
     * 那踏马这不是删除当前节点吗？
     *
     * 将后面节点的值赋到当前节点，然后把当前节点指针指向后节点的下一节点即可
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        /*if(node == null || node.next == null || node.next.next == null) {
            return;
        }

        ListNode slowNode = node;
        ListNode fastNode = node.next;

        while(fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        // 删除中间节点
        slowNode.next = slowNode.next.next;*/

        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] arg) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        Solution1462 solution1462 = new Solution1462();
        solution1462.deleteNode(listNode.next.next);

        while(listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
    }
}
