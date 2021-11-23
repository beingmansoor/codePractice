package com.dp.lcs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Give three strings ‘m’, ‘n’, and ‘p’, write a method to find out if ‘p’ has been 
 * formed by interleaving ‘m’ and ‘n’. ‘p’ would be considered interleaving ‘m’ and ‘n’ if 
 * it contains all the letters from ‘m’ and ‘n’ and the order of letters is preserved too.

Example 1:

Input: m="abd", n="cef", p="abcdef"
Output: true
Explanation: 'p' contains all the letters from 'm' and 'n' and preserves their order too. 
Example 2:

Input: m="abd", n="cef", p="adcbef"
Output: false
Explanation: 'p' contains all the letters from 'm' and 'n' but does not preserve the order. 
Example 3:

Input: m="abc", n="def", p="abdccf"
Output: false
Explanation: 'p' does not contain all the letters from 'm' and 'n'. 
Example 4:

Input: m="abcdef", n="mnop", p="mnaobcdepf"
Output: true
Explanation: 'p' contains all the letters from 'm' and 'n' and preserves their order too. 

The problem follows the Longest Common Subsequence (LCS) pattern and has some similarities with Subsequence Pattern Matching.

A basic brute-force solution could be to try matching ‘m’ and ‘n’ with ‘p’ one letter at a time. 
Let’s assume mIndex, nIndex, and pIndex represent the current indexes of ‘m’, ‘n’, and ‘p’ strings 
respectively. Therefore, we have two options at any step:

If the letter at mIndex matches with the letter at pIndex, we can recursively match for the 
remaining lengths of ‘m’ and ‘p’.
If the letter at nIndex matches with the letter at ‘pIndex’, we can recursively match for the 
remaining lengths of ‘n’ and ‘p’.
 */
public class StringInterLeaving {

	
	public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (visited[p[0]][p[1]]) continue;
            if (p[0] == s1.length() && p[1] == s2.length()) return true;
            
            if (p[0] < s1.length() && s1.charAt(p[0]) == s3.charAt(p[0] + p[1]))
                queue.offer(new int[]{p[0] + 1, p[1]});
            if (p[1] < s2.length() && s2.charAt(p[1]) == s3.charAt(p[0] + p[1]))
                queue.offer(new int[]{p[0], p[1] + 1});
            visited[p[0]][p[1]] = true;
        }
        return false;
    }
	
	public boolean isInterleave1(String s1, String s2, String s3) {

	    if ((s1.length()+s2.length())!=s3.length()) return false;

	    boolean[][] matrix = new boolean[s2.length()+1][s1.length()+1];

	    matrix[0][0] = true;

	    for (int i = 1; i < matrix[0].length; i++){
	        matrix[0][i] = matrix[0][i-1]&&(s1.charAt(i-1)==s3.charAt(i-1));
	    }

	    for (int i = 1; i < matrix.length; i++){
	        matrix[i][0] = matrix[i-1][0]&&(s2.charAt(i-1)==s3.charAt(i-1));
	    }

	    for (int i = 1; i < matrix.length; i++){
	        for (int j = 1; j < matrix[0].length; j++){
	            matrix[i][j] = (matrix[i-1][j]&&(s2.charAt(i-1)==s3.charAt(i+j-1)))
	                    || (matrix[i][j-1]&&(s1.charAt(j-1)==s3.charAt(i+j-1)));
	        }
	    }

	    return matrix[s2.length()][s1.length()];

	}
	
	public Boolean findSI(String m, String n, String p) {
	    // dp[mIndex][nIndex] will be storing the result of string interleaving
	    // up to p[0..mIndex+nIndex-1]
	    boolean[][] dp = new boolean[m.length()+1][n.length()+1];

	    // for the empty pattern, we have one matching
	    if(m.length() + n.length() != p.length())
	      return false;

	    for(int mIndex=0; mIndex<=m.length(); mIndex++) {
	      for(int nIndex=0; nIndex<=n.length(); nIndex++) {
	        // if 'm' and 'n' are empty, then 'p' must have been empty too.
	        if(mIndex==0 && nIndex==0)
	          dp[mIndex][nIndex] = true;
	        // if 'm' is empty, we need to check the interleaving with 'n' only
	        else if (mIndex==0 && n.charAt(nIndex-1) == p.charAt(mIndex+nIndex-1))
	          dp[mIndex][nIndex] = dp[mIndex][nIndex-1];
	        // if 'n' is empty, we need to check the interleaving with 'm' only
	        else if (nIndex==0 && m.charAt(mIndex-1) == p.charAt(mIndex+nIndex-1))
	          dp[mIndex][nIndex] = dp[mIndex-1][nIndex];
	        else {
	          // if the letter of 'm' and 'p' match, we take whatever is matched till mIndex-1
	          if(mIndex > 0 && m.charAt(mIndex-1) == p.charAt(mIndex+nIndex-1))
	            dp[mIndex][nIndex] = dp[mIndex-1][nIndex];
	          // if the letter of 'n' and 'p' match, we take whatever is matched till nIndex-1 too
	          // note the '|=', this is required when we have common letters
	          if(nIndex > 0 && n.charAt(nIndex-1) == p.charAt(mIndex+nIndex-1))
	            dp[mIndex][nIndex] |= dp[mIndex][nIndex-1];
	        }
	      }
	    }
	    return dp[m.length()][n.length()];
	  }
	
	public boolean findSIrec(String m, String n, String p) {
		return findSIRecursive(m, n, p, 0, 0, 0);
	}
	
	

	private boolean findSIRecursive(String m, String n, String p, int mIndex, int nIndex, int pIndex) {

		// if we have reached the end of the all the strings
		if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length())
			return true;

		// if we have reached the end of 'p' but 'm' or 'n' still have some
		// characters left
		if (pIndex == p.length())
			return false;

		boolean b1 = false, b2 = false;
		if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex))
			b1 = findSIRecursive(m, n, p, mIndex + 1, nIndex, pIndex + 1);

		if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex))
			b2 = findSIRecursive(m, n, p, mIndex, nIndex + 1, pIndex + 1);

		return b1 || b2;
	}

	public static void main(String[] args) {
		StringInterLeaving si = new StringInterLeaving();
		System.out.println(si.findSI("abd", "cef", "abcdef"));
		System.out.println(si.findSI("abd", "cef", "adcbef"));
		System.out.println(si.findSI("abc", "def", "abdccf"));
		System.out.println(si.findSI("abcdef", "mnop", "mnaobcdepf"));
	}
}
