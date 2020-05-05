package com.freshbin.dailyTopic.month5.firstTen;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author freshbin
 * @date 2020/5/2 8:17
 */
public class Solution3 {
    /**
     * 思路：遍历字符串，循环判断每个字符是否存在map中，如果存在就继续后面的遍历
     * 当所有字符循环完毕直接退出，这时候是最长的
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int maxNum = 0;
        for(int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> charMap = new HashMap<>();
            int num = 0;
            int currentIndex = i;
            while(currentIndex < s.length()) {
                if(charMap.containsKey(s.charAt(currentIndex))) {
                    // 下标i之后的数有重复，直接遍历i的下一个，这里好像可以优化，但是感觉好像会很复杂，就不优化了
                    break;
                }
                charMap.put(s.charAt(currentIndex), 1);
                currentIndex++;
                num++;
                maxNum = maxNum > num ? maxNum : num;
            }

            if(currentIndex >= s.length()) {
                // 下标i之后的数都没有重复的，直接返回。
                return maxNum;
            }
        }
        return maxNum;
    }

    /**
     * 优化解法，滑动窗口
     *
     * @param s
     * @return
     */
    public int slideWindows(String s) {
        int maxNum = 0;
        int startIndex = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                startIndex = Math.max(startIndex, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i+1);
            maxNum = Math.max(maxNum, i-startIndex+1);
        }
        return maxNum;
    }

    public static void main(String[] arg) {
        String s = "pwwkew";
        Solution3 solution = new Solution3();
        System.out.println(solution.lengthOfLongestSubstring(s));
        System.out.println(solution.slideWindows(s));
    }
}
