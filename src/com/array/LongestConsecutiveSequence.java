package com.array;

import java.util.HashSet;

public class LongestConsecutiveSequence {

	static int lcs(int[] nums)
	{
		HashSet<Integer> hashSet = new HashSet<Integer>();
		int best =0;
		
		for(int n : nums)
		{
			hashSet.add(n);
		}
		
		for(int n: nums)
		{
			if(false == hashSet.contains(n-1))
			{
				int m = n+1;
				while(hashSet.contains(m))
				{
					m++;
				}
				
				best = Math.max(best, m-n);
			}
		}
		
		return best;
		
	}
	
}
