package com.array;

import java.util.Arrays;

public class CountWaysToMinJumps {
	// function to count ways to jump to
	// reach end for each array element
	static void countWaysToJump(int arr[], int n) {

		// count_jump[i] store number of ways
		// arr[i] can reach to the end
		int count_jump[] = new int[n];
		Arrays.fill(count_jump, 0);

		// Last element does not require to jump.
		// Count ways to jump for remaining
		// elements
		for (int i = n - 2; i >= 0; i--) {

			// if the element can directly
			// jump to the end
			if (arr[i] >= n - i - 1)
				count_jump[i]++;

			// add the count of all the elements
			// that can reach to end and arr[i] can
			// reach to them
			for (int j = i + 1; j < n - 1 && j <= arr[i] + i; j++)

				// if element can reach to end then add
				// its count to count_jump[i]
				if (count_jump[j] != -1)
					count_jump[i] += count_jump[j];

			// if arr[i] cannot reach to the end
			if (count_jump[i] == 0)
				count_jump[i] = -1;
		}

		// print count_jump for each
		// array element
		for (int i = 0; i < n; i++)
			System.out.print(count_jump[i] + " ");
	}

	// driver code
	public static void main(String[] args) {

		int arr[] = { 1, 3, 5, 8, 9, 1, 0, 7, 6, 8, 9 };
		int n = arr.length;

		countWaysToJump(arr, n);
		System.out.println();

		countWaysToJump(new int[] { 3, 2, 0, 1 }, 4);
	}
}
