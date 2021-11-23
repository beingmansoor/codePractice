package com.subArrays;

public class MaxPoints
{
	public static int maxPoints(int[] deck, int k)
	{
		int left = 0;
		int right = deck.length - k;
		int total, best;
		total = 0;
		for (int i = right; i < deck.length; i++)
		{
			total += deck[i];
		}
		best = total;
		for (int i = 0; i < k; i++)
		{
			total += deck[left] - deck[right];
			best = Math.max(best, total);
			left += 1;
			right += 1;
		}
		return best;
	}

	public static void main(String args[])
	{
		int[] deck = { 5, 3, 4, 4, 2, 3, 2, 6, 3 };
		int k = 4;
		System.out.println(maxPoints(deck, k));
	}
}
