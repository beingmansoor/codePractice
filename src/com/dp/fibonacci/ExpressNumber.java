package com.dp.fibonacci;

/*
 * Problem Statement #
Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’
 as the sum of 1, 3, or 4.

Example 1:

n : 4
Number of ways = 4
Explanation: Following are the four ways we can exoress 'n' : {1,1,1,1}, {1,3}, {3,1}, {4} 
Example 2:

n : 5
Number of ways = 6
Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1}, {1,1,3}, {1,3,1}, {3,1,1},
{1,4}, {4,1}
 */
public class ExpressNumber {

	public int CountWays(int n) {
	    int dp[] = new int[n+1];
	    dp[0] = 1;
	    dp[1] = 1;
	    dp[2] = 1;
	    dp[3] = 2;

	    for(int i=4; i<=n; i++)
	      dp[i] = dp[i-1] + dp[i-3] + dp[i-4];

	    return dp[n];
	  }
	
	public int CountWaysRec(int n) {
		if (n == 0)
			return 1; // base case, we don't need to subtract any thing, so
						// there is only one way

		if (n == 1)
			return 1; // we can subtract 1 to be left with zero, and that is the
						// only way

		if (n == 2)
			return 1; // we can subtract 1 twice to get zero and that is the
						// only way

		if (n == 3)
			return 2; // '3' can be expressed as {1,1,1}, {3}

		// if we subtract 1, we are left with 'n-1'
		int subtract1 = CountWaysRec(n - 1);
		// if we subtract 3, we are left with 'n-3'
		int subtract3 = CountWaysRec(n - 3);
		// if we subtract 4, we are left with 'n-4'
		int subtract4 = CountWaysRec(n - 4);

		return subtract1 + subtract3 + subtract4;
	}

	public static void main(String[] args) {
		ExpressNumber en = new ExpressNumber();
		System.out.println(en.CountWaysRec(4));
		System.out.println(en.CountWaysRec(5));
		System.out.println(en.CountWaysRec(6));
	}
}
