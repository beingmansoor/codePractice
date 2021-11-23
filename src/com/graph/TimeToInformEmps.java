package com.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeToInformEmps
{
	public static int numOfMinutes(int headID, int[] manager, int[] informTime)
	{
		int n = manager.length;
		List<Integer>[] graph = new List[n];
		for (int i = 0; i < n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < n; i++)
		{
			if (manager[i] != -1)
				graph[manager[i]].add(i);
		}
		Queue<int[]> q = new LinkedList<>(); // Since it's a tree, we don't need
												// `visited` array
		q.offer(new int[] { headID, 0 });
		int ans = 0;
		while (!q.isEmpty())
		{
			int[] top = q.poll();
			int u = top[0], w = top[1];
			ans = Math.max(w, ans);
			for (int v : graph[u])
				q.offer(new int[] { v, w + informTime[u] });
		}
		return ans;
	}

	public static void main(String args[])
	{
		// Driver code

		int mainServerId = 0;
		int[] parents = new int[] { -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6 };
		int[] delays = new int[] { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };

		System.out.println("Time required by message to reach all devices is "
				+ numOfMinutes(mainServerId, parents, delays) + " units!");
	}
}
