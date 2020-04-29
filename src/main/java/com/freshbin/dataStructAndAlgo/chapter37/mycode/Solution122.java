package com.freshbin.dataStructAndAlgo.chapter37.mycode;

/**
 * leetcode122
 * 思路：剽窃答案之后的思路，一开始竟然想歪了，
 * 哎，其实很简单，只要每天都买卖，后一天的比前一天价格高就卖掉就是赚的最多的
 * 可惜实际股市股价跌了之后就再也不升回去了。。。
 *
 * @author freshbin
 * @date 2020/4/24 22:38
 */
public class Solution122 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            int currentProfit = prices[i] - prices[i - 1];
            if(currentProfit > 0) {
                maxProfit += currentProfit;
            }
        }
        return maxProfit;
    }

    public static void main(String[] arg) {
        int[] prices = {1,2,3,4,5};
        Solution122 solution122 = new Solution122();
        System.out.println(solution122.maxProfit(prices));
    }
}
