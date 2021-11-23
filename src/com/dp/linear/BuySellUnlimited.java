package com.dp.linear;

/*
 * Problem Statement:
Say you have an array prices for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
Example 1:
Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4. Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4. Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BuySellUnlimited
{
	public int maxProfit(int[] prices)
	{
		int days = prices.length;
		if (prices == null || days < 2)
		{ // if days < 2 net profit = 0 since we
			// cannot sell. For selling you at least need 2 days since
			// ou may not engage in multiple transactions at the
			// same time (i.e., you must sell the stock before you buy again).
			return 0;
		}
		int[] noStock = new int[days];
		int[] hasStock = new int[days];

		// Base Condition
		noStock[0] = 0; // no stock at the end of first day means we did not do
						// anything
		// all day first day. We started with zero stock and
		// we ended the day with zero stock. So profit = 0
		hasStock[0] = -prices[0]; // it is first day so we started with net
									// profit = 0 and then we bought a stock, so
									// at the end
		// of first day profit = negative of prices[0]

		for (int day = 1; day < days; day++)
		{
			noStock[day] = Math.max(noStock[day - 1], hasStock[day - 1] + prices[day]);
			hasStock[day] = Math.max(hasStock[day - 1], noStock[day - 1] - prices[day]);
		}
		return noStock[days - 1];
	}
}
