package com.freshbin.dailyTopic.month5.twoTen;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 *
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2的-2次方 = 1/(2的平方) = 1/4 = 0.25
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 *
 * @author freshbin
 * @date 2020/5/11 20:56
 */
public class Solution50 {
    /**
     * 思路：判断n是否小于0，小于0，则先取绝对值
     * 之后就循环相乘n次x。
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1;
        }
        double result = 1;
        boolean negativeFlag = false;
        if(n < 0) {
            n = -n;
            negativeFlag = true;
        }

        for(int i = 0; i < n; i++) {
            result = result * x;
        }

        if(negativeFlag) {
            result = 1.0 / result;
        }
        return result;
    }

    public static void main(String[] arg) {
        double x = 2;
        int n = 10;
        Solution50 solution50 = new Solution50();
        long startTime = System.currentTimeMillis();
        System.out.println(solution50.myPow(x, n));
        System.out.println("耗时：" + (System.currentTimeMillis()-startTime));
        long startTime1 = System.currentTimeMillis();
        System.out.println(solution50.myPow01(x, n));
        System.out.println("耗时：" + (System.currentTimeMillis()-startTime1));
    }

    public double myPow01(double x, int n) {
        return n >= 0 ? quickMul(x, n) : (1/quickMul(x, -n));
    }

    private double quickMul(double x, int n) {
        if(n == 0) {
            return 1;
        }

        double result = quickMul(x, n/2);
        if(n % 2 == 0) {
            result = result*result;
        } else {
            result = result*result*x;
        }
        return result;
    }
}
