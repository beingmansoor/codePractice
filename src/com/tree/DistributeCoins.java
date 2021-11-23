package com.tree;

public class DistributeCoins {
	int res = 0;

	/*
	 * Given the root of a binary tree with N nodes, each node in the tree has
	 * node.val coins, and there are N coins total.
	 * 
	 * In one move, we may choose two adjacent nodes and move one coin from one
	 * node to another. (The move may be from parent to child, or from child to
	 * parent.)
	 * 
	 * Return the number of moves required to make every node have exactly one
	 * coin.
	 */
	
	private int steps = 0; 

	public int distributeCoins4(TreeNode root) {
	    postorder(root);
	    return steps;
	}

	// return coins of this level
	private int postorder(TreeNode node) {
	    if(node == null) return 0;
	    
	    // coin from children -- post-order traversal
	    int coin = postorder(node.left) + postorder(node.right);
		
	    // current node coin
	    if(node.val == 0) coin += -1; // current node need one coin
	    else coin += node.val - 1; // keep one coin for current node
	    
	    steps += Math.abs(coin); // each coin move up to parent node need 1 step
	    return coin; 
	}

	
	public int distributeCoins(TreeNode root) {
		dfs(root);
		return res;
	}

	public int dfs(TreeNode root) {
		if (root == null)
			return 0;
		int left = dfs(root.left), right = dfs(root.right);
		res += Math.abs(left) + Math.abs(right);
		return root.val + left + right - 1;
	}

	int moves = 0;

	/*
	 * For each node, we use an int array to record the information [# of nodes
	 * in the subtree (include itself), # of total coins in the subtree (include
	 * itself)]. Then we know that for a certain node, if there are more coins
	 * than nodes in the subtree, the node must "contribute" the redundant coins
	 * to its parent. Else, if there are more nodes than coins in the subtree,
	 * then the node must take some coins from the parent.
	 * 
	 * Both of these two operations will create moves in the tree. And we just
	 * need to add the absolute value of the (# nodes - # coins) to the final
	 * result because the move can be "contribute" or "take". The time
	 * complexity is O(n) because we are just traversing the tree.
	 */
	public int distributeCoins3(TreeNode root) {
		getNumAndCoins(root);
		return moves;
	}

	/*
	 * return [number_of_nodes_in_subtree, number_of_total_coins_in_subtree]
	 */
	private int[] getNumAndCoins(TreeNode node) {
		if (node == null)
			return new int[] { 0, 0 };
		int[] left = getNumAndCoins(node.left);
		int[] right = getNumAndCoins(node.right);
		moves += Math.abs(left[0] - left[1]) + Math.abs(right[0] - right[1]);
		return new int[] { left[0] + right[0] + 1, left[1] + right[1] + node.val };
	}

	public int distributeCoins1(TreeNode root) {
		int res = 0;
		if (root.left != null) {
			res += distributeCoins(root.left);
			root.val += root.left.val - 1;
			res += Math.abs(root.left.val - 1);
		}
		if (root.right != null) {
			res += distributeCoins(root.right);
			root.val += root.right.val - 1;
			res += Math.abs(root.right.val - 1);
		}
		return res;
	}

	int distributeCoins2(TreeNode root, TreeNode pre) {
		if (null == root)
			return 0;
		int res = distributeCoins2(root.left, root) + distributeCoins2(root.right, root);
		if (null != pre)
			pre.val += root.val - 1;
		return res + Math.abs(root.val - 1);
	}

	int distributeCoins2(TreeNode root) {
		return distributeCoins2(root, null);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);

		int distributeCoins2 = new DistributeCoins().distributeCoins3(root);
		System.out.println(distributeCoins2);
	}

}
