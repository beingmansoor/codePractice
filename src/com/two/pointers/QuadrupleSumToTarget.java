package com.two.pointers;

import java.util.*;

/*
 * 
 * Given an array of unsorted numbers and a target number, find all unique quadruplets in it,
 *  whose sum is equal to the target number.

	Example 1:
	
	Input: [4, 1, 2, -1, 1, -3], target=1
	Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
	Explanation: Both the quadruplets add up to the target.
	Example 2:
	
	Input: [2, 0, -1, 1, -2, 2], target=2
	Output: [-2, 0, 2, 2], [-1, 0, 1, 2]
	Explanation: Both the quadruplets add up to the target.
	
	
	Time complexity #
Sorting the array will take O(N*logN)O(N∗logN). Overall searchQuadruplets() will take O(N * logN + N^3)O(N∗logN+N
​3
​​ ), which is asymptotically equivalent to O(N^3)O(N
​3
​​ ).

Space complexity #
The space complexity of the above algorithm will be O(N)O(N) which is required for sorting.

 */
public class QuadrupleSumToTarget {

	public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
		Arrays.sort(arr);
		List<List<Integer>> quadruplets = new ArrayList<>();
		for (int i = 0; i < arr.length - 3; i++) {
			if (i > 0 && arr[i] == arr[i - 1]) // skip same element to avoid
												// duplicate quadruplets
				continue;
			for (int j = i + 1; j < arr.length - 2; j++) {
				if (j > i + 1 && arr[j] == arr[j - 1]) // skip same element to
														// avoid duplicate
														// quadruplets
					continue;
				searchPairs(arr, target, i, j, quadruplets);
			}
		}
		return quadruplets;
	}

	private static void searchPairs(int[] arr, int targetSum, int first, int second, List<List<Integer>> quadruplets) {
		int left = second + 1;
		int right = arr.length - 1;
		while (left < right) {
			int sum = arr[first] + arr[second] + arr[left] + arr[right];
			if (sum == targetSum) { // found the quadruplet
				quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
				left++;
				right--;
				while (left < right && arr[left] == arr[left - 1])
					left++; // skip same element to avoid duplicate quadruplets
				while (left < right && arr[right] == arr[right + 1])
					right--; // skip same element to avoid duplicate quadruplets
			} else if (sum < targetSum)
				left++; // we need a pair with a bigger sum
			else
				right--; // we need a pair with a smaller sum
		}
	}

	public static List<List<Integer>> searchQuadruplets1(int[] arr, int target) {
		List<List<Integer>> quadruplets = new ArrayList<>();
		Arrays.sort(arr);

		for (int i = 0; i < arr.length - 3; i++) {
			if (i > 0 && arr[i] == arr[i - 1]) // skip same element to avoid
												// duplicate quadruplets
				continue;

			int sum = 0;

			for (int j = i + 1; j < arr.length - 2; j++) {
				if (j > i + 1 && arr[j] == arr[j - 1]) // skip same element to
														// avoid duplicate
														// quadruplets
					continue;

				int l = j + 1, r = arr.length - 1;
				while (l < r) {
					sum = (arr[i] + arr[j] + arr[l] + arr[r]);
					if (sum == target) {
						quadruplets.add(Arrays.asList(arr[i], arr[j], arr[l], arr[r]));
						l++;
						r--;

						// skip duplicates
						while (l < r && arr[l] == arr[l + 1])
							l++;
						while (l < r && arr[r] == arr[r - 1])
							r--;

					} else if (sum < target) {
						l++;
					} else {
						r--;
					}
				}
			}
		}
		return quadruplets;
	}

	public static void main(String[] args) {
		System.out.println(QuadrupleSumToTarget.searchQuadruplets1(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
		System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
	}

}