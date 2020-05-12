package com.freshbin.dailyTopic.month5.twoTen;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 *
 * @author freshbin
 * @date 2020/5/12 22:25
 */
public class MinStack {
    private Node head;
    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        if(head == null) {
            head = new Node(x, x);
        } else {
            int miniVal = Math.min(x, head.miniVal);
            Node newNode = new Node(x, miniVal);
            newNode.next = head;
            head = newNode;
        }
    }

    public void pop() {
        if(head == null) {
            return;
        }
        head = head.next;
    }

    public int top() {
        if(head == null) {
            return -1;
        }
        return head.val;
    }

    public int getMin() {
        if(head == null) {
            return -1;
        }
        return head.miniVal;
    }

    class Node {
        protected int val;
        protected Node next;
        protected int miniVal;
        Node(int val, int miniVal) {
            this.val = val;
            this.miniVal = miniVal;
        }
    }
}
