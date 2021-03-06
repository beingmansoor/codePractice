package com.dp.knapsack01;

public class TargetSum
{

	/*
	 * You are given a set of positive numbers and a target sum ?S?. Each number
	 * should be assigned either a ?+? or ?-? sign. We need to find the total
	 * ways to assign symbols to make the sum of the numbers equal to the target
	 * ?S?.
	 * 
	 * Example 1: # Input: {1, 1, 2, 3}, S=1 Output: 3 Explanation: The given
	 * set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} &
	 * {+1+1+2-3} Example 2: # Input: {1, 2, 7, 1}, S=9 Output: 2 Explanation:
	 * The given set has '2' ways to make a sum of '9': {+1+2+7-1} & {-1+2+7+1}
	 * 
	 * 
	 * Solution # This problem follows the 0/1 Knapsack pattern and can be
	 * converted into Count of Subset Sum. Let?s dig into this.
	 * 
	 * We are asked to find two subsets of the given numbers whose difference is
	 * equal to the given target ?S?. Take the first example above. As we saw,
	 * one solution is {+1-1-2+3}. So, the two subsets we are asked to find are
	 * {1, 3} & {1, 2} because,
	 * 
	 * (1 + 3) - (1 + 2 ) = 1 Now, let?s say ?Sum(s1)? denotes the total sum of
	 * set ?s1?, and ?Sum(s2)? denotes the total sum of set ?s2?. So the
	 * required equation is:
	 * 
	 * Sum(s1) - Sum(s2) = S This equation can be reduced to the subset sum
	 * problem. Let?s assume that ?Sum(num)? denotes the total sum of all the
	 * numbers, therefore:
	 * 
	 * Sum(s1) + Sum(s2) = Sum(num) Let?s add the above two equations:
	 * 
	 * => Sum(s1) - Sum(s2) + Sum(s1) + Sum(s2) = S + Sum(num) => 2 * Sum(s1) =
	 * S + Sum(num) => Sum(s1) = (S + Sum(num)) / 2 Which means that one of the
	 * set ?s1? has a sum equal to (S + Sum(num)) / 2. This essentially converts
	 * our problem to:
	 * "Find the count of subsets of the given numbers whose sum is equal to (S + Sum(num)) / 2"
	 */

	public int findTargetSumWays(int[] nums, int target)
	{

		int sum = 0;
		for (int a : nums)
		{
			sum += a;
		}
		if (target > sum || target < -sum)
		{
			return 0;
		}
		if ((target + sum) % 2 != 0)
		{
			return 0;
		}
		int subsetSum = (target + sum) / 2;
		int[] dp = new int[subsetSum + 1]; // dp[i] = number of unique subsets
											// that sum up to i
		dp[0] = 1; // dp[0] is initially 0 (before iterating the array)
		// since we can get 0 by having an empty subset.
		// Eventually it gets updated as we iterate over the array if
		// we happen encounter one or more subset(s) giving zero
		for (int i = 0; i < nums.length; i++)
		{
			for (int weight = subsetSum; weight >= nums[i]; weight--)
			{
				dp[weight] += dp[weight - nums[i]];
			}
		}
		return dp[subsetSum];
	}

	public int findTargetSubsets1(int[] num, int s)
	{
		int totalSum = 0;
		for (int n : num)
			totalSum += n;

		// if 's + totalSum' is odd, we can't find a subset with sum equal to
		// '(s + totalSum) / 2'
		if (totalSum < s || (s + totalSum) % 2 == 1)
			return 0;

		return countSubsets(num, (s + totalSum) / 2);
	}

	public int findTargetSubsets(int[] num, int s)
	{
		int totalSum = 0;
		for (int n : num)
			totalSum += n;

		// if 's + totalSum' is odd, we can't find a subset with sum equal to
		// '(s + totalSum) / 2'
		if (totalSum < s || (s + totalSum) % 2 == 1)
			return 0;

		return countSubsets(num, (s + totalSum) / 2);
	}

	// this function is exactly similar to what we have in 'Count of Subset Sum'
	// problem.
	private int countSubsets(int[] num, int sum)
	{
		int n = num.length;
		int[] dp = new int[sum + 1];
		dp[0] = 1;

		// with only one number, we can form a subset only when the required sum
		// is equal to the number
		for (int s = 1; s <= sum; s++)
		{
			dp[s] = (num[0] == s ? 1 : 0);
		}

		// process all subsets for all sums
		for (int i = 1; i < num.length; i++)
		{
			for (int s = sum; s >= 0; s--)
			{
				if (s >= num[i])
					dp[s] += dp[s - num[i]];
			}
		}

		return dp[sum];
	}

	// this function is exactly similar to what we have in 'Count of Subset Sum'
	// problem.
	private int countSubsets1(int[] num, int sum)
	{
		int n = num.length;
		int[][] dp = new int[n][sum + 1];

		// populate the sum=0 columns, as we will always have an empty set for
		// zero sum
		for (int i = 0; i < n; i++)
			dp[i][0] = 1;

		// with only one number, we can form a subset only when the required sum
		// is equal to the number
		for (int s = 1; s <= sum; s++)
		{
			dp[0][s] = (num[0] == s ? 1 : 0);
		}

		// process all subsets for all sums
		for (int i = 1; i < num.length; i++)
		{
			for (int s = 1; s <= sum; s++)
			{
				dp[i][s] = dp[i - 1][s];
				if (s >= num[i])
					dp[i][s] += dp[i - 1][s - num[i]];
			}
		}

		// the bottom-right corner will have our answer.
		return dp[num.length - 1][sum];
	}

	public static void main(String[] args)
	{
		TargetSum ts = new TargetSum();
		int[] num = { 1, 1, 2, 3 };
		System.out.println(ts.findTargetSubsets(num, 1));
		num = new int[] { 1, 2, 7, 1 };
		System.out.println(ts.findTargetSubsets(num, 9));
	}
}
