package com.dp.linear;

public class BuySell_Ktxs
{
	// STATE MACHINE Approach
    public int maxProfit(int k, int[] prices) {
       int days = prices.length;
       if (k == 0 || prices == null || days < 2) {
           return 0;
       }
       if (k > days) { 
           // If we are allowed to make more transaction
           // than the total number of days
           // and the goal is still maximizing the profit then
           // the problem becomes similar to finding max profit
           // when you can make as many transactions as possible
           // with NO overlapping transaction
           // This specific problem is discussed in details 
           // at https://www.thealgorists.com/Algo/DynamicProgramming/MaxProfitWithUnlimitedStockTradingTransactions
           return allTimeProfit(prices);
       }
       int[][] noStock = new int[days][k + 1]; // we are accounting
                // for 0 transaction as well, therefore transaction = [0...k], 
                // which justifies the array size int[days][k + 1] and not int[days][k]
       int[][] hasStock = new int[days][k + 1]; // we are accounting
                // for 0 transaction as well, 
                // therefore transaction = [0...k], which
                // justifies the size [days][k + 1] and not [days][k]
       
       // Base cases:
       noStock[0][0] = 0; // no stock at the end of first
               // day means we did nothing on first day and so net profit = 0
               // with ZERO transaction
       noStock[0][1] = 0; // no stock at the end of first day means we 
               // did nothing on first day and so net profit = 0 with one transaction.
               // This means we bought a stock on first day and sold the stock at the same price
               // so net profit = 0
       hasStock[0][1] = -prices[0]; // if we end up having a
               // stock with us on first day
               // then that would mean that
               // we started the day with zero
               // profit (obviously, since it is first day)
               // and ended up buying a
               // stock. So net profit = 0 - prices[0] = -prices[0]
               // for tranaction#1
       
       
       // Now lets calculate for transaction# > 1 on the first day
       // 
       // Now let's first see what it means to
       // make more than one transation on the first day.
       // Making N number of transactions on first day (where N > 1)
       // and ending up in hasStock state would make that in we completed 
       // (N - 1) transactions just to end up with 0
       // profit (because in every transaction we buy and sell
       // stock at the same price giving us net profit = 0) and then buy one stock.
       // So irrespective of whatever the value of N is,
       // net profit = 0 - prices[0] = -prices[0] for hasStock state.
       // 
       // Making N number of transactions on first day (where N > 1)
       // and ending up in hasStock state would make that in we completed 
       // (N - 1) transactions just to end up with 0 profit (because
       // in every transaction we buy and sell
       // stock at the same price giving us net profit = 0) and then
       // as part of transaction# N we buy a stock and
       // then sell the stok as well. 
       // Since all these transactions are done on
       // the same day (first day), we made all 
       // purchases and sales at the same stock
       // price giving us max profit = 0.
       for (int transaction = 2; transaction <= k; transaction++) {
           hasStock[0][transaction] = -prices[0]; 
           noStock[0][transaction] = 0; 
       }
       
       for (int day = 1; day < days; day++) {
           // next three lines apply only for first transaction
           noStock[day][1] = Math.max(noStock[day - 1][1], hasStock[day - 1][1] + prices[day]); 
           hasStock[day][1] = Math.max(hasStock[day - 1][1], - prices[day]); // irrespective of which day it is
               // hasStock value for the first transaction will always yield -prices[day] profit.
               // Since it is first transaction, we had no profit till then. And on top of that
               // we purchased a stock. So profit for hasStock state = -prices[day]
           
           // now lets process for second transaction and onwards
           for (int transaction = 2; transaction <= k; transaction++) {

               // Important thing to keep in mind:
               // if a state gets transitioned from a different state
               // it always does so from the different state of the DAY BEFORE
               
               // noStock state can be reached from noStock state (for the
               // same transaction) of day before by doing nothing.
               // noStock state can also be reached from hasStock
               // state (FOR THE SAME TRANSACTION) of day before
               // by selling the stock purchased.
               noStock[day][transaction] = Math.max(noStock[day - 1][transaction],
                                     hasStock[day - 1][transaction] + prices[day]);

               // hasStock state can be reached from hasStock
               // state (for the same transaction) of day before by doing nothing
               // hasStock state can also be reached from noStock state
               // from day before WHICH WAS PART OF
               // PREVIOUS TRANSACTION, by buying a stock.
               hasStock[day][transaction] = Math.max(hasStock[day - 1][transaction],
                                    noStock[day - 1][transaction - 1] - prices[day]);
           }
       }
       return noStock[days - 1][k]; // the noStock state for the last
           // day for k-th transaction will hold the max profit
           //
           // We can never achieve maximum profit if we hold a stock on the last day. So
           // the return value will be the maximum of profit we get on the last day
           // by being in noStock state transactions done = k.
           // You might argue and come up with examples where
           // less than k transactions would give you the maximum
           // profit and you not need to do any additional transaction(s).
           // But convince yourself that by doing additional unnecessary
           // transactions you won't miss out on getting the maximum
           // profit since any unnecessary additional transaction would
           // yield zero more profit. So by returning
           // the noStock value for maximum trasactions (k in our case)
           // you are also capturing maximum profit achievable from from lesser 
           // number of transactions. So for example if 1 transaction gives
           // maximum profit, then 2 transactions will also give the same profit. Let's
           // take an example: for the stock prices [5,5,7] and k = 2,
           // you only need to make one transaction, that is buy stock on first
           // day and sell either on second or third
           // day getting maximum profit of 2. However you would get the
           // same maximum profit by doing 2 transactions instead of one.
           // Buy stock on first day and sell on second day. Buy again on
           // second day and sell on third giving you the maximum
           // profit of (7 - 5) + (5 - 5) = 2. Either way you get
           // same result in case we do not need all 2 transactions to
           // achieve maximum profit. Any unnecessary transactions
           // yield 0 profit, that is why we do not see any error
           // in returning noStock result for last day for
           // 2nd< transaction even when maximum profit
           // can be reached by doing 0 or 1 transaction.
           // YOU NEED TO KEEP IN MIND
           // THAT IN EVERY STAGE WE ARE CHOOSING THE MAXIMUM
           // PROFIT FOR noStock and hasStock STATE.
           // That is why we would never get a lesser profit 
           // in higher transaction than in a lower transaction
           // since due to optimal substructure
           // property higher transaction values are computed
           // based on lower or same transaction, and at every
           // point of time we take the maximum profit possible.
   }
   
