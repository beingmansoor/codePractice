package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GraphAllPathsSourceTarget {
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		if (graph == null || graph.length == 0) {
			return res;
		}
		int N = graph.length - 1;
		List<Integer> partialSolution = new ArrayList<>();
		partialSolution.add(0);
		backtrack(0, graph, partialSolution, res, N);
		return res;
	}

	private void backtrack(int nodeID, int[][] graph, List<Integer> partialSolution,
			List<List<Integer>> completeSolution, int N) {
		if (nodeID == N) {
			completeSolution.add(new ArrayList<Integer>(partialSolution));
			return;
		}
		int[] candidates = graph[nodeID];
		for (int currentNode : candidates) {
			partialSolution.add(currentNode); // makeMode
			backtrack(currentNode, graph, partialSolution, completeSolution, N); // recursively
																					// call
																					// backtrack(
																					// ...
																					// )
			partialSolution.remove(partialSolution.size() - 1); // undoMove
		}
	}
}
