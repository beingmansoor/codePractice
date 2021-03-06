package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringWithoutRepeatingChars {
	public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0, counter = 0, d = 0;

        while (end < s.length()) {
            // > 0 means repeating character
            //if(map[s.charAt(end++)]-- > 0) counter++;
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) > 1) counter++;
            end++;
            
            while (counter > 0) {
                //if (map[s.charAt(begin++)]-- > 1) counter--;
                char charTemp = s.charAt(begin);
                if (map.get(charTemp) > 1) counter--;
                map.put(charTemp, map.get(charTemp)-1);
                begin++;
            }
            d = Math.max(d, end - begin);
        }
        return d;
    }
	
	 public int lengthOfLongestSubstring2(String s) {
		    int[] map = new int[128];
		    int start = 0, end = 0, maxLen = 0, counter = 0;

		    while (end < s.length()) {
		      final char c1 = s.charAt(end);
		      if (map[c1] > 0) counter++;
		      map[c1]++;
		      end++;

		      while (counter > 0) {
		        final char c2 = s.charAt(start);
		        if (map[c2] > 1) counter--;
		        map[c2]--;
		        start++;
		      }

		      maxLen = Math.max(maxLen, end - start);
		    }

		    return maxLen;
		  }
}
