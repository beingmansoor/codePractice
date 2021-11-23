package com.dp.linear;

public class BuySellWithTxFee
{
	
	/*
	 * Recurrence Relation:

Let noStock[i] denote the maximum profit achievable at the end of day i if we have no stock with us on day i.

Let hasStock[i] denote the maximum profit achievable at the end of day i if we have a stock with us on day i.

We can transition to hasStock[i] in two ways:
By doing nothing, i.e, transition from hasStock[i - 1].
From noStock[i - 1]: by buying one stock.
hasStock[i] = noStock[i - 1] - stockPrices[i]

We take the maximum of the above two, since our goal is to maximize profit:
hasStock[i] = Max(hasStock[i - 1], noStock[i - 1] - stockPrices[i])

noStock[i] can be reached by two ways:
From hasStock[i - 1] by selling stock and paying transaction fee.
noStock[i] = hasStock[i - 1] + stockPrices[i] - fee
From noStock state of day before by doing nothing on day i.
noStock[i] = noStock[i - 1]

We take the maximum of the above 2 since our goal is to maximize profit:
noStock[i] = Max(noStock[i - 1], hasStock[i - 1] + stockPrices[i] - transactionFee)
This leads us to the following relatios:

hasStock[i] = Max(hasStock[i - 1], noStock[i - 1] - stockPrices[i])

noStock[i] = Max(noStock[i - 1], hasStock[i - 1] + stockPrices[i] - transactionFee)
	 */
	 public int maxProfit(int[] prices, int fee) {
	        int days = prices.length;
	        if (days == 0) {
	            return 0;
	        }
	        int[] hasStock = new int[days]; // hasStock[i] denotes max profit achievable at 
	                // the end of day i if we have one stock with us at the end of day i
	        int[] noStock = new int[days]; // noStock[i] denotes max profit achievable at 
	                // the end of day i if we have no stock with us at the end of day i
	        
	        //base cases
	        hasStock[0] = -prices[0]; // if we buy stock on first day then at the end of day i we have negative of prices[0] as profit
	        noStock[0] = 0; // having no stock at the end of first day means we did not do any transaction. We have 0 profit
	        
	        for (int day = 1; day < days; day++) {
	            hasStock[day] = Math.max(hasStock[day - 1], // we can transition to hasStock state for today from hasStock state of yesterday by doing nothing
	                                    noStock[day - 1] - prices[day]); // we can transition to hasStock state for today from noStock state of yesterday by buying a stock today
	            noStock[day] = Math.max(noStock[day - 1], // we can transition to noStock state for today from noStock state of yesterday by doing nothing
	                                    hasStock[day - 1] + prices[day] - fee); // we can transition to noStock state for today from hasStock state of yesterday by selling a stock today. We need to pay transaction fee only when we sell a stock
	        }
	        
	        return noStock[days - 1]; // to maximize profit we cannot hold any stock with us on the last day
	    }
}
