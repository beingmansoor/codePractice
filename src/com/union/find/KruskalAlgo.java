package com.union.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalAlgo {
	// construct MST using Kruskal's algorithm
	public static List<Edge> KruskalAlgo(List<Edge> edges, int N) {
		// stores edges present in MST
		List<Edge> MST = new ArrayList();

		// initialize DisjointSet class
		// create singleton set for each element of universe
		DisjointSet ds = new DisjointSet();
		ds.makeSet(N);

		int index = 0;

		// MST contains exactly V-1 edges
		while (MST.size() != N - 1) {
			// consider next edge with minimum weight from the graph
			Edge next_edge = edges.get(index++);

			// find root of the sets to which two endpoint
			// vertices of next_edge belongs
			int x = ds.Find(next_edge.src);
			int y = ds.Find(next_edge.dest);

			// if both endpoints have different parents, they belong to
			// different connected components and can be included in MST
			if (x != y) {
				MST.add(next_edge);
				ds.Union(x, y);
			}
		}
		return MST;
	}

	public static void main(String[] args) {
		// (u, v, w) tiplet represent undirected edge from
		// vertex u to vertex v having weight w
		List<Edge> edges = Arrays.asList(new Edge(0, 1, 7), new Edge(1, 2, 8), new Edge(0, 3, 5), new Edge(1, 3, 9),
				new Edge(1, 4, 7), new Edge(2, 4, 5), new Edge(3, 4, 15), new Edge(3, 5, 6), new Edge(4, 5, 8),
				new Edge(4, 6, 9), new Edge(5, 6, 11));

		// sort edges by increasing weight
		Collections.sort(edges, (a, b) -> a.weight - b.weight);

		// Number of vertices in the graph
		final int N = 7;

		// construct graph
		List<Edge> e = KruskalAlgo(edges, N);

		for (Edge edge : e) {
			System.out.println(edge);
		}
	}
}

// Data structure to store graph edges
class Edge {
	int src, dest, weight;

	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "(" + src + ", " + dest + ", " + weight + ")";
	}
};

