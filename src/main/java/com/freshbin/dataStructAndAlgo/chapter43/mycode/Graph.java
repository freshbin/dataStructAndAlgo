package com.freshbin.dataStructAndAlgo.chapter43.mycode;

import java.util.LinkedList;

/**
 * 图
 * @author freshbin
 * @date 2020/4/29 9:19
 */
public class Graph {
    private int v;
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }
}
