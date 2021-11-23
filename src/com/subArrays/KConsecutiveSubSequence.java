package com.subArrays;

import java.util.HashMap;

public class KConsecutiveSubSequence
{
	public static boolean goalsFulfilled(int[] trades)
	{
		Counter frequencyMap = new Counter();
		Counter imaginedMap = new Counter();

		for (int n : trades)
			frequencyMap.add(n, 1);

		for (int n : trades)
		{

			if (frequencyMap.get(n) == 0)
			{
				continue;
			}

			else if (imaginedMap.get(n) > 0)
			{
				// Adding number to existing sequence
				imaginedMap.add(n, -1);
				imaginedMap.add(n + 1, 1);
			}

			else if (frequencyMap.get(n + 1) > 0 && frequencyMap.get(n + 2) > 0)
			{
				// Creating new subsequence
				frequencyMap.add(n + 1, -1);
				frequencyMap.add(n + 2, -1);
				imaginedMap.add(n + 3, 1);
			}

			else
			{
				return false;
			}

			frequencyMap.add(n, -1);
		}

		return true;
	}

	public static void main(String args[])
	{
		// Driver code

		int[] trades = { 1, 2, 3, 3, 4, 4, 5, 5 };

		if (goalsFulfilled(trades) == false)
		{
			System.out.println("The goals have not been fulfilled!");
		}
		else
		{
			System.out.println("The goals have been fulfilled!");
		}

	}
}

// extending class to get frequency of elements
class Counter extends HashMap<Integer, Integer>
{
	public int get(int k)
	{
		return containsKey(k) ? super.get(k) : 0;
	}

	public void add(int k, int v)
	{
		put(k, get(k) + v);
	}
}
