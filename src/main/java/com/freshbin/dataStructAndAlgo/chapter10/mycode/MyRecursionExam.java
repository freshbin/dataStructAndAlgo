package com.freshbin.dataStructAndAlgo.chapter10.mycode;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 递归
 * @author freshbin
 * @date 2020/4/14 22:20
 */
public class MyRecursionExam {
    private HashMap<Integer, BigDecimal> cacheMap = new HashMap();
    /**
     * 跳台阶问题，每次只能跳一个台阶或者两个台阶，跳上第n个台阶有多少种跳法
     * 思路：第一次跳时，有两类跳法，
     * 第一类：跳一个，那么就剩下n-1个要跳，
     * 第二类：跳两个，那么就剩下n-2个要跳。
     * 所以，n个台阶的跳法=f(n-1) + f(n-2)
     * 则，f(n) = f(n-1) + f(n-2)，终止条件为n=1或者n=2
     * @param n
     * @return
     */
    public BigDecimal jumpStep(int n) {
        if(n == 0) return new BigDecimal(1);
        if(n == 1) return new BigDecimal(1);
        if(n == 2) return new BigDecimal(2);
        if(cacheMap.containsKey(n)) {
            return cacheMap.get(n);
        }
        BigDecimal sum = jumpStep(n - 1).add(jumpStep(n - 2));
        cacheMap.put(n, sum);
        return sum;
    }

    /**
     * leetcode 1533
     * @param n
     * @return
     */
    public int jumpStep01(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int a = 1, b = 2, sum = 1;
        for(int i = 3; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return sum;
    }

    public static void main(String[] arg) {
        // 跳台阶
        MyRecursionExam myRecursionExam = new MyRecursionExam();
//        int n = 5;
        int n = 44;
        System.out.println("跳上第" + n + "个台阶的跳法有：" + myRecursionExam.jumpStep(n));
        System.out.println("跳上第" + n + "个台阶的跳法有：" + myRecursionExam.jumpStep01(n));
    }

}
