package com.freshbin.basics.array;

/**
 * 面试题29. 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * -
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author freshbin
 * @date 2020/5/1 14:05
 */
public class Solution1577 {
    /**
     * 思路：顺时针打印，所以流程一直都是从左到右，从上到下，从右到左，从下到上
     *  ps:一开始想到了这样处理，可是在处理边界值的时候，写得乱七八糟，结果写了一两个小时
     *  而且一运行还是错的，当时就崩溃了，于是以为这种想法不太行，
     *  想去看看有没有更巧妙的解法，看了大佬的题解，发现也是这个思路，
     *  再一看边界处理，惊呼，还能这么简单，代码还这么简洁
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int down = matrix.length - 1;
        int[] passArray = new int[matrix.length*matrix[0].length];
        int index = 0;

        while(true) {
            for(int i = left; i <= right; i++) {
                passArray[index++] = matrix[top][i];
            }
            if(++top > down) {
                break;
            }
            for(int i = top; i <= down; i++) {
                passArray[index++] = matrix[i][right];
            }
            if(--right < left) {
                break;
            }
            for(int i = right; i >= left; i--) {
                passArray[index++] = matrix[down][i];
            }
            if(--down < top) {
                break;
            }
            for(int i = down; i >= top; i--) {
                passArray[index++] = matrix[i][left];
            }
            if(++left > right) {
                break;
            }
        }
        return passArray;
    }

    public static void main(String[] arg) {
        int[][] material = {{1,2,3},{4,5,6},{7,8,9}};
        Solution1577 solution1577 = new Solution1577();
        int[] array = solution1577.spiralOrder(material);
        for(int i : array) {
            System.out.print(i + " ");
        }
    }
}
