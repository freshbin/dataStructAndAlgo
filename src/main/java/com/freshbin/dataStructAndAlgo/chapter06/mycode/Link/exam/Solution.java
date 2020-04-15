package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.exam;

/**
 * @author freshbin
 * @date 2020/4/14 22:36
 */
class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode returnRevesalNode = null;
        ListNode returnNode = null;
        ListNode newNode = null;
        ListNode newL1Node = null;
        ListNode newL2Node = null;
        if(l1 == null) {
            newNode = l2;
            return newNode;
        }
        if(l2 == null) {
            newNode = l1;
            return newNode;
        }

        while(l1 != null) {
            ListNode next = l1.next;
            l1.next = newL1Node;
            newL1Node = l1;
            l1 = next;
        }
        while(l2 != null) {
            ListNode next = l2.next;
            l2.next = newL2Node;
            newL2Node = l2;
            l2 = next;
        }

        boolean addFlag = false;
        while (newL1Node != null && newL2Node != null) {
            int newValue = newL1Node.val + newL2Node.val;
            if(addFlag) {
                newValue += 1;
                addFlag = false;
            }
            if(newValue >= 10) {
                newValue = newValue%10;
                addFlag = true;
            }

            if(newNode != null) {
                newNode.next = new ListNode(newValue);
                newNode = newNode.next;
            } else {
                newNode = new ListNode(newValue);
                returnRevesalNode = newNode;
            }

            newL1Node = newL1Node.next;
            newL2Node = newL2Node.next;
        }

        while (newL1Node != null) {
            int value = newL1Node.val;
            if(addFlag) {
                value += 1;
                addFlag = false;
            }
            if(value >= 10) {
                value = value % 10;
                addFlag = true;
            }
            newNode.next = new ListNode(value);
            newNode = newNode.next;
            newL1Node = newL1Node.next;
        }

        while (newL2Node != null) {
            int value = newL2Node.val;
            if(addFlag) {
                value += 1;
                addFlag = false;
            }
            if(value >= 10) {
                value = value % 10;
                addFlag = true;
            }
            newNode.next = new ListNode(value);
            newNode = newNode.next;
            newL2Node = newL2Node.next;
        }

        if(addFlag) {
            ListNode listNode = new ListNode(1);
            newNode.next = listNode;
        }

        while (returnRevesalNode != null) {
            ListNode next = returnRevesalNode.next;
            returnRevesalNode.next = returnNode;
            returnNode = returnRevesalNode;
            returnRevesalNode = next;
        }
        return returnNode;
    }

    public static void main(String[] arg) {
        ListNode listNode10 = new ListNode(1);


        ListNode listNode20 = new ListNode(9);
        ListNode listNode21 = new ListNode(9);
        listNode20.next = listNode21;

        ListNode listNode = addTwoNumbers(listNode10, listNode20);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
