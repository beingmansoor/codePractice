package com.slidingwindow;

import java.util.*;

/*
 * 
 * Given a string, find the length of the longest substring which has no repeating characters.

Example 1:

Input: String="aabccbb"
Output: 3
Explanation: The longest substring without any repeating characters is "abc".
Example 2:

Input: String="abbbb"
Output: 2
Explanation: The longest substring without any repeating characters is "ab".
Example 3:

Input: String="abccde"
Output: 3
Explanation: Longest substrings without any repeating characters are "abc" & "cde".

 */
public class NoRepeatSubstring {
	public static int findLength(String str) {
		
		HashSet<Character> uniqueChars = new HashSet<Character>();
		
		int maxLength =-1,start =0,end =0;
		
		int length = str.length();
		while(end < length)
		{
			boolean unique = uniqueChars.add(str.charAt(end));
			
			if(!unique)
			{
				uniqueChars.clear();
				start = end+1;
			}
			else
			{
				maxLength = Math.max(maxLength, (end - start +1));
			}
			end++;			
			
		}
		
		return maxLength;
	}
	
	public static int findLength1(String str) {
	    int windowStart = 0, maxLength = 0;
	    Map<Character, Integer> charIndexMap = new HashMap<>();
	    // try to extend the range [windowStart, windowEnd]
	    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
	      char rightChar = str.charAt(windowEnd);
	      // if the map already contains the 'rightChar', shrink the window from the beginning so that
	      // we have only one occurrence of 'rightChar'
	      if (charIndexMap.containsKey(rightChar)) {
	        // this is tricky; in the current window, we will not have any 'rightChar' after its previous index
	        // and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
	        windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
	      }
	      charIndexMap.put(str.charAt(windowEnd), windowEnd); // insert the 'rightChar' into the map
	      maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
	    }

	    return maxLength;
	  }
	
	public static void main(String[] args) {
		System.out.println(findLength1("aabccbb"));
		System.out.println(findLength("aabccbb"));
		System.out.println(findLength("abbbb"));
		System.out.println(findLength("abccde"));
		
	}
}