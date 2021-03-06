package com.substrings;

import java.util.HashSet;

public class LongestDuplicateSubstring {
	public int search(int L, int a, long modulus, int n, int[] nums) {
		// compute the hash of string S[:L]
		long h = 0;
		for (int i = 0; i < L; ++i) {
			h = (h * a + nums[i]) % modulus;
		}

		// already seen hashes of strings of length L
		HashSet<Long> seen = new HashSet();
		seen.add(h);
		// const value to be used often : a**L % modulus
		long aL = 1;
		for (int i = 1; i <= L; ++i) {
			aL = (aL * a) % modulus;
		}

		for (int start = 1; start < n - L + 1; ++start) {
			// compute rolling hash in O(1) time
			h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
			h = (h + nums[start + L - 1]) % modulus;
			if (seen.contains(h)) {
				return start;
			}
			seen.add(h);
		}
		return -1;
	}

	/*
	 * String Searching Algorithms The problem is a follow-up of Longest
	 * Repeating Substring, and typically used to check if you're comfortable
	 * with string searching algortihms. Best algorithms have a linear execution
	 * time in average. The most popular ones are Aho-Corasick, KMPand
	 * Rabin-Karp: Aho-Corasick is used by fgrep, KMP is used for chinese string
	 * searching, and Rabin-Karp is used for plagiarism detection and in
	 * bioinformatics to look for similarities in two or more proteins. The
	 * first two are optimised for a single pattern search, and Rabin-Karp for a
	 * multiple pattern search, that is exactly the case here.
	 * 
	 */
	public String longestDupSubstring(String S) {
		int n = S.length();
		// convert string to array of integers
		// to implement constant time slice
		int[] nums = new int[n];
		for (int i = 0; i < n; ++i) {
			nums[i] = S.charAt(i) - 'a';
		}
		// base value for the rolling hash function
		int a = 26;
		// modulus value for the rolling hash function to avoid overflow
		long modulus = (long) Math.pow(2, 32);

		// binary search, L = repeating string length
		int left = 1, right = n;
		int L;
		while (left != right) {
			L = left + (right - left) / 2;
			if (search(L, a, modulus, n, nums) != -1) {
				left = L + 1;
			} else {
				right = L;
			}
		}

		int start = search(left - 1, a, modulus, n, nums);
		return start != -1 ? S.substring(start, start + left - 1) : "";
	}
}
