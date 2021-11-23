package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {
	
	 public static int findLength(String str, int k) {
		    if (str == null || str.length() == 0 || str.length() < k)
		      throw new IllegalArgumentException();

		    int windowStart = 0, maxLength = 0;
		    Map<Character, Integer> charFrequencyMap = new HashMap<>();
		    // in the following loop we'll try to extend the range [windowStart, windowEnd]
		    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
		      char rightChar = str.charAt(windowEnd);
		      charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
		      // shrink the sliding window, until we are left with 'k' distinct characters in the frequency map
		      while (charFrequencyMap.size() > k) {
		        char leftChar = str.charAt(windowStart);
		        charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
		        if (charFrequencyMap.get(leftChar) == 0) {
		          charFrequencyMap.remove(leftChar);
		        }
		        windowStart++; // shrink the window
		      }
		      maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
		    }

		    return maxLength;
		  }
	 
	  public int lengthOfLongestSubstringAtmostKDistinct(String s, int k) {
		    int[] map = new int[256];
		    int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

		    while (end < s.length()) {
		      final char c1 = s.charAt(end);
		      if (map[c1] == 0) counter++;
		      map[c1]++;
		      end++;

		      while (counter > k) {
		        final char c2 = s.charAt(start);
		        if (map[c2] == 1) counter--;
		        map[c2]--;
		        start++;
		      }

		      maxLen = Math.max(maxLen, end - start);
		    }

		    return maxLen;
		  }

	public static void main(String[] args) {
		System.out.println(findLength("araaci", 2));
		System.out.println(findLength("araaci", 1));
		System.out.println(findLength("cbbebi", 3));
	}
}