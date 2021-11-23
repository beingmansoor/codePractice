package com.dp.linear;

public class BuySell1 {

	public static int maxProfit(int[] prices) {
		int maxP = 0, min = Integer.MAX_VALUE;

		for (int i : prices) {
			min = Math.min(min, i);
			maxP = Math.max(maxP, i - min);
		}

		return maxP;
	}

	public static int maxProfit_DP(int[] prices) {
		int minPrice = Integer.MAX_VALUE;

		int n = prices.length;
		int[] dp = new int[n];
		for (int i = 1; i < prices.length; i++) {
			dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
			minPrice = Math.min(minPrice, prices[i]);
		}

		return dp[n-1];
	}

	public static int maxProfit_StateMachine(int[] prices) {
        int days = prices.length;
        if (prices == null || days < 2) {
            return 0;
        }
        int[] hasStock = new int[days]; // hasStock[i] = max profit we can have at the end of day i with
                                        // one stock in possession, following the transition relation in
                                        // the state machine diagram
        int[] noStock = new int[days]; // noStock[i] = max profit at the end of day i with no stock at hand
                                       // following the transition relation in the state machine diagram
        
        // Base Condition
        hasStock[0] = -prices[0]; // we need to have one stock in our possession mandatorily
        noStock[0] = 0; // on first day there is no stock to sell. So, noStock[0] means 
                // we did not make any transaction at all on the first day  
                // giving us 0 net profit
        
        for (int day = 1; day < days; day++) {
            hasStock[day] = Math.max(hasStock[day - 1], -prices[day]);
            noStock[day] = Math.max(noStock[day - 1], hasStock[day - 1] + prices[day]);
        }
        return noStock[days - 1];
        
    }
	
	public int maxProfit_Kadane(int[] prices) {
		int maxCur = 0, maxSoFar = 0;
		for (int i = 1; i < prices.length; i++) {
			maxCur = Math.max(0, maxCur += prices[i] - prices[i - 1]);
			maxSoFar = Math.max(maxCur, maxSoFar);
		}
		return maxSoFar;
	}

	public static void main(String[] args) {
		System.out.println(maxProfit_StateMachine(new int[] { 7, 1, 5, 3, 6, 4 }));
	}
}
