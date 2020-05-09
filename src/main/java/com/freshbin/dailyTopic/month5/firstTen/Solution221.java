package com.freshbin.dailyTopic.month5.firstTen;

/**
 * 221. 最大正方形
 * <p>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 *
 * @author freshbin
 * @date 2020/5/8 10:15
 */
public class Solution221 {
    /**
     * 思路：拿一个最大的正方形去和矩阵对比，将正方形盖在矩阵上，从矩阵的左边向右边移动，一行行往下移动比较。
     * 后来发现写得乱七八糟，看完官方解法，顿时觉得智商被碾压，使用动态规划，
     * 每个格子的左边，上边，左上三部分最小正方形边长+1即是该位置可组成正方形的边长
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int maxSquare = 0;
        int maxSide = 0;
        int[][] dp = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == '1') {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    public static void main(String[] arg) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        Solution221 solution221 = new Solution221();
        System.out.println(solution221.maximalSquare(matrix));
    }
}
