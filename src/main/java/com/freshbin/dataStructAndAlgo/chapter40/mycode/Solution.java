package com.freshbin.dataStructAndAlgo.chapter40.mycode;

/**
 * @author freshbin
 * @date 2020/4/28 20:01
 */
public class Solution {
    public int waysToStep(int n) {
        int[] sum = new int[n];
        if(n == 0) {
            return 0;
        }
        sum[0] = 1;
        if(n == 1) {
            return 1;
        }
        sum[1] = 2;
        if(n == 2) {
            return 2;
        }
        sum[2] = 4;
        if(n == 3) {
            return 4;
        }

        for(int i = 3; i < n; i++) {
            sum[i] = ((sum[i-3] + sum[i-2])% 1000000007 + sum[i-1]) % 1000000007;
        }
        return sum[n-1];
    }

    public static void main(String[] arg) {
        Solution solution = new Solution();
        System.out.println(solution.waysToStep(61));
    }
}