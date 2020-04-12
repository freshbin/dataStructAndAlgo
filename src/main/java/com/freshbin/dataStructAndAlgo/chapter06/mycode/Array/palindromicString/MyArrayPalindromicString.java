package com.freshbin.dataStructAndAlgo.chapter06.mycode.Array.palindromicString;

/**
 * 使用数组实现回文字符串校验
 *
 * 思路：
 * 1、首先将字符串写进数组
 * 2、从索引0到(0+size)/2(小于该值)处遍历数组，
 * 同时用leftIndex记录从0递增到(0+size)/2（小于该值），用rightIndex记录从size-1递减到(0+size)/2（大于该值）
 * 左右两边同时进行移动，判断左右两边的值是否相等，不相等就退出循环表示不是回文数，否则就是回文数
 * @author freshbin
 * @date 2020/4/12 10:45
 */
public class MyArrayPalindromicString {
    public static Boolean isPalindromicString(String target) {
        if(target == null && target.length() == 0) {
            return false;
        }
        target = target.trim();

        // 写进数组
        String[] targetArray = initArray(target);
        int leftIndex = 0;
        int rightIndex = targetArray.length-1;
        int middleIndex = (0+targetArray.length)/2;

        while(leftIndex < middleIndex) {
            if(!targetArray[leftIndex].equals(targetArray[rightIndex])) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }

        return true;
    }

    private static String[] initArray(String target) {
        String[] arr = new String[target.length()];
        System.out.print("需要判断回文的字符串：");
        for(int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(target.charAt(i));
            System.out.print(arr[i]);
        }
        System.out.println();
        return arr;
    }

    public static void main(String[] arg) {
        String s1 = "a";
        String s2 = "ab";
        String s3 = "abc";
        String s4 = "abcba";
        String s5 = "abcbabcba";

        System.out.println("a:" + isPalindromicString(s1));
        System.out.println("ab:" + isPalindromicString(s2));
        System.out.println("abc:" + isPalindromicString(s3));
        System.out.println("abcba:" + isPalindromicString(s4));
        System.out.println("abcbabcba:" + isPalindromicString(s5));
    }
}
