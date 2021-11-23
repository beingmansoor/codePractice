package com.binary.search;

import java.util.Arrays;

/*
 * Given an array chocolate of n non-negative integers, where the values are sweetness levels of the chocolate. You are also given a value k which denotes the number of friends you will share this chocolate with. Your friends are greedy so they will always take the highest sweetness chunk. Find out what is the maximum sweetness level you could get.

tltr: Split the array into k non-empty continuous subarrays. Write an algorithm to maximize the minimum sum among these k subarrays.

Example:

Input: chocolate = [6, 3, 2, 8, 7, 5], k = 3
Output: 9
Explanation:
The values in array are sweetness level in each chunk of chocolate. Since k = 3, so you have to divide this array in 3 pieces,
such that you would get maximum out of the minimum sweetness level. So, you should divide this array in
[6, 3] -> 6 + 3 = 9
[2, 8] -> 2 + 8 = 10
[7, 5] -> 7 + 5 = 12
Your other two friends will take the sweetest chunk, so they will take 12 and 10. The maximum sweetness level you could get is 9.
 */
public class ChocolateSweetness
{
	public int chocolateSweetnessChunk(int chocolates[], int people)
	{

		if (null == chocolates || chocolates.length == 0 || people == 0)
			return 0;

		int low = Arrays.stream(chocolates).min().getAsInt();
		int high = Arrays.stream(chocolates).sum();
		int potentialSolution = 0;

		// O(n * log (sum - min)).
		while (low < high)
		{

			int sweetnessLevel = (low + high) >> 1;

			int requiredPeople = peopleRequired(chocolates, people, sweetnessLevel);

			if (requiredPeople > people)
			{
				low = sweetnessLevel + 1;

			}
			else
			{

				high = sweetnessLevel;
				potentialSolution = sweetnessLevel;

			}
		}

		return potentialSolution;
	}

	// O(n)
	private int peopleRequired(int[] chocolates, int people, int sweetnessLevel)
	{

		int minimumPeople = 1;
		int currentSweetness = 0;

		for (Integer s : chocolates)
		{

			currentSweetness += s;

			if (currentSweetness > sweetnessLevel)
			{
				minimumPeople++;
				currentSweetness = 0;

				if (minimumPeople > people)
					return minimumPeople;
			}

		}

		return minimumPeople;
	}
}
