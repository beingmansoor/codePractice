package com.dfs;

import java.util.Arrays;
import java.util.HashSet;

public class WordBreakI
{
	public static void main( String args[] ) {
        String query = "vegancookbook";
        String[] dict = {"i", "cream", "cook", "scream", "ice", "cat", "book", "icecream", "vegan"};
        System.out.println(breakQuery(query, dict));
    }

	private static boolean breakQuery(String query, String[] dict)
	{
		int n = query.length();
		boolean[] dp = new boolean[n+1];
		
		dp[0] = true;
		
		HashSet<String> words = new HashSet<>(Arrays.asList(dict));
		
		for(int i=1; i<=n; i++)
		{
			for(int j=0; j <i; j++)
			{
				if(dp[j] && words.contains(query.substring(j, i)))
				{
					dp[i] = true;
				}
			}
		}
		
		return dp[n];
	}
}
