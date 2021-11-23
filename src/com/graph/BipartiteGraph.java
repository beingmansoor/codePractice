package com.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BipartiteGraph
{
	// Perform BFS on graph starting from vertex v
	public static boolean BFS(Graph1 graph, int v, int N)
	{
		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// stores level of each vertex in BFS
		int[] level = new int[N];

		// mark source vertex as discovered and
		// set its level to 0
		discovered[v] = true;
		level[v] = 0;

		// create a queue to do BFS and enqueue
		// source vertex in it
		Queue<Integer> q = new ArrayDeque<>();
		q.add(v);

		// run till queue is not empty
		while (!q.isEmpty())
		{
			// pop front node from queue and print it
			v = q.poll();

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(v))
			{
				// if vertex u is explored for first time
				if (!discovered[u])
				{
					// mark it discovered
					discovered[u] = true;

					// set level as level of parent node + 1
					level[u] = level[v] + 1;

					// push the vertex into the queue
					q.add(u);
				}
				// if the vertex is already been discovered and
				// level of vertex u and v are same, then the
				// graph contains an odd-cycle & is not biparte
				else if (level[v] == level[u])
					return false;
			}
		}
		return true;
	}
	
	boolean isBipartite(int G[][],int src) 
    { 
        int V = G.length;
		// Create a color array to store  
        // colors assigned to all veritces. 
        // Vertex number is used as index  
        // in this array. The value '-1' 
        // of colorArr[i] is used to indicate  
        // that no color is assigned 
        // to vertex 'i'. The value 1 is  
        // used to indicate first color 
        // is assigned and value 0 indicates  
        // second color is assigned. 
        int colorArr[] = new int[V ]; 
        for (int i=0; i<V; ++i) 
            colorArr[i] = -1; 
  
        // Assign first color to source 
        colorArr[src] = 1; 
  
        // Create a queue (FIFO) of vertex numbers  
        // and enqueue source vertex for BFS traversal 
        LinkedList<Integer>q = new LinkedList<Integer>(); 
        q.add(src); 
  
        // Run while there are vertices in queue (Similar to BFS) 
        while (q.size() != 0) 
        { 
            // Dequeue a vertex from queue 
            int u = q.poll(); 
  
            // Return false if there is a self-loop  
            if (G[u][u] == 1) 
                return false;  
  
            // Find all non-colored adjacent vertices 
            for (int v=0; v<V; ++v) 
            { 
                // An edge from u to v exists  
                // and destination v is not colored 
                if (G[u][v]==1 && colorArr[v]==-1) 
                { 
                    // Assign alternate color to this adjacent v of u 
                    colorArr[v] = 1-colorArr[u]; 
                    q.add(v); 
                } 
  
                // An edge from u to v exists and destination 
                //  v is colored with same color as u 
                else if (G[u][v]==1 && colorArr[v]==colorArr[u]) 
                    return false; 
            } 
        } 
        // If we reach here, then all adjacent vertices can 
        // be colored with alternate color 
        return true; 
    } 

	public static void main(String[] args)
	{
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(
							new Edge(1, 2), new Edge(2, 3), new Edge(2, 8),
							new Edge(3, 4), new Edge(4, 6), new Edge(5, 7),
							new Edge(5, 9), new Edge(8, 9)
							// if we add 2->4 edge, graph is becomes non-Bipartite
						);

		// Set number of vertices in the graph
		final int N = 10;

		// create a graph from edges
		Graph1 graph = new Graph1(edges, N);

		// Do BFS traversal starting from vertex 1
		if (BFS(graph, 1, N))
			System.out.println("Bipartite Graph");
		else
			System.out.println("Not a Bipartite Graph");
		
		int G[][] = {{0, 1, 0, 1}, 
	            {1, 0, 1, 0}, 
	            {0, 1, 0, 1}, 
	            {1, 0, 1, 0} 
	        }; 
	        BipartiteGraph b = new BipartiteGraph(); 
	        if (b.isBipartite(G, 0)) 
	        System.out.println("Yes"); 
	        else
	        System.out.println("No"); 
	}
}