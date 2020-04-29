package com.freshbin.dataStructAndAlgo.chapter31.mycode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 * @author freshbin
 * @date 2020/4/23 9:17
 */
public class Graph {
    private int v;
    private LinkedList<Integer>[] adj;

    Boolean foundFlag = false;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; i++) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 图的广度优先搜索
     *
     * 需要借助一个是否访问过的数据visited[]记录所有访问过的顶点
     * 使用queue队列记录准备访问的顶点
     * 使用prev[]记录搜索的路径
     * 当队列有顶点元素，那么就取出该顶点的所有连接顶点，挨个判断是否已经访问过，
     * 没有访问过的都放入队列,之后记录该顶点为已访问过
     * 直到找到目标顶点t，就可以递归打印prev，打印出最短搜索路径
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if(s == t) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        boolean[] visited = new boolean[v];
        visited[s] = true;

        int[] prev = new int[v];
        for(int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        while(queue.size() > 0) {
            int w = queue.poll();
            for(int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if(!visited[q]) {
                    prev[q] = w;
                    if(q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }

            }
        }
    }

    public void print(int[] prev, int s, int t) {
        if(prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] arg) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);

        System.out.println("广度优先:");
        graph.bfs(0,6);
        System.out.println();

        System.out.println("深度优先:");
        graph.dfs(0, 6);
    }

    /**
     * 深度优先搜索
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for(int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        recurDfs(visited, prev, s, t);
        if(foundFlag) {
            print(prev, s, t);
        } else {
            System.out.println("两个顶点不存在通路!");
        }

    }

    private void recurDfs(boolean[] visited, int[] prev, int w, int t) {
        if(foundFlag) return; // 找到之后直接返回，不再遍历for代码中目标下标值后面的数
        visited[w] = true;
        if(w == t) {
            foundFlag = true;
            return;
        }

        for(int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if(!visited[q]) {
                prev[q] = w;
                recurDfs(visited, prev, q, t);
            }
        }
    }
}
