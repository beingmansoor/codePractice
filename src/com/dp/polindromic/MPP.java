package com.dp.polindromic;

/*
 * Given a string, we want to cut it into pieces such that each piece is a palindrome. 
 * Write a function to return the minimum number of cuts needed.

Example 1:

Input: "abdbca"
Output: 3
Explanation: Palindrome pieces are "a", "bdb", "c", "a".
Example 2:

Input: = "cddpd"
Output: 2
Explanation: Palindrome pieces are "c", "d", "dpd".
Example 3:

Input: = "pqr"
Output: 2
Explanation: Palindrome pieces are "p", "q", "r".
Example 4:

Input: = "pp"
Output: 0
Explanation: We do not need to cut, as "pp" is a palindrome.
 */
public class MPP {

	public int findMPPCuts1(String st) {
		return this.findMPPCutsRecursive(st, 0, st.length() - 1);
	}

	public int findMPPCuts(String st) {
		// isPalindrome[i][j] will be 'true' if the string from index 'i' to
		// index 'j' is a palindrome
		boolean[][] isPalindrome = new boolean[st.length()][st.length()];

		// every string with one character is a palindrome
		for (int i = 0; i < st.length(); i++)
			isPalindrome[i][i] = true;

		// populate isPalindrome table
		for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
			for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
				if (st.charAt(startIndex) == st.charAt(endIndex)) {
					// if it's a two character string or if the remaining string
					// is a palindrome too
					if (endIndex - startIndex == 1 || isPalindrome[startIndex + 1][endIndex - 1]) {
						isPalindrome[startIndex][endIndex] = true;
					}
				}
			}
		}

		// now lets populate the second table, every index in 'cuts' stores the
		// minimum cuts needed
		// for the substring from that index till the end
		int[] cuts = new int[st.length()];
		for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
			int minCuts = st.length(); // maximum cuts
			for (int endIndex = st.length() - 1; endIndex >= startIndex; endIndex--) {
				if (isPalindrome[startIndex][endIndex]) {
					// we can cut here as we got a palindrome
					// also we dont need any cut if the whole substring is a
					// palindrome
					minCuts = (endIndex == st.length() - 1) ? 0 : Math.min(minCuts, 1 + cuts[endIndex + 1]);
				}
			}
			cuts[startIndex] = minCuts;
		}

		return cuts[0];
	}

	private int findMPPCutsRecursive(String st, int startIndex, int endIndex) {
		// we don't need to cut the string if it is a palindrome
		if (startIndex >= endIndex || isPalindrome(st, startIndex, endIndex))
			return 0;

		// at max, we need to cut the string into its 'length-1' pieces
		int minimumCuts = endIndex - startIndex;
		for (int i = startIndex; i <= endIndex; i++) {
			if (isPalindrome(st, startIndex, i)) {
				// we can cut here as we have a palindrome from 'startIndex' to
				// 'i'
				minimumCuts = Math.min(minimumCuts, 1 + findMPPCutsRecursive(st, i + 1, endIndex));
			}
		}
		return minimumCuts;
	}

	private boolean isPalindrome(String st, int x, int y) {
		while (x < y) {
			if (st.charAt(x++) != st.charAt(y--))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		MPP mpp = new MPP();
		System.out.println(mpp.findMPPCuts("abdbca"));
		System.out.println(mpp.findMPPCuts("cdpdd"));
		System.out.println(mpp.findMPPCuts("pqr"));
		System.out.println(mpp.findMPPCuts("pp"));
	}
}
