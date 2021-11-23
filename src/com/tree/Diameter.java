package com.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Diameter {

	public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int[] ans = dfs(root);
        return ans[0];
    }
    private int[] dfs(TreeNode root){
        int[] ans = new int[2];
        if(root == null ) return ans;
        
        int[] left = dfs(root.left);
        int[] right = dfs(root. right);
        
        ans[1] = 1 + Math.max(left[1], right[1]);
        ans[0] = Math.max(left[0],Math.max(right[0],left[1]+ right[1]));
        
        return ans;
    }

	public int diameterOfBinaryTree_Iterative(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int overallNodeMax = 0;
		Stack<TreeNode> nodeStack = new Stack<>();
		Map<TreeNode, Integer> nodePathCountMap = new HashMap<>();
		nodeStack.push(root);
		while (!nodeStack.isEmpty()) {
			TreeNode node = nodeStack.peek();
			if (node.left != null && !nodePathCountMap.containsKey(node.left)) {
				nodeStack.push(node.left);
			} else if (node.right != null && !nodePathCountMap.containsKey(node.right)) {
				nodeStack.push(node.right);
			} else {
				TreeNode rootNodeEndofPostOrder = nodeStack.pop();
				int leftMax = nodePathCountMap.getOrDefault(rootNodeEndofPostOrder.left, 0);
				int rightMax = nodePathCountMap.getOrDefault(rootNodeEndofPostOrder.right, 0);
				int nodeMax = 1 + Math.max(leftMax, rightMax);
				nodePathCountMap.put(rootNodeEndofPostOrder, nodeMax);
				overallNodeMax = Math.max(overallNodeMax, leftMax + rightMax);
			}

		}
		return overallNodeMax;

	}
}
