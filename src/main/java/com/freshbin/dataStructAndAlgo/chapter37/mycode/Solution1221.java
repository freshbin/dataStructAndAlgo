package com.freshbin.dataStructAndAlgo.chapter37.mycode;

import java.util.HashMap;

/**
 * leetcode 1221题
 *
 * 描述：在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
 * 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 * 返回可以通过分割得到的平衡字符串的最大数量。
 *
 * 思路：使用一个hashMap存储"L"和"R"两个key，value都初始为0
 * 遍历字符串，将当前字符在hashMap中的value+1，当L和R的value相等的时候，将两个value清空，同时最大数量+1。
 *
 * @author freshbin
 * @date 2020/4/24 15:08
 */
public class Solution1221 {
    public int balancedStringSplit(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }

        HashMap<Character, Integer> tempMap = new HashMap<>();
        tempMap.put('L', 0);
        tempMap.put('R', 0);
        int maxCount = 0;
        for(int i = 0; i < s.length(); i++) {
            int LCount = tempMap.get('L');
            int RCount = tempMap.get('R');
            char currentChar = s.charAt(i);
            if(currentChar == 'L') {
                LCount = LCount+1;
                tempMap.put('L', LCount);
            } else {
                RCount = RCount+1;
                tempMap.put('R', RCount);
            }

            if(LCount == RCount) {
                tempMap.put('L', 0);
                tempMap.put('R', 0);
                maxCount++;
            }

        }
        return maxCount;
    }

    /**
     * leetcode大佬们的代码
     * @param s
     * @return
     */
    public int balancedStringSplit01(String s) {
        int num = 0;
        int res = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == 'L')
                num++;
            else
                num--;
            if(num == 0)
                res++;
        }
        return res;
    }

    public static void main(String[] arg) {
        String s = "RLRRRLLRLL";
        Solution1221 solution1221 = new Solution1221();
        System.out.println(solution1221.balancedStringSplit(s));
    }
}
