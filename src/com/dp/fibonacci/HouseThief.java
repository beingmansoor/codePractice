package com.dp.fibonacci;

/*
 * Given a number array representing the wealth of ‘n’ houses, 
 * determine the maximum amount of money the thief can steal without alerting the security system.
 * 

Example 1:

Input: {2, 5, 1, 3, 6, 2, 4}
Output: 15
Explanation: The thief should steal from houses 5 + 6 + 4
Example 2:

Input: {2, 10, 14, 8, 1}
Output: 18
Explanation: The thief should steal from houses 10 + 8
 */
public class HouseThief {

	public int findMaxSteal1(int[] wealth) {
		return findMaxStealRecursive(wealth, 0);
	}

	public int findMaxStealMemoization(int[] wealth) {
		if (wealth.length == 0)
			return 0;
		int n1 = 0, n2 = wealth[0], temp;
		for (int i = 1; i < wealth.length; i++) {
			temp = Math.max(n1 + wealth[i], n2);
			n1 = n2;
			n2 = temp;
		}
		return n2;
	}
	
	public int rob(int[] nums) {
	    if (nums.length == 0) return 0;
	    int prev1 = 0;
	    int prev2 = 0;
	    for (int num : nums) {
	        int tmp = prev1;
	        prev1 = Math.max(prev2 + num, prev1);
	        prev2 = tmp;
	    }
	    return prev1;
	}
	
	public int robRec(int[] nums) {
	    return rob(nums, nums.length - 1);
	}
	private int rob(int[] nums, int i) {
	    if (i < 0) {
	        return 0;
	    }
	    return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
	}

	public int findMaxSteal(int[] wealth) {
		if (wealth.length == 0)
			return 0;
		int dp[] = new int[wealth.length + 1]; // '+1' to handle the zero house
		dp[0] = 0; // if there are no houses, the thief can't steal anything
		dp[1] = wealth[0]; // only one house, so the thief have to steal from it

		// please note that dp[] has one extra element to handle zero house
		for (int i = 1; i < wealth.length; i++)
			dp[i + 1] = Math.max(wealth[i] + dp[i - 1], dp[i]);

		return dp[wealth.length];
	}

	private int findMaxStealRecursive(int[] wealth, int currentIndex) {
		if (currentIndex >= wealth.length)
			return 0;

		// steal from current house and skip one to steal from the next house
		int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(wealth, currentIndex + 2);
		// skip current house to steel from the adjacent house
		int skipCurrent = findMaxStealRecursive(wealth, currentIndex + 1);

		return Math.max(stealCurrent, skipCurrent);
	}

	public int rob_kadane(int[] nums) {
        if (nums.length == 0) {
            return 0;    
        }
        
        int n = nums.length;
        //int[][] dp = new int[n][2]; 
        
        //dp[0][0] = 0;
        int previousItemExcluded = 0; // base case
        //dp[0][1] = nums[0];
        int previousItemIncluded = nums[0]; // base case
        
        int currentItemIncluded = 0;
        int currentItemExcluded = 0;
        
        for (int i = 1; i < n; i++) {
            currentItemIncluded = previousItemExcluded + nums[i];
            currentItemExcluded = Math.max(previousItemIncluded, previousItemExcluded);
            
            previousItemExcluded = currentItemExcluded;
            previousItemIncluded = currentItemIncluded;
        }
        return Math.max(previousItemExcluded, previousItemIncluded);
    }
	public static void main(String[] args) {
		HouseThief ht = new HouseThief();
		int[] wealth = { 2, 5, 1, 3, 6, 2, 4 };
		System.out.println(ht.findMaxSteal(wealth));
		wealth = new int[] { 2, 10, 14, 8, 1 };
		System.out.println(ht.findMaxSteal(wealth));
	}
}
