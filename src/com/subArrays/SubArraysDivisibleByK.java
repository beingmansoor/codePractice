package com.subArrays;

import java.util.HashMap;


/*
 * 
 * Let there be a subarray (i, j) whose sum is divisible by k
	  sum(i, j) = sum(0, j) - sum(0, i-1)
	Sum for any subarray can be written as q*k + rem where q 
	is a quotient and rem is remainder
	Thus,     
	    sum(i, j) = (q1 * k + rem1) - (q2 * k + rem2)
	    sum(i, j) = (q1 - q2)k + rem1-rem2
	     
	We see, for sum(i, j) i.e. for sum of any subarray to be
	divisible by k, the RHS should also be divisible by k.
	(q1 - q2)k is obviously divisible by k, for (rem1-rem2) to 
	follow the same, rem1 = rem2 where
	    rem1 = Sum of subarray (0, j) % k
	    rem2 = Sum of subarray (0, i-1) % k 
	    
 */
public class SubArraysDivisibleByK {
	public static int subarraysDivByK(int[] A, int K) {
		int count = 0;

		HashMap<Integer, Integer> remainderCounts = new HashMap<>();
		remainderCounts.put(0, 1);

		int prefixSum = 0;
		for (int i : A) {

			prefixSum += i;

			int rem = prefixSum % K;
			if (rem < 0) {
				rem += K;
			}

			Integer remCount = remainderCounts.getOrDefault(rem, 0);
			count += remCount;

			remainderCounts.put(rem, remCount + 1);

		}

		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
	}
}
