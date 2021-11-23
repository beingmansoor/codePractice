package com.dp.lis;

import java.util.Arrays;
import java.util.Comparator;

/*

Problem Statement:

You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
One envelope can fit into another if and only if both the width and height of one envelope is 
greater than the width and height of the other envelope.
What is the maximum number of envelopes can you Russian doll? (put one inside other)
Note:
Rotation is not allowed.



 
 */
public class RussianDollEnvelopes
{
	public int maxEnvelopes(int[][] envelopes)
	{
		if (envelopes == null || envelopes.length == 0)
		{
			return 0;
		}
		// Just like in Box stacking example, we do ascending order
		// sorting on area. Since One envelope can fit into another
		// if and only if both the width and height of one envelope
		// is greater than the width and height of the other envelope
		// this means if envelope E1 goes inside emvelope E2
		// then area of E2 > area of E1
		Arrays.sort(envelopes, new Comparator<int[]>()
		{
			public int compare(int[] arr1, int[] arr2)
			{
				return (arr1[0] * arr1[1]) - (arr2[0] * arr2[1]);
			}
		});
		int len = envelopes.length;
		int[] longestIncreasingSubsequence = new int[len];
		Arrays.fill(longestIncreasingSubsequence, 1); // common step for most
														// LIS (Longest
														// Increasing
														// Subsequence) based
														// solution
		int max = 1;
		// Perform Longest Increasing Subsequence operation
		for (int i = 1; i < len; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
				{
					longestIncreasingSubsequence[i] = Math.max(longestIncreasingSubsequence[i],
							longestIncreasingSubsequence[j] + 1);
				}
			}
			max = Math.max(max, longestIncreasingSubsequence[i]);
		}
		return max;
	}
}