   private int allTimeProfit(int prices[]){
       // Please visit https://www.thealgorists.com/Algo/DynamicProgramming/MaxProfitWithUnlimitedStockTradingTransactions
       // to learn how we landed this soluion
       int days = prices.length;
       if (prices == null || days < 2) { // if days < 2 net profit = 0 since we 
               // cannot sell. For selling you at least need 2 days since 
               // ou may not engage in multiple transactions at the 
               // same time (i.e., you must sell the stock before you buy again).
           return 0;
       }
       int[] noStock = new int[days];
       int[] hasStock = new int[days];
       
       // Base Condition
       noStock[0] = 0; // no stock at the end of first day means we did not do anything 
           // all day first day. We started with zero stock and 
           // we ended the day with zero stock. So profit = 0
       hasStock[0] = -prices[0]; // it is first day so we started with net profit = 0 and then we bought a stock, so at the end 
           // of first day profit = negative of prices[0]
       
       for (int day = 1; day < days; day++) {
           noStock[day] = Math.max(noStock[day - 1], hasStock[day - 1] + prices[day]);
           hasStock[day] = Math.max(hasStock[day - 1], noStock[day - 1] - prices[day]);
       }

       return noStock[days - 1]; // to maximize profit we cannot end the
               // last day with a stock in hand and not selling it.
               // So the return state is always noStock state
   }
}
