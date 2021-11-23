package com.dp.lcs;

public class LongestCommonSubstring {

	/*
	 * Since we want to match all the substrings of the given two strings, we
	 * can use a two-dimensional array to store our results. The lengths of the
	 * two strings will define the size of the two dimensions of the array. So
	 * for every index ‘i’ in string ‘s1’ and ‘j’ in string ‘s2’, we have two
	 * options:
	 * 
	 * If the character at s1[i] matches s2[j], the length of the common
	 * substring would be one plus the length of the common substring till i-1
	 * and j-1 indexes in the two strings. If the character at the s1[i] does
	 * not match s2[j], we don’t have any common substring. 
	 * 
	 * 
	 * So our recursive formula would be 
	 * if s1[i] == s2[j] dp[i][j] = 1 + dp[i-1][j-1] 
	 * else
	 * dp[i][j] = 0
	 */
	public int findLCSLength(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		int maxLength = 0;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					maxLength = Math.max(maxLength, dp[i][j]);
				}
			}
		}
		return maxLength;
	}

	public int findLCSLength1(String s1, String s2) {
		return findLCSLengthRecursive(s1, s2, 0, 0, 0);
	}

	private int findLCSLengthRecursive(String s1, String s2, int i1, int i2, int count) {
		if (i1 == s1.length() || i2 == s2.length())
			return count;

		if (s1.charAt(i1) == s2.charAt(i2))
			count = findLCSLengthRecursive(s1, s2, i1 + 1, i2 + 1, count + 1);

		int c1 = findLCSLengthRecursive(s1, s2, i1, i2 + 1, 0);
		int c2 = findLCSLengthRecursive(s1, s2, i1 + 1, i2, 0);

		return Math.max(count, Math.max(c1, c2));
	}

	public static void main(String[] args) {
		LongestCommonSubstring lcs = new LongestCommonSubstring();
		System.out.println(lcs.findLCSLength("abdca", "cbda"));
		System.out.println(lcs.findLCSLength("passport", "ppsspt"));
	}
}