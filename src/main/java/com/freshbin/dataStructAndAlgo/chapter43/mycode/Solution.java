package com.freshbin.dataStructAndAlgo.chapter43.mycode;

import java.util.LinkedList;

/**
 * 拓扑排序
 * @author freshbin
 * @date 2020/4/29 9:11
 */
public class Solution {
    /**
     * leetcode207
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     *
     * 示例 1:
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     *
     * 示例 2:
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     *
     * 思路：
     * 1、先把所有的课程关联关系都用邻接表存起来
     * 2、统计所有的课程数（用一个数组存各个课程的入度数，即先于该课程学习的课程数, 当入度数为0表示不需要依赖课程，直接打印）
     * 3、如果统计出来的课程数小于实际课程数，说明有环，否则，无环。
     *
     * 广度优先算法
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] relyCourseNums = new int[numCourses];
        LinkedList<Integer>[] adj = initGraph(numCourses, prerequisites, relyCourseNums);

        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(relyCourseNums[i] == 0) {
                queue.add(i); // 入度为0的课程不需要依赖其他课程，可以直接放入队列
            }
        }

        System.out.println("广度优先访问如下");
        int count = 0;
        while(!queue.isEmpty()) {
            int currentCourse = queue.remove();
            for(int j = 0; j < adj[currentCourse].size(); j++) {
                int nextCourse = adj[currentCourse].get(j);
                relyCourseNums[nextCourse]--;
                if(relyCourseNums[nextCourse] != 0) {
                    // 入度不为0，说明nextCourse还存在依赖的课程，即需要先学习的课程，所以这里直接跳过
                    continue;
                }
                queue.add(nextCourse);
            }
            count++;
            System.out.print(currentCourse + "->");
        }
        System.out.println();

        return count == numCourses;
    }

    public LinkedList<Integer>[] initGraph(int n, int[][] prerequisites, int[] relyCourseNums) {
        LinkedList<Integer>[] adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }

        for(int i = 0; i < prerequisites.length; i++) {
            int s = prerequisites[i][1];
            int t = prerequisites[i][0];
            adj[s].add(t); // 构造图
            relyCourseNums[t]++; // 统计每个课程需要依赖的课程数
        }

        return adj;
    }

    public static void main(String[] arg) {
        Solution solution = new Solution();
        int numCourse = 2;
//        int[][] prerequisites = {{0,1}, {3,1}, {1,3}, {3,2}};
        int[][] prerequisites = {{0, 1}};
        System.out.println("广度优先，该课程学习路径安排是否合理：" + solution.canFinish(numCourse, prerequisites));
        System.out.println();
        System.out.println("深度优先，该课程学习路径安排是否合理：" + solution.canFinish01(numCourse, prerequisites));
    }

    public Boolean flag = true;
    /**
     * 深度优先算法
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish01(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] adj = initGraph(numCourses, prerequisites);
        LinkedList<Integer>[] inverseAdj = initInverseLinkedList(adj);

        System.out.println("深度优先访问如下");
        int[] visitedPoint = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(!flag) {
                return false;
            }
            if(visitedPoint[i] == -1) {
                // 已经被访问过
                continue;
            }
            dfs(i, inverseAdj, visitedPoint);
        }
        System.out.println();

        return flag;
    }

    public void dfs(int index, LinkedList<Integer>[] inverseAdj, int[] visitedPoint) {
        if(visitedPoint[index] == 1) {
            flag = false;
            return;
        }
        if(visitedPoint[index] == -1) {
            return;
        }
        visitedPoint[index] = 1;
        for(int i = 0; i < inverseAdj[index].size(); i++) {
            int courseIndex = inverseAdj[index].get(i);
            dfs(courseIndex, inverseAdj, visitedPoint);
        }
        visitedPoint[index] = -1;
        System.out.print(index + "->");
    }

    public LinkedList<Integer>[] initGraph(int n, int[][] prerequisites) {
        LinkedList<Integer>[] adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }

        for(int i = 0; i < prerequisites.length; i++) {
            int s = prerequisites[i][1];
            int t = prerequisites[i][0];
            adj[s].add(t); // 构造图
        }

        return adj;
    }

    /**
     * 构造反向边
     * @param adj
     * @return
     */
    public LinkedList<Integer>[] initInverseLinkedList(LinkedList<Integer>[] adj) {
        LinkedList<Integer>[] inverseAdj = new LinkedList[adj.length];
        for(int i = 0; i < adj.length; i++) {
            inverseAdj[i] = new LinkedList<>();
        }

        for(int i = 0; i < adj.length; i++) {
            for(int j = 0; j < adj[i].size(); j++) {
                int nextPoint = adj[i].get(j);
                inverseAdj[nextPoint].add(i);
            }
        }
        return inverseAdj;
    }
}
