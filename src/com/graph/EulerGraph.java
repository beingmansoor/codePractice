package com.graph;

import java.util.Iterator;

import com.graph.Tarjan_StronglyConnected.Graph;;

/*
 * Eulerian Cycle
An undirected graph has Eulerian cycle if following two conditions are true.
….a) All vertices with non-zero degree are connected. We don’t care about vertices with zero 
degree because they don’t belong to Eulerian Cycle or Path (we only consider all edges).
….b) All vertices have even degree.

Eulerian Path
An undirected graph has Eulerian Path if following two conditions are true.
….a) Same as condition (a) for Eulerian Cycle
….b) If zero or two vertices have odd degree and all other vertices have even degree. 
Note that only one vertex with odd degree is not possible in an undirected graph 
(sum of all degrees is always even in an undirected graph)

Note that a graph with no edges is considered Eulerian because there are no edges to traverse.

How does this work?
In Eulerian path, each time we visit a vertex v, we walk through two unvisited edges with one end 
point as v. Therefore, all middle vertices in Eulerian Path must have even degree. 
For Eulerian Cycle, any vertex can be middle vertex, therefore all vertices must have even degree.
 */
public class EulerGraph
{
	static void DFSUtil(Graph g, int v, boolean visited[])
	{
		// Mark the current node as visited
		visited[v] = true;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = g.adj[v].listIterator();
		while (i.hasNext())
		{
			int n = i.next();
			if (!visited[n])
				DFSUtil(g, n, visited);
		}
	}

	// Method to check if all non-zero degree vertices are
	// connected. It mainly does DFS traversal starting from
	static boolean isConnected(Graph g)
	{
		// Mark all the vertices as not visited
		boolean visited[] = new boolean[g.V];
		int i;
		for (i = 0; i < g.V; i++)
			visited[i] = false;

		// Find a vertex with non-zero degree
		for (i = 0; i < g.V; i++)
			if (g.adj[i].size() != 0)
				break;

		// If there are no edges in the graph, return true
		if (i == g.V)
			return true;

		// Start DFS traversal from a vertex with non-zero degree
		DFSUtil(g, i, visited);

		// Check if all non-zero degree vertices are visited
		for (i = 0; i < g.V; i++)
			if (visited[i] == false && g.adj[i].size() > 0)
				return false;

		return true;
	}

	/*
	 * The function returns one of the following values 0 --> If grpah is not
	 * Eulerian 1 --> If graph has an Euler path (Semi-Eulerian) 2 --> If graph
	 * has an Euler Circuit (Eulerian)
	 */
	static int isEulerian(Graph g)
	{
		// Check if all non-zero degree vertices are connected
		if (isConnected(g) == false)
			return 0;

		// Count vertices with odd degree
		int odd = 0;
		for (int i = 0; i < g.V; i++)
			if (g.adj[i].size() % 2 != 0)
				odd++;

		// If count is more than 2, then graph is not Eulerian
		if (odd > 2)
			return 0;

		// If odd count is 2, then semi-eulerian.
		// If odd count is 0, then eulerian
		// Note that odd count can never be 1 for undirected graph
		return (odd == 2) ? 1 : 2;
	}

	// Function to run test cases
	static void test(Graph g)
	{
		int res = isEulerian(g);
		if (res == 0)
			System.out.println("graph is not Eulerian");
		else if (res == 1)
			System.out.println("graph has a Euler path");
		else
			System.out.println("graph has a Euler cycle");
	}

	// Driver method
	public static void main(String args[])
	{
		// Let us create and test graphs shown in above figures
		Tarjan_StronglyConnected t = new Tarjan_StronglyConnected();
		Graph g1 = t.new Graph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		test(g1);

		Graph g2 = t.new Graph(5);
		g2.addEdge(1, 0);
		g2.addEdge(0, 2);
		g2.addEdge(2, 1);
		g2.addEdge(0, 3);
		g2.addEdge(3, 4);
		g2.addEdge(4, 0);
		test(g2);

		Graph g3 = t.new Graph(5);
		g3.addEdge(1, 0);
		g3.addEdge(0, 2);
		g3.addEdge(2, 1);
		g3.addEdge(0, 3);
		g3.addEdge(3, 4);
		g3.addEdge(1, 3);
		test(g3);

		// Let us create a graph with 3 vertices
		// connected in the form of cycle
		Graph g4 = t.new Graph(3);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		test(g4);

		// Let us create a graph with all veritces
		// with zero degree
		Graph g5 = t.new Graph(3);
		test(g5);
	}
}
