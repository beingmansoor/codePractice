package com.graph;

/*
 * 
 * You wrote a trendy new messaging app, MeshMessage, to get around flaky cell phone coverage.

Instead of routing texts through cell towers, your app sends messages via the phones of nearby 
users, passing each message along from one phone to the next until it reaches the intended 
recipient. (Don't worry—the messages are encrypted while they're in transit.)

Some friends have been using your service, and they're complaining that it takes a long time 
for messages to get delivered. After some preliminary debugging, you suspect messages might
 not be taking the most direct route from the sender to the recipient.

Given information about active users on the network, find the shortest route for a message
 from one user (the sender) to another (the recipient). Return an array of users that make up
  this route.

There might be a few shortest delivery routes, all with the same length. 
For now, let's just return any shortest route.

Your network information takes the form of a hash map mapping username strings to an array of
 other users nearby:

  Map<String, String[]> network = new HashMap<String, String[]>() {{
    put("Min",     new String[] { "William", "Jayden", "Omar" });
    put("William", new String[] { "Min", "Noam" });
    put("Jayden",  new String[] { "Min", "Amelia", "Ren", "Noam" });
    put("Ren",     new String[] { "Jayden", "Omar" });
    put("Amelia",  new String[] { "Jayden", "Adam", "Miguel" });
    put("Adam",    new String[] { "Amelia", "Miguel", "Sofia", "Lucas" });
    put("Miguel",  new String[] { "Amelia", "Adam", "Liam", "Nathan" });
    put("Noam",    new String[] { "Nathan", "Jayden", "William" });
    put("Omar",    new String[] { "Ren", "Min", "Scott" });
    ...
}};

For the network above, a message from Jayden to Adam should have this route:

  { "Jayden", "Amelia", "Adam" }
 * 
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MeshMessageShortestPath {
	private static String[] reconstructPath(Map<String, String> previousNodes, String startNode, String endNode) {

		List<String> reversedShortestPath = new ArrayList<>();

		// start from the end of the path and work backwards
		String currentNode = endNode;

		while (currentNode != null) {
			reversedShortestPath.add(currentNode);
			currentNode = previousNodes.get(currentNode);
		}

		// reverse our path to get the right order
		// by flipping it around, in place
		Collections.reverse(reversedShortestPath);
		return reversedShortestPath.toArray(new String[reversedShortestPath.size()]);
	}

	public static String[] bfsGetPath(Map<String, String[]> graph, String startNode, String endNode) {

		if (!graph.containsKey(startNode)) {
			throw new IllegalArgumentException("Start node not in graph");
		}
		if (!graph.containsKey(endNode)) {
			throw new IllegalArgumentException("End node not in graph");
		}

		Queue<String> nodesToVisit = new ArrayDeque<>();
		nodesToVisit.add(startNode);

		// keep track of how we got to each node
		// we'll use this to reconstruct the shortest path at the end
		// we'll ALSO use this to keep track of which nodes we've
		// already visited
		Map<String, String> howWeReachedNodes = new HashMap<>();
		howWeReachedNodes.put(startNode, null);

		while (!nodesToVisit.isEmpty()) {
			String currentNode = nodesToVisit.remove();

			// stop when we reach the end node
			if (currentNode.equals(endNode)) {
				return reconstructPath(howWeReachedNodes, startNode, endNode);
			}

			for (String neighbor : graph.get(currentNode)) {
				if (!howWeReachedNodes.containsKey(neighbor)) {
					nodesToVisit.add(neighbor);
					howWeReachedNodes.put(neighbor, currentNode);
				}
			}
		}

		// if we get here, then we never found the end node
		// so there's NO path from startNode to endNode
		return null;
	}

	/*
	 * Complexity Our solution has two main steps. First, we do a breadth-first
	 * search of the user network starting from the sender. Then, we use the
	 * results of our search to backtrack and find the shortest path.
	 * 
	 * How much work is a breadth-first search?
	 * 
	 * In the worst case, we'll go through the BFS loop once for every node in
	 * the graph, since we only ever add each node to nodesToVisit once (we
	 * check howWeReachedNodes to see if we've already added a node before).
	 * Each loop iteration involves a constant amount of work to dequeue the
	 * node and check if it's our end node. If we have nn nodes, then this
	 * portion of the loop is O(N)O(N).
	 * 
	 * But there's more to each loop iteration: we also look at the current
	 * node's neighbors. Over all of the nodes in the graph, checking the
	 * neighbors is O(M)O(M), since it involves crossing each edge twice: once
	 * for each node at either end.
	 * 
	 * Putting this together, the complexity of the breadth-first search is
	 * O(N+M)O(N+M).
	 * 
	 * BFS and DFS are common enough that it's often acceptable to just state
	 * their complexity as O(N+M)O(N+M). Some interviewers might want you to
	 * derive it though, so definitely be ready in case they ask.
	 * 
	 * What about backtracking to determine the shortest path? Handling each
	 * node in the path is O(1)O(1), and we could have at most NN nodes in our
	 * shortest path. So, that's O(N)O(N) for building up the path. Then, it's
	 * another O(N)O(N) to reverse it. So, the total time complexity of our
	 * backtracking step is O(N)O(N).
	 * 
	 * Putting these together, the time complexity of our entire algorithm is
	 * O(N+M)O(N+M).
	 * 
	 * What about space complexity? The queue of nodes to visit, the mapping of
	 * nodes to previous nodes, and the final path ... they all store a constant
	 * amount of information per node. So, each data structure could take up to
	 * O(N)O(N) space if it stored information about all of our nodes. That
	 * means our overall space complexity is O(N)O(N).
	 */

}
