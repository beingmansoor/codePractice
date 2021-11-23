package com.dp.linear;

import java.util.Arrays;

public class BuySell11 {

	
	//STATE MACHINE DP Approach
    public int maxProfit_StateMachine(int[] prices) {
        int days = prices.length;
        if (days == 0) {
            return 0;
        }
        int[][] noStock = new int[days][2]; // noStock[i][j] denotes maximum profit achievable 
            // by the end of day i with (j + 1) transactions completed with no stock in 
            // hand at the end of day 1. j = 0 or 1, that way (j + 1) can be either 1 or 2
        int[][] hasStock = new int[days][2]; // hasStock[i][j] denotes maximum profit achievable 
            // by the end of day i with (j + 1) transactions completed with 1 stock in hand at the end
            // of day 1. j = 0 or 1, that way (j + 1) can be either 1 or 2
        
        //Base conditions
        // First day - 1 transaction
        hasStock[0][0] = -prices[0];
        noStock[0][0] = 0; 
        // First day - 2 transactions
        noStock[0][1] = 0; // On the end of first day we are in noStock state and already two transactions have been made.
            // This means we have purchased and sold as part of first transaction, and have again purchased and sold
            // as part of second transaction. Since all both the purchases and sales have been made at the same price
            // we have made zero profit. Net profit = 0
        hasStock[0][1] = -prices[0]; 
        
        for (int day = 1; day < days; day++) { 
            // 1 transaction by the end of day-th day
            hasStock[day][0] = Math.max(hasStock[day - 1][0], -prices[day]); // hasStock[i][0] cannot come from noStock[i - 1][0]. 
                // It has to come from noStock[i - 1][-1]. noStock[i - 1][-1] denotes 
                // noStock value with ZERO transaction till end of day (i - 1). 
                // noStock[i - 1][-1] is always 0, since there has been no transaction.  
                // hasStock[day][0] = noStock[day - 1][-1] - prices[day] = 0 - prices[day] = -prices[day]
            noStock[day][0] = Math.max(noStock[day - 1][0], hasStock[day - 1][0] + prices[day]);
            
            // 2 transactions by the end of day-th day
            hasStock[day][1] = Math.max(hasStock[day - 1][1], noStock[day - 1][0] - prices[day]);
            noStock[day][1] = Math.max(noStock[day - 1][1], hasStock[day - 1][1] + prices[day]);
        }
        
       return noStock[days - 1][1];
        /*
        Example:
        [30,30,15,10,10,3,1,1]
        result: 0
        ZERO Transaction
        */
    }
    
	// Version 1:Time complexity is O(kn^2), space complexity is O(kn).
	public int maxProfit3(int[] prices) {
		int n = prices.length;
		if (n == 0)
			return 0;
		int[][] dp = new int[3][n];
		for (int k = 1; k <= 2; k++) {
			for (int i = 1; i < n; i++) {
				int min = prices[0];
				for (int j = 1; j <= i; j++) {
					min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
				}
				dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
			}
		}
		return dp[2][n - 1];
	}

	// Version 2: Get rid of repeating calculation of min
	// Time complexity is O(kn), space complexity is O(kn).
	public int maxProfit2(int[] prices) {
		int n = prices.length;
		if (n == 0)
			return 0;
		int[][] dp = new int[3][n];
		for (int k = 1; k <= 2; k++) {
			int min = prices[0];
			for (int i = 1; i < n; i++) {
				min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
				dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
			}
		}
		return dp[2][n - 1];
	}

	// Version 3
	// swap the two 'for' loops,save min for each transaction.
	class Solution {
		public int maxProfit(int[] prices) {
			int n = prices.length;
			if (n == 0)
				return 0;
			int[][] dp = new int[3][n];
			int[] min = new int[3];
			Arrays.fill(min, prices[0]);
			for (int i = 1; i < n; i++) {
				for (int k = 1; k <= 2; k++) {
					min[k] = Math.min(min[k], prices[i] - dp[k - 1][i - 1]);
					dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min[k]);
				}
			}
			return dp[2][n - 1];
		}
	}

	// Version 4: compact i
	public int maxProfit(int[] prices) {
		int n = prices.length;
		if (n == 0)
			return 0;
		int[] dp = new int[3];
		int[] min = new int[3];
		Arrays.fill(min, prices[0]);
		for (int i = 1; i < n; i++) {
			for (int k = 1; k <= 2; k++) {
				min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
				dp[k] = Math.max(dp[k], prices[i] - min[k]);
			}
		}
		return dp[2];
	}

	// Version 5
	class Solution2 {
		public int maxProfit(int[] prices) {
			int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
			int sell1 = 0, sell2 = 0;
			for (int i = 0; i < prices.length; i++) {
				buy1 = Math.min(buy1, prices[i]);
				sell1 = Math.max(sell1, prices[i] - buy1);
				buy2 = Math.min(buy2, prices[i] - sell1);
				sell2 = Math.max(sell2, prices[i] - buy2);
			}
			return sell2;
		}
	}
}
