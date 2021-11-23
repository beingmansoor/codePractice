package com.tree.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinSteps
{
	 public static int minSteps(int[] k) {
	        int n = k.length;
	        if (n <= 1) {
	            return 0;
	        }

	        Map<Integer, List<Integer>> graph = new HashMap<>();
	        for (int i = 0; i < n; i++) {
	            graph.computeIfAbsent(k[i], v -> new LinkedList<>()).add(i);
	        }

	        List<Integer> current = new LinkedList<>(); // store current layer
	        current.add(0);
	        Set<Integer> visited = new HashSet<>();
	        int step = 0;

	        // when current layer exists
	        while (!current.isEmpty()) {
	            List<Integer> nextNode = new LinkedList<>();

	            // iterate the layer
	            for (int node : current) {
	                // check if reached end
	                if (node == n - 1) {
	                    return step;
	                }

	                // check same value
	                for (int child : graph.get(k[node])) {
	                    if (!visited.contains(child)) {
	                        visited.add(child);
	                        nextNode.add(child);
	                    }
	                }

	                // clear the list to prevent redundant search
	                graph.get(k[node]).clear();

	                // check neighbors
	                if (node + 1 < n && !visited.contains(node + 1)) {
	                    visited.add(node + 1);
	                    nextNode.add(node + 1);
	                }
	                if (node - 1 >= 0 && !visited.contains(node - 1)) {
	                    visited.add(node - 1);
	                    nextNode.add(node - 1);
	                }
	            }

	            current = nextNode;
	            step++;
	        }

	        return -1;
	    }
	    public static void main( String args[] ) {
	        // Driver code
	        
	        int[] k = {1, 2, 3, 4, 1, 3, 5, 3, 5};
	        System.out.println(minSteps(k));
	    }
}
