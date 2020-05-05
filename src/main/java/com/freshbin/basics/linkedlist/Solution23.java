package com.freshbin.basics.linkedlist;

import java.util.PriorityQueue;

/**
 * 23. 合并K个排序链表
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author freshbin
 * @date 2020/5/3 14:37
 */
public class Solution23 {
    /**
     * 思路：把k个链表的值都放入新数组，对新数组用归并排序或者快速排序，再把新数组的值存入链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }

        // 获取总的数据量
        int numberSum = 0;
        for(ListNode listNode : lists) {
            while(listNode != null) {
                listNode = listNode.next;
                numberSum++;
            }
        }
        // new一个数组存入所有数据
        int[] newArray = new int[numberSum];
        int index = 0;
        for(ListNode listNode : lists) {
            while(listNode != null) {
                newArray[index++] = listNode.val;
                listNode = listNode.next;
            }
        }

        // 对数组排序
        // 归并排序
//        mergeSort(newArray, 0, numberSum-1);
        // 快速排序
        quickSort(newArray, 0, numberSum-1);

        // 将排序后的数组放入链表
        ListNode newNode = new ListNode(-1);
        ListNode currentNode = newNode;
        for(int val : newArray) {
            currentNode.next = new ListNode(val);
            currentNode = currentNode.next;
        }

        return newNode.next;
    }

    private void quickSort(int[] newArray, int start, int end) {
        if(start >= end) {
            return;
        }

        int targetIndex = partition(newArray, start, end);
        quickSort(newArray, start, targetIndex-1);
        quickSort(newArray, targetIndex+1, end);
    }

    private int partition(int[] newArray, int left, int right) {
        int targetVal = newArray[right];
        while(left < right) {
            while(newArray[left] <= targetVal && left < right) {
                left++;
            }
            newArray[right] = newArray[left];
            newArray[left] = targetVal;
            while(newArray[right] >= targetVal && right > left) {
                right--;
            }
            newArray[left] = newArray[right];
            newArray[right] = targetVal;
        }

        return left;
    }

    private void mergeSort(int[] newArray, int start, int end) {
        if(start >= end) {
            return;
        }

        int middle = (start + end) / 2;
        mergeSort(newArray, start, middle);
        mergeSort(newArray, middle+1, end);
        mergeArray(newArray, start, middle, end);
    }

    /**
     * 合并左右两个数组
     * @param newArray
     * @param start
     * @param middle
     * @param end
     */
    private void mergeArray(int[] newArray, int start, int middle, int end) {
        int left = start, right = middle + 1, k = 0;
        int[] tempArr = new int[end-start+1];
        while(left <= middle && right <= end) {
            if(newArray[left] <= newArray[right]) {
                tempArr[k++] = newArray[left++];
            } else {
                tempArr[k++] = newArray[right++];
            }
        }

        while(left <= middle) {
            tempArr[k++] = newArray[left++];
        }

        while(right <= end) {
            tempArr[k++] = newArray[right++];
        }

        int index = 0;
        for(int i = start; i <= end; i++) {
            newArray[i] = tempArr[index++];
        }
    }

    /**
     * 将k个链表头放入小跟堆中，然后再从中把堆顶元素依次取出来，每取一次，都把当前链表的下一个节点放入堆中
     * 测试发现，放堆中的运行时间还真不如 直接把链表数据都放到数组，再对数组排序后扔回链表，
     * @param lists
     * @return
     */
    public ListNode mergeKListsByHeap(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        // 将k个链表的表头放入优先级队列，即小根堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        for(ListNode listNode : lists) {
            if(listNode != null) {
                queue.add(listNode);
            }
        }

        ListNode head = new ListNode(-1);
        ListNode currentNode = head;
        // 依次取出队列元素，即取出堆顶元素
        while(!queue.isEmpty()) {
            currentNode.next = queue.poll();
            currentNode = currentNode.next;
            if(currentNode.next != null) {
                // 将该链表的下一节点放入队列，即放入堆中
                queue.add(currentNode.next);
            }
        }

        return head.next;
    }

}
