package com.freshbin.dailyTopic.month5.firstTen;

/**
 * 53. 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @author freshbin
 * @date 2020/5/3 10:59
 */
public class Solution53 {
    /**
     * 思路：遍历数组，
     * 如果前面累加的和如果大于0，那么才把当前值与前面累加的值相加，
     * （不然加前面累加的值一定比当前值小，所以还不如直接用当前值开始往后继续累加）
     * 判断当前值和临时最大值哪个比较大，大的继续赋值到临时最大值
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxNum = nums[0];
        for(int i = 1; i <nums.length; i++) {
            if(nums[i - 1] > 0) {
                nums[i] += nums[i-1];
            }
            maxNum = Math.max(maxNum, nums[i]);
        }
        return maxNum;
    }

    public static void main(String[] arg) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        Solution53 solution53 = new Solution53();
        System.out.println(solution53.maxSubArray(a));
    }
}
