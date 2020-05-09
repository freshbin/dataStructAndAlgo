package com.freshbin.dailyTopic.month5.firstTen;


import java.math.BigDecimal;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * @author freshbin
 * @date 2020/5/5 16:40
 */
public class Solution98 {
    /**
     * 思路：定义一个递归函数，接收当前节点和上界值与下界值，
     * 如果存在上下界，就判断当前值与上下界的大小关系，
     * 当前值小于等于下界值，或者大于等于上界值就退出递归，返回false
     * 继续遍历该节点的右子树，将下界值设为当前节点值，上界不变
     * 继续遍历该节点的左子树，将上界值设为当前节点值，下界不变
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return recurse(root, null, null);
    }

    private boolean recurse(TreeNode root, Integer lower, Integer upper) {
        if(root == null) {
            return true;
        }
        int val = root.val;
        if(lower != null && val <= lower) {
            return false;
        }
        if(upper != null && val >= upper) {
            return false;
        }

        if(!recurse(root.right, val, upper)) {
            return false;
        }
        if(!recurse(root.left, lower, val)) {
            return false;
        }

        return true;
    }

    /**
     * 用中序遍历将数据都取出来，当后一位比前一位小，那么说明不符合题意的二叉查找树
     *
     * @param root
     * @return
     */
    public boolean isValidBST01(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double preNum = -Double.MAX_VALUE;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                // 将根节点与左子树的左子节点都入栈
                stack.push(root);
                root = root.left;
            }
            // 取出左子树
            root = stack.pop();
            int val = root.val;
            if(val <= preNum) {
                return false;
            }
            preNum = val;
            // 右子树入栈
            root = root.right;
        }
        return true;
    }

    public static void main(String[] arg) throws Exception {
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(4.0));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(3.6));
        System.out.println(bigDecimal1.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(bigDecimal1.subtract(bigDecimal2).setScale(2));
    }
}
