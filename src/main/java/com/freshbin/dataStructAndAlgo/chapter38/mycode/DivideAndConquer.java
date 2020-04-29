package com.freshbin.dataStructAndAlgo.chapter38.mycode;

import com.freshbin.dataStructAndAlgo.utils.MyArrayUtil;

/**
 * 分治算法
 * @author freshbin
 * @date 2020/4/27 15:28
 */
public class DivideAndConquer {
    public int num = 0;

    public int count(int[] a) {
        mergeSortCounting(a, 0, a.length-1);
        return num;
    }

    private void mergeSortCounting(int[] a, int start, int end) {
        if(start >= end) {
            return;
        }

        int middle = (start+end)/2;
        mergeSortCounting(a, start, middle);
        mergeSortCounting(a, middle+1, end);
        merge(a, start, middle, end);
    }

    private void merge(int[] a, int start, int middle, int end) {
        int left = start, right = middle+1;
        int[] temp = new int[end-start+1];
        int index = 0;
        while(left <= middle && right <= end) {
            if(a[left] <= a[right]) {
                temp[index++] = a[left++];
            } else {
                num += middle-left+1;
                temp[index++] = a[right++];
            }
        }

        while(left <= middle) {
            temp[index++] = a[left++];
        }

        while(right <= end) {
            temp[index++] = a[right++];
        }

        for(int i = 0; i < end-start+1; i++) {
            a[start+i] = temp[i];
        }
    }

    public static void main(String[] arg) {
        int[] a = {4, 2, 3, 5};
        DivideAndConquer divideAndConquer = new DivideAndConquer();
        System.out.println("数组：");
        MyArrayUtil.display(a);
        System.out.println("逆序对数量为：" + divideAndConquer.count(a));

        System.out.println("=================");
        int[] nums = {-2,1};
        System.out.println("暴力解法最大值：" + divideAndConquer.maxSubArray(nums));
        System.out.println("分治算法最大值：" + divideAndConquer.maxSubArray01(nums));
        System.out.println("动态规划最大值：" + divideAndConquer.maxSubArray02(nums));
    }

    /**
     * leetcode53
     * 给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。
     *
     * 示例：
     * 输入： [-2,1,-3,4,-1,2,1,-5,4]
     * 输出： 6
     * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */

    public int maxSubArray(int[] nums) {
        int maxNum = nums[0];
        for(int i = 0; i < nums.length; i++) {
            int currentMax = nums[i];
            maxNum = nums[i] >= maxNum ? nums[i] : maxNum;
            for(int j = i + 1; j < nums.length; j++) {
                currentMax = nums[j] + currentMax;
                maxNum = currentMax >= maxNum ? currentMax : maxNum;
            }
        }
        return maxNum;
    }

    /**
     * 分治算法
     * @param nums
     * @return
     */
    public int maxSubArray01(int[] nums) {
        return dacMaxSubArray(nums, 0, nums.length-1);
    }

    private int dacMaxSubArray(int[] nums, int left, int right) {
        if(left == right) {
            return nums[left];
        }

        int middle = (left+right)/2;
        int leftMax = dacMaxSubArray(nums, left, middle);
        int rightMax = dacMaxSubArray(nums, middle+1, right);

        int middleToLeftMax = Integer.MIN_VALUE;
        int middleToLeftCurrentMax = 0;
        for(int i = middle; i >= left; i--) {
            middleToLeftCurrentMax += nums[i];
            middleToLeftMax = Math.max(middleToLeftCurrentMax, middleToLeftMax);
        }

        int middleToRightMax = Integer.MIN_VALUE;
        int middleToRightCurrentMax = 0;
        for(int j = middle+1; j <= right; j++) {
            middleToRightCurrentMax += nums[j];
            middleToRightMax = Math.max(middleToRightCurrentMax, middleToRightMax);
        }

        int middleMax = middleToLeftMax + middleToRightMax;
        return Math.max(middleMax, Math.max(leftMax, rightMax));
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray02(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }

}
