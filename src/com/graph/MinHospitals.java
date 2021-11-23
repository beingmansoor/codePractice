package com.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinHospitals {
	public static void main(String[] args) {
		int node1 = 7;
		int[] cityFrom1 = { 1, 3, 1, 3, 2, 1 }, cityTo1 = { 2, 6, 4, 7, 5, 3 };
		System.out.println(getMinHosptials(node1, cityFrom1, cityTo1));
		int node2 = 4;
		int[] cityFrom2 = { 1, 2, 3 }, cityTo2 = { 2, 3, 4 };
		System.out.println(getMinHosptials(node2, cityFrom2, cityTo2));
		int node3 = 12;
		int[] cityFrom3 = { 1, 1, 6, 1, 1, 2, 11, 12, 4, 12, 6 }, cityTo3 = { 11, 2, 7, 6, 12, 3, 10, 9, 5, 8, 4 };
		System.out.println(getMinHosptials(node3, cityFrom3, cityTo3));
		int node4 = 6;
		int[] cityFrom4 = { 1, 2, 4, 2, 2 }, cityTo4 = { 2, 3, 2, 5, 6 };
		System.out.println(getMinHosptials(node4, cityFrom4, cityTo4));
	}

	static int tmp = 0;

	private static int getMinHosptials(int node, int[] cityFrom, int[] cityTo) {
		int res = 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		boolean[] visited = new boolean[node + 1];
		for (int i = 1; i <= node; i++) {
			map.put(i, new HashSet<>());
		}
		for (int i = 0; i < cityFrom.length; i++) {
			map.get(cityFrom[i]).add(cityTo[i]);
			map.get(cityTo[i]).add(cityFrom[i]);
		}
		for (int i = 1; i <= node; i++) {
			if (!visited[i]) {
				tmp = 0;
				res += (dfs(map, i, visited) == 0 ? 1 : 0) + tmp;
			}
		}
		return res;
	}

	// 0 leaf, 1 parent, 2 covered
	static int dfs(Map<Integer, Set<Integer>> map, int cur, boolean[] visited) {
		if (visited[cur])
			return 2;
		visited[cur] = true;
		Set<Integer> set = new HashSet<>();
		for (int nei : map.get(cur)) {
			if (!visited[nei]) {
				set.add(dfs(map, nei, visited));
			}
		}
		if (set.contains(0)) {
			tmp++;
			return 1;
		}
		if (set.contains(1))
			return 2;
		return 0;
	}
}
