package com.tree;

public class SumRootToLeafNodes {

	public int sumNumbers(TreeNode root) {
		return populateSum(root, 0);
	}

	private int populateSum(TreeNode root, int s) {

		if (null == root) {
			return 0;
		}
		int sum = s * 10 + root.val;
		if (root.left == null && root.right == null) {
			return sum;
		}
		return populateSum(root.left, sum) + populateSum(root.right, sum);
	}

	public static void main(String[] args) {
		SumRootToLeafNodes s = new SumRootToLeafNodes();
		TreeNode r = new TreeNode(4);
		r.left = new TreeNode(9);
		r.left.left = new TreeNode(5);
		r.left.right = new TreeNode(1);
		r.right = new TreeNode(0);

		int sumNumbers = s.sumNumbers(r);
		System.out.println(sumNumbers);
	}

}
