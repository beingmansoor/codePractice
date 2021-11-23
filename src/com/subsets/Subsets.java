package com.subsets;

import java.util.*;

public class Subsets {

	public static List<List<Integer>> findSubsets(int[] nums) {
		List<List<Integer>> subsets = new ArrayList<>();
		// start by adding the empty subset
		subsets.add(new ArrayList<>());
		for (int currentNumber : nums) {
			// we will take all existing subsets and insert the current number
			// in them to create new subsets
			int n = subsets.size();
			for (int i = 0; i < n; i++) {
				// create a new subset from the existing subset and insert the
				// current element to it
				List<Integer> set = new ArrayList<>(subsets.get(i));
				set.add(currentNumber);
				subsets.add(set);
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		List<List<Integer>> result = Subsets.findSubsets(new int[] { 1, 3 });
		System.out.println("Here is the list of subsets: " + result);

		result = Subsets.findSubsets(new int[] { 1, 5, 3 });
		System.out.println("Here is the list of subsets: " + result);
	}

	/*
	 * Solution # To generate all subsets of the given set, we can use the
	 * Breadth First Search (BFS) approach. We can start with an empty set,
	 * iterate through all numbers one-by-one, and add them to existing sets to
	 * create new subsets.
	 * 
	 * Let’s take the example-2 mentioned above to go through each step of our
	 * algorithm:
	 * 
	 * Given set: [1, 5, 3]
	 * 
	 * Start with an empty set: [[]] Add the first number (1) to all the
	 * existing subsets to create new subsets: [[], [1]]; Add the second number
	 * (5) to all the existing subsets: [[], [1], [5], [1,5]]; Add the third
	 * number (3) to all the existing subsets: [[], [1], [5], [1,5], [3], [1,3],
	 * [5,3], [1,5,3]].
	 * 
	 * Time complexity # Since, in each step, the number of subsets doubles as
	 * we add each element to all the existing subsets, the time complexity of
	 * the above algorithm is O(2^N), where ‘N’ is the total number of elements
	 * in the input set. This also means that, in the end, we will have a total
	 * of O(2^N)subsets.
	 * 
	 * Space complexity # All the additional space used by our algorithm is for
	 * the output list. Since we will have a total of O(2^N) subsets, the space
	 * complexity of our algorithm is also O(2^N).
	 */
}
