package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PossibleBiPartition {

	public boolean possibleBipartition(int N, int[][] dislikes) {
		int[][] g = new int[N + 1][N + 1];

		Set<Integer> srcs = new HashSet<Integer>();
		for (int[] dl : dislikes) {
			g[dl[0]][dl[1]] = 1;
			g[dl[1]][dl[0]] = 1;
			srcs.add(dl[0]);
		}

		int[] color = new int[N + 1];
		Arrays.fill(color, -1);

		for (int src : srcs) {
			if (color[src] == -1) {
				Queue<Integer> q = new LinkedList<>();
				q.offer(src);
				color[src] = 1;
				while (!q.isEmpty()) {
					Integer u = q.poll();

					// self loop
					if (g[u][u] == 1) {
						return false;
					}
					for (int v = 1; v <= N; v++) {
						if (g[u][v] == 1 && color[v] == -1) {
							color[v] = 1 - color[u];
							q.offer(v);
						} else if (g[u][v] == 1 && color[u] == color[v]) {
							return false;
						}

					}
				}
			}
		}
		return true;
	}

	public boolean possibleBipartition_UF(int N, int[][] dislikes) {
		int[] parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int[] pair : dislikes) {
			int a = pair[0];
			int b = pair[1];
			map.putIfAbsent(a, new ArrayList<>());
			map.putIfAbsent(b, new ArrayList<>());
			map.get(a).add(b);
			map.get(b).add(a);
		}
		for (int i = 1; i <= N; i++) {
			if (map.containsKey(i)) {
				int parent1 = find(parent, i);
				List<Integer> opponents = map.get(i);
				int parent2 = find(parent, opponents.get(0));
				if (parent1 == parent2) {
					return false;
				}
				for (int j = 1; j < opponents.size(); j++) {
					int opponentParent = find(parent, opponents.get(j));
					if (parent1 == opponentParent) {
						return false;
					}
					parent[opponentParent] = parent2;
				}
			}
		}
		return true;
	}

	private int find(int[] parent, int i) {
		while (i != parent[i]) {
			i = parent[parent[i]];
		}
		return i;
	}

	public static void main(String[] args) {
		PossibleBiPartition p = new PossibleBiPartition();

		System.out.println(p.possibleBipartition(5, new int[][] { { 1, 2 }, { 3, 4 }, { 4, 5 }, { 3, 5 } }));
	}
}
