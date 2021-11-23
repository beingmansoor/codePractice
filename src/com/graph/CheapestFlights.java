package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200

 */
public class CheapestFlights {

	public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {

		Map<Integer, List<int[]>> g = new HashMap<Integer, List<int[]>>();

		for (int[] f : flights) {
			List<int[]> list = g.getOrDefault(f[0], new ArrayList<int[]>());
			list.add(new int[] { f[1], f[2] });
			g.put(f[0], list);
		}

		int[] r = new int[] { Integer.MAX_VALUE };

		boolean[] visited = new boolean[n];
		dfs(g, src, dst, r, K, visited, 0);

		return r[0];
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
		for (int[] f : flights) {
			if (!prices.containsKey(f[0])) {
				prices.put(f[0], new HashMap<>());
			}
			prices.get(f[0]).put(f[1], f[2]);
		}
		Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
		pq.add(new int[] { 0, src, k + 1 });
		while (!pq.isEmpty()) {
			int[] top = pq.remove();
			int price = top[0];
			int city = top[1];
			int stops = top[2];
			if (city == dst) {
				return price;
			}
			if (stops > 0) {
				Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
				for (int a : adj.keySet()) {
					pq.add(new int[] { price + adj.get(a), a, stops - 1 });
				}
			}
		}
		return -1;
	}

	private void dfs(Map<Integer, List<int[]>> g, int src, int dst, int[] r, int k, boolean[] visited, int tc) {

		if (k < -1) {
			return;
		}

		if (src == dst) {
			r[0] = Math.min(r[0], tc);
			return;
		}

		visited[src] = true;

		List<int[]> list = g.get(src);
		if (null != list) {
			for (int[] to : list) {

				if (false == visited[to[0]] && tc + to[1] <= r[0]) {
					dfs(g, to[0], dst, r, k - 1, visited, tc + to[1]);
				}
			}
		}

		visited[src] = false;

	}

	public static void main(String[] args) {

		CheapestFlights c = new CheapestFlights();
		System.out
				.println(c.findCheapestPrice(3, new int[][] { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } }, 0, 2, 1));
		System.out
				.println(c.findCheapestPrice(3, new int[][] { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } }, 0, 2, 0));
	}

}
