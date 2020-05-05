package com.freshbin.dailyTopic.month5.firstTen;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 *
 * @author freshbin
 * @date 2020/5/4 16:41
 */
public class Solution45 {
    /**
     * 思路：从第一个开始往后跳，每次跳的时候，获取当前值，
     * 依次取出该值后面几个值能跳到的最大值，即该位置的索引加该位置的数据之和
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int stepNum = 0;
        for(int currentIndex = 0; currentIndex < nums.length - 1; ) {
            stepNum++;
            int currentNum = nums[currentIndex];
            int maxNum = currentIndex + currentNum;
            int nextIndex = currentIndex+1;
            int maxIndex = currentIndex;
            while(currentNum > 0 && nextIndex < nums.length-1) {
                if(currentNum + currentIndex >= nums.length - 1) {
                    //当前值可以直接跳到终点
                    return stepNum;
                }
                if(nextIndex + nums[nextIndex] > maxNum) {
                    maxIndex = nextIndex;
                    maxNum = nextIndex + nums[nextIndex];
                }
                currentNum--;
                nextIndex++;
            }
            if(currentIndex == maxIndex) {
                // 当前值后面的数跳的最大值都没有当前值跳的步数大，因此，直接由该索引跳到索引+该值的位置
                currentIndex = currentIndex + currentNum;
            } else {
                currentIndex = maxIndex;
            }
        }
        return stepNum;
    }

    /**
     * 官方解法
     *
     * @param nums
     * @return
     */
    public int jump01(int[] nums) {
        int steps = 0;
        int end = 0;
        int maxPosition = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if(i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] arg) {
        int[] nums = {10,9,8,7,6,5,4,3,2,1,1,0};
        Solution45 solution45 = new Solution45();
        System.out.println(solution45.jump(nums));
        System.out.println(solution45.jump01(nums));
    }
}
