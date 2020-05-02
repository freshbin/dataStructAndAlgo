package com.freshbin.basics.array;

/**
 * 面试题 10.01. 合并排序的数组
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 * @author freshbin
 * @date 2020/5/1 17:47
 */
public class Solution1496 {
    /**
     * 思路：遍历B数组，与A数组从下标为0的值开始往后比较
     * 如果B数组的值比A数组当前下标的值大，那么A数组下标继续往后走
     * 否则，将A数组当前下标以及后面的值都往后推一个索引，
     * 将A数组当前下标的值设为B数组当前值
     * 最后如果B数组还没有全部放入A数组，那么将剩下的数据全部放入A数组后面
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int aIndex = 0;
        int addNum = 0;
        for(int i = 0; i < n; i++) {
            for(; aIndex < m+addNum; aIndex++) {
                if(B[i] <= A[aIndex]) {
                    for(int nextIndex = m+addNum; nextIndex > aIndex; nextIndex--) {
                        A[nextIndex] = A[nextIndex-1];
                    }
                    A[aIndex] = B[i];
                    aIndex++;
                    addNum++;
                    break;
                }
            }
        }
        for(; addNum < n; addNum++) {
            A[m+addNum] = B[addNum];
        }
    }

    public static void main(String[] arg) {
        int[] A = {4,0,0,0,0,0};
        int m = 1;
        int[] B = {1,2,3,5,6};
        int n = 5;
        Solution1496 solution1496 = new Solution1496();
        solution1496.merge(A, m, B, n);

        for(int i : A) {
            System.out.print(i + " ");
        }
    }
}
