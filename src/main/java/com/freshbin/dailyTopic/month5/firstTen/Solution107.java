package com.freshbin.dailyTopic.month5.firstTen;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 *
 * @author freshbin
 * @date 2020/5/8 15:03
 */
public class Solution107 {
    /**
     * 思路：树的广度搜索
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> returnList = new ArrayList<>();
        if(root == null) {
            return returnList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> currentLevelList = new ArrayList<>();
            int len = queue.size();
            for(int i = 0; i < len; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelList.add(currentNode.val);
                if(currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            returnList.add(0, currentLevelList);

        }
        return returnList;
    }

    public static void main(String[] arg) {
        Solution107 solution107 = new Solution107();
        solution107.levelOrderBottom(null);
    }
}
