package com.dp.polindromic;

public class LongestPolindromicSubString {

	public int findLPSLength(String st) {
		// dp[i][j] will be 'true' if the string from index 'i' to index 'j' is
		// a
		// palindrome
		boolean[][] dp = new boolean[st.length()][st.length()];

		// every string with one character is a palindrome
		for (int i = 0; i < st.length(); i++)
			dp[i][i] = true;

		int maxLength = 1;
		for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
			for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
				if (st.charAt(startIndex) == st.charAt(endIndex)) {
					// if it's a two character string or if the remaining string
					// is a palindrome too
					if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
						dp[startIndex][endIndex] = true;
						maxLength = Math.max(maxLength, endIndex - startIndex + 1);
					}
				}
			}
		}

		return maxLength;
	}

	public int findLPSLength1(String st) {
		return findLPSLengthRecursive(st, 0, st.length() - 1);
	}

	private int findLPSLengthRecursive(String st, int startIndex, int endIndex) {
		if (startIndex > endIndex)
			return 0;

		// every string with one character is a palindrome
		if (startIndex == endIndex)
			return 1;

		// case 1: elements at the beginning and the end are the same
		if (st.charAt(startIndex) == st.charAt(endIndex)) {
			int remainingLength = endIndex - startIndex - 1;
			// check if the remaining string is also a palindrome
			if (remainingLength == findLPSLengthRecursive(st, startIndex + 1, endIndex - 1))
				return remainingLength + 2;
		}

		// case 2: skip one character either from the beginning or the end
		int c1 = findLPSLengthRecursive(st, startIndex + 1, endIndex);
		int c2 = findLPSLengthRecursive(st, startIndex, endIndex - 1);
		return Math.max(c1, c2);
	}
	
	// Space Optimized Solution
    // Space Complexity: O(1) or contant space
    // Time Complexity: O(n * n)
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // leftCenter and rightCenter are having
            // same index, which means we have only one center character here. 
            // This computes length of the palindrome with odd number of characters
            int len2 = expandAroundCenter(s, i, i + 1); // leftCenter and rightCenter 
            // are having two distinct indices, which means we have two center 
            // characters here. This computes length of 
            // the palindrome with even number of characters.
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int leftCenter, int rightCenter) {
        int L = leftCenter;
        int R = rightCenter;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

	public static void main(String[] args) {
		LongestPolindromicSubString lps = new LongestPolindromicSubString();
		System.out.println(lps.findLPSLength("abdbca"));
		System.out.println(lps.findLPSLength("cddpd"));
		System.out.println(lps.findLPSLength("pqr"));
	}
}
