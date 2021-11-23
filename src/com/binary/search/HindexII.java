package com.binary.search;

/*
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.

 */
public class HindexII {

	public int hIndex2(int[] c) {

		int n = c.length;

		int l = 0, h = n - 1;
		while (l <= h) {
			int m = h - (h - l) / 2;
			if (c[m] >= n - m) {
				h = m - 1;
			} else {
				l = m + 1;
			}
		}
		return n - l;
	}

	public int hIndex(int[] c) {

		int n = c.length;

		for (int i = 0; i < n; i++) {
			if (c[i] >= n - i) {
				return n - i;
			}
		}
		return 0;

	}

	public static void main(String[] args) {
		HindexII h = new HindexII();
		System.out.println(h.hIndex(new int[] { 0, 1, 3, 5, 6 }));
		System.out.println(h.hIndex2(new int[] { 0, 1, 3, 5, 6 }));
		System.out.println(h.hIndex(new int[] { 0, 0, 0, 0, 0 }));
		System.out.println(h.hIndex2(new int[] { 0, 0, 0, 0, 0 }));
	}
}
