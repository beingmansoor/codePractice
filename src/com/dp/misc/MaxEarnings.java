package com.dp.misc;

public class MaxEarnings {

	static int maxEarnings(int[]b, int[]h)
	{
		int n= b.length;
		
		int dp[][] = new int[n+1][2];
		
		dp[0][0] = 0;
		dp[0][1] = 0;
		
		for(int i=0;i<b.length;i++)
		{
			if(i>=1)
			{
				dp[i+1][0] = Math.max(b[i]+ dp[i][0], h[i] + dp[i-1][0]);
				dp[i+1][1] = Math.max(h[i]+ dp[i][1], b[i] + dp[i-1][1]);
			}
			else
			{
				dp[i+1][0] = b[i]+ dp[i][0];
				dp[i+1][1] = h[i]+ dp[i][1];
			}
		}
		
		return Math.max(dp[n][0], dp[n][1]);
	}
	
	static int maxEarningsRec(int[]b, int[]h, int bI, int hI)
	{
		if(hI >= h.length-1 || bI >= h.length-1)
		{
			return 0;
		}
		
		int max1 =  Math.max( b[bI] + maxEarningsRec(b, h, bI+1, hI), b[bI] + maxEarningsRec(b, h, bI, hI+2));
		int max2 = h[hI] + Math.max( maxEarningsRec(b, h, bI, hI+1),  maxEarningsRec(b, h, bI+2, hI));
		System.out.println(max1 + "\t" + max2 + "---> [" + bI + "," + hI + "]");
		return Math.max(max1, max2);
	}
	
	
	public static void main(String[] args) {
		System.out.println(maxEarnings(new int[]{5,2,3,10}, new int[]{6,7,2,6}));
		
		System.out.println(maxEarningsRec(new int[]{5,2,3,10}, new int[]{6,7,2,6},0,0));
	}
	
}
