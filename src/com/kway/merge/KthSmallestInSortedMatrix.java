package com.kway.merge;

import java.util.PriorityQueue;

class Node1 {
	int row;
	int col;

	Node1(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

/*
 * Given an N * NN∗N matrix where each row and column is sorted in ascending
 * order, find the Kth smallest element in the matrix.
 * 
 * Example 1:
 * 
 * Input: Matrix=[ [2, 6, 8], [3, 7, 10], [5, 8, 11] ], K=5 Output: 7
 * Explanation: The 5th smallest number in the matrix is 7.
 * 
 * Solution # This problem follows the K-way merge pattern and can be easily
 * converted to Kth Smallest Number in M Sorted Lists. As each row (or column)
 * of the given matrix can be seen as a sorted list, we essentially need to find
 * the Kth smallest number in ‘N’ sorted lists.
 */
public class KthSmallestInSortedMatrix {

	public static int findKthSmallest(int[][] matrix, int k) {
		PriorityQueue<Node1> minHeap = new PriorityQueue<Node1>(
				(n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);

		// put the 1st element of each row in the min heap
		// we don't need to push more than 'k' elements in the heap
		for (int i = 0; i < matrix.length && i < k; i++)
			minHeap.add(new Node1(i, 0));

		// take the smallest (top) element form the min heap, if the running
		// count is equal to k return the number
		// if the row of the top element has more elements, add the next element
		// to the heap
		int numberCount = 0, result = 0;
		while (!minHeap.isEmpty()) {
			Node1 node = minHeap.poll();
			result = matrix[node.row][node.col];
			if (++numberCount == k)
				break;
			node.col++;
			if (matrix[0].length > node.col)
				minHeap.add(node);
		}
		return result;
	}

	public static void main(String[] args) {
		int matrix[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
		int result = KthSmallestInSortedMatrix.findKthSmallest(matrix, 5);
		System.out.print("Kth smallest number is: " + result);
	}
}