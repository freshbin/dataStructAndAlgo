package com.freshbin.dailyTopic.month5.firstTen;

import java.util.ArrayList;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 *
 * @author freshbin
 * @date 2020/5/9 9:03
 */
public class Solution69 {
    public int x;
    public String y;
    /**
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x == 0) {
            return 0;
        }
        int ans = (int)Math.exp(0.5*Math.log(x));
        return (long)(ans+1)*(ans+1) <= x ? (ans+1) : ans;
    }

    public int mySqrt01(int x) {
        int low = 0, high = x, ans = 0;
        while(low <= high) {
            int middle = low + (high-low)/2;
            long square = (long)middle*middle;
            if(square == x) {
                return middle;
            } else if(square < x) {
                ans = middle;
                low = middle+1;
            } else {
                high = middle-1;
            }
        }
        return ans;
    }

    public static void main(String[] arg) {
        int x = 2147395599;
        Solution69 solution69 = new Solution69();
        System.out.println(solution69.mySqrt(x));

        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        String s3 = s1.intern();
        String s4 = s1.intern();
        System.out.println(s3 == s4);           // true
        String s5 = s2.intern();
        System.out.println(s3 == s5);           // true

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
    }

    public int hashCode() {
        int result = 17;
        result = 31*result + x;
        result = 31*result + y.hashCode();
        return result;
    }
}
