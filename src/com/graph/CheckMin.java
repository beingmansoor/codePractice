package com.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CheckMin {
	public static int findMin(Graph g, int source, int destination) {

		int num_of_vertices = g.vertices;

		// Boolean Array to hold the history of visited nodes (by default-false)
		// Make a node visited whenever you enqueue it into queue
		boolean[] visited = new boolean[num_of_vertices];

		// For keeping track of distance of current_node from source
		int[] distance = new int[num_of_vertices];

		// Create Queue for Breadth First Traversal and enqueue source in it
		Queue<Integer> queue = new LinkedList<>();

		queue.add(source);
		visited[source] = true;

		// Traverse while queue is not empty
		while (!queue.isEmpty()) {

			// Dequeue a vertex/node from queue and add it to result
			int current_node = queue.poll();

			// Get adjacent vertices to the current_node from the array,
			// and if they are not already visited then enqueue them in the
			// Queue
			// and also update their distance from source by adding 1 in
			// current_nodes's distance
			DoublyLinkedList<Integer>.Node temp = null;
			if (g.adjacencyList[current_node] != null)
				temp = g.adjacencyList[current_node].headNode;

			while (temp != null) {

				if (!visited[temp.data]) {
					queue.add(temp.data);
					visited[temp.data] = true;
					distance[temp.data] = distance[current_node] + 1;
				}
				if (temp.data == destination) {
					return distance[destination];
				}
				temp = temp.nextNode;
			}
		} // end of while
		return -1;
	}

	public static void main(String args[]) {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(3, 4);
		g.addEdge(1, 4);
		System.out.println(findMin(g, 0, 4));
	}
}