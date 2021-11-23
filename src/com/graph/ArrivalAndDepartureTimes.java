package com.graph;
import java.util.*;

//Data structure to store Graph1 edges
class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

//Class to represent a Graph1 object
class Graph1
{
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph1(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected Graph1
		for (Edge edge: edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}


public class ArrivalAndDepartureTimes
{
	// Function to perform DFS Traversal
	public static int DFS(Graph1 Graph1, int v, boolean[] discovered, int[] arrival, int[] departure, int time) {

		// set arrival time of vertex v
		arrival[v] = ++time;

		// mark vertex as discovered
		discovered[v] = true;

		for (int i : Graph1.adjList.get(v)) {
			if (!discovered[i]) {
				time = DFS(Graph1, i, discovered, arrival, departure, time);
			}
		}

		// set departure time of vertex v
		departure[v] = ++time;

		return time;
	}

	public static void main(String[] args)
	{
		// List of Graph1 edges as per above diagram
		List<Edge> edges = Arrays.asList(
								new Edge(0, 1), new Edge(0, 2),
								new Edge(2, 3), new Edge(2, 4),
								new Edge(3, 1), new Edge(3, 5),
								new Edge(4, 5), new Edge(6, 7)
							);

		// Set number of vertices in the Graph1
		final int N = 8;

		// create a Graph1 from edges
		Graph1 Graph1 = new Graph1(edges, N);

		// array to store arrival time of vertex
		int[] arrival = new int[N];

		// array to store departure time of vertex
		int[] departure = new int[N];

		// Mark all the vertices as not discovered
		boolean[] discovered = new boolean[N];
		int time = -1;

		// Do DFS traversal from all undiscovered nodes to
		// cover all unconnected components of Graph1
		for (int i = 0; i < N; i++) {
			if (!discovered[i]) {
				time = DFS(Graph1, i, discovered, arrival, departure, time);
			}
		}

		// print arrival and departure time of each
		// vertex in DFS
		for (int i = 0; i < N; i++) {
			System.out.println("Vertex " + i + " (" + arrival[i]
								+ ", " + departure[i] + ")");
		}
	}
}