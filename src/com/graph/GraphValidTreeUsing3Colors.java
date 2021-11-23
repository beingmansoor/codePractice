package com.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GraphValidTreeUsing3Colors {

    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> adj = new HashMap<Integer, List<Integer>>();
        init(adj, edges);
        int[] color = new int[n]; // 0 is white 1 is gray and 2 is black
        if (!dfsVisit(-1, 0, adj, color)) {
            return false;
        }
        for (int i = 0; i < color.length; i ++) {
            if (color[i] == 0) {
                return false;
            }
        }
        return true;
    }
    private void init(HashMap<Integer, List<Integer>> adj, int[][] edges) {
        for (int i = 0; i < edges.length; i ++) {
            if (!adj.containsKey(edges[i][0])) {
                adj.put(edges[i][0], new LinkedList<Integer>());
            }
            adj.get(edges[i][0]).add(edges[i][1]);
            if (!adj.containsKey(edges[i][1])) {
                adj.put(edges[i][1], new LinkedList<Integer>());
            }
            adj.get(edges[i][1]).add(edges[i][0]);
        }
    }
    private boolean dfsVisit(int parent, int index, HashMap<Integer, List<Integer>> adj, int[] color) {
        if (color[index] != 0) {
            return false;
        }
        color[index] = 1;
        if (adj.containsKey(index)) {
            for (Integer i:adj.get(index)) {
                if (parent != i && !dfsVisit(index, i, adj, color)) {
                    return false;
                }
            }
        }
        return true;
    }
}
