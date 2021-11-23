package com.dp.polindromic;

public class CountOfPolindromicSubStrings {

	public int findCPS(String st) {
		// dp[i][j] will be 'true' if the string from index 'i' to index 'j' is
		// a
		// palindrome
		boolean[][] dp = new boolean[st.length()][st.length()];
		int count = 0;

		// every string with one character is a palindrome
		for (int i = 0; i < st.length(); i++) {
			dp[i][i] = true;
			count++;
		}

		for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
			for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
				if (st.charAt(startIndex) == st.charAt(endIndex)) {
					// if it's a two character string or if the remaining string
					// is a palindrome too
					if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
						dp[startIndex][endIndex] = true;
						count++;
					}
				}
			}
		}

		return count;
	}
	
	 public int countSubstrings(String s) {
	        int count=0;
	        for(int i=0;i<s.length();i++){
	            count+=extractPalindrome(s,i,i);//odd length
	            count+=extractPalindrome(s,i,i+1);//even length
	        }
	        return count;
	    }
	    public int extractPalindrome(String s, int left, int right){
	        int count=0;
	        while(left>=0 && right<s.length()&& (s.charAt(left)==s.charAt(right))){
	        	System.out.println(s.substring(left, right+1));
	            left--;
	            right++;
	            count++;
	        }
	        return count;
	    }

	public static void main(String[] args) {
		CountOfPolindromicSubStrings cps = new CountOfPolindromicSubStrings();
		System.out.println(cps.findCPS("abdbca") + "\t" + cps.countSubstrings("abdbca"));
		System.out.println(cps.findCPS("cdpdd")+ "\t" + cps.countSubstrings("cdpdd"));
		System.out.println(cps.findCPS("pqr")+ "\t" + cps.countSubstrings("pqr"));
	}
}