package com.queue;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

 
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 */
public class QueueReconstruction {
	public int[][] reconstructQueue(int[][] people) {

		Arrays.sort(people, (int[] a, int[] b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			} else {
				return b[0] - a[0];
			}
		});

		ArrayList<int[]> temp = new ArrayList<int[]>();

		for (int[] p : people) {
			temp.add(p[1], p);
		}

		int[][] result = new int[people.length][2];
		for (int i = 0; i < people.length; i++) {
			result[i] = temp.get(i);
		}
		return result;
	}

	public static void main(String[] args) {
		QueueReconstruction q = new QueueReconstruction();

		System.out.println(Arrays.deepToString(
				q.reconstructQueue(new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } })));
	}
}
