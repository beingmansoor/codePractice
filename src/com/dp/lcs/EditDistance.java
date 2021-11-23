package com.dp.lcs;

/*
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting, inserting, or replacing 
 * characters. Write a function to calculate the count of the minimum number of edit operations.

Example 1:

Input: s1 = "bat"
       s2 = "but"
Output: 1
Explanation: We just need to replace 'a' with 'u' to transform s1 to s2.
Example 2:

Input: s1 = "abdca"
       s2 = "cbda"
Output: 2
Explanation: We can replace first 'a' with 'c' and delete second 'c'.
Example 3:

Input: s1 = "passpot"
       s2 = "ppsspqrt"
Output: 3 
Explanation: Replace 'a' with 'p', 'o' with 'q', and insert 'r'.
 */
public class EditDistance {

	/*
	 * Since we want to match all the characters of the given two strings, we
	 * can use a two-dimensional array to store our results. The lengths of the
	 * two strings will define the size of the two dimensions of the array. So
	 * for every index ‘i1’ in string ‘s1’ and ‘i2’ in string ‘s2’, we will
	 * choose one of the following options:
	 * 
	 * If the character s1[i1] matches s2[i2], the count of the edit operations
	 * will be equal to the count of the edit operations for the remaining
	 * strings. If the character s1[i1] does not match s2[i2], we will take the
	 * minimum count from the remaining strings after performing any of the
	 * three edit operations. So our recursive formula would be:
	 * 
	 * if s1[i1] == s2[i2] 
		  dp[i1][i2] = dp[i1-1][i2-1]
		else 
		  dp[i1][i2] = 1 + min(dp[i1-1][i2], // delete
		                       dp[i1][i2-1], // insert 
		                       dp[i1-1][i2-1]) // replace
	 */
	public int findMinOperations(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		// if s2 is empty, we can remove all the characters of s1 to make it
		// empty too
		for (int i1 = 0; i1 <= s1.length(); i1++)
			dp[i1][0] = i1;

		// if s1 is empty, we have to insert all the characters of s2
		for (int i2 = 0; i2 <= s2.length(); i2++)
			dp[0][i2] = i2;

		for (int i1 = 1; i1 <= s1.length(); i1++) {
			for (int i2 = 1; i2 <= s2.length(); i2++) {
				// If the strings have a matching character, we can recursively
				// match for the remaining lengths
				if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1))
					dp[i1][i2] = dp[i1 - 1][i2 - 1];
				else
					dp[i1][i2] = 1 + Math.min(dp[i1 - 1][i2], // delete
							Math.min(dp[i1][i2 - 1], // insert
									dp[i1 - 1][i2 - 1])); // replace
			}
		}

		return dp[s1.length()][s2.length()];
	}

	public int findMinOperationsRec(String s1, String s2) {
		return findMinOperationsRecursive(s1, s2, 0, 0);
	}

	private int findMinOperationsRecursive(String s1, String s2, int i1, int i2) {

		// if we have reached the end of s1, then we have to insert all the
		// remaining characters of s2
		if (i1 == s1.length())
			return s2.length() - i2;

		// if we have reached the end of s2, then we have to delete all the
		// remaining characters of s1
		if (i2 == s2.length())
			return s1.length() - i1;

		// If the strings have a matching character, we can recursively match
		// for the remaining lengths.
		if (s1.charAt(i1) == s2.charAt(i2))
			return findMinOperationsRecursive(s1, s2, i1 + 1, i2 + 1);

		int c1 = 1 + findMinOperationsRecursive(s1, s2, i1 + 1, i2); // perform
																		// deletion
		int c2 = 1 + findMinOperationsRecursive(s1, s2, i1, i2 + 1); // perform
																		// insertion
		int c3 = 1 + findMinOperationsRecursive(s1, s2, i1 + 1, i2 + 1); // perform
																			// replacement

		return Math.min(c1, Math.min(c2, c3));
	}

	public static void main(String[] args) {
		EditDistance editDisatnce = new EditDistance();
		System.out.println(editDisatnce.findMinOperations("bat", "but"));
		System.out.println(editDisatnce.findMinOperations("abdca", "cbda"));
		System.out.println(editDisatnce.findMinOperations("passpot", "ppsspqrt"));
	}
}
