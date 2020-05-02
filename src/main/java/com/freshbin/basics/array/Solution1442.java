package com.freshbin.basics.array;

import java.util.HashMap;

/**
 * 面试题 01.01. 判定字符是否唯一
 *
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 *
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 *
 * 限制：
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 *
 * @author freshbin
 * @date 2020/5/1 11:18
 */
public class Solution1442 {

    /**
     * 思路：遍历字符串，使用hashMap保存每一个字符，如果该字符已经存在，直接false
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        HashMap<Character, Integer> keyMap = new HashMap<>();
        for(int i = 0; i < astr.length(); i++) {
            if(keyMap.containsKey(astr.charAt(i))) {
                return false;
            }
            keyMap.put(astr.charAt(i), 1);
        }

        return true;
    }

    public static void main(String[] arg) {
        String test = "abca";
        Solution1442 solution1442 = new Solution1442();
        System.out.println(solution1442.isUnique(test));
    }
}
