package com.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * 
 * Problem Statement #
Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ 
letters with any letter, find the length of the longest substring having the same letters 
after replacement.

Example 1:

Input: String="aabccbb", k=2
Output: 5
Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
Example 2:

Input: String="abbcb", k=1
Output: 4
Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
Example 3:

Input: String="abccde", k=1
Output: 3
Explanation: Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".
 * 
 */
public class CharacterReplacement {
	
	public int characterReplacementl(String s, int k) {
        int[] freq = new int[26];
        int mostFreqLetter = 0;
        int left = 0;
        int max = 0;
        
        for(int right = 0; right < s.length(); right++){
            freq[s.charAt(right) - 'A']++;
            mostFreqLetter = Math.max(mostFreqLetter, freq[s.charAt(right) - 'A']);
            
            int lettersToChange = (right - left + 1) - mostFreqLetter;
            if(lettersToChange > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            
            max = Math.max(max, right - left + 1);
        }
        
        return max;
    }
	
	public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'a']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'a']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
//There's no edge case for this question. The initial step is to extend the window to its limit,
//that is, the longest we can get to with maximum number of modifications. 
//Until then the variable start will remain at 0.

//Then as end increase, the whole substring from 0 to end will violate the rule,
//so we need to update start accordingly (slide the window). 
//We move start to the right until the whole string satisfy the constraint again.
//Then each time we reach such situation, we update our max length.

	public static int findLength1(String str, int k) {
		
		int counter =0,start=0,end=0,maxLength=-1;
		
		int []map = new int[26];
		while(end < str.length())
		{
			
			char c = str.charAt(end);
			
			if (map[c-'a']==0)
				counter++;
			map[c-'a']++;
			
			maxLength = Math.max(maxLength, (end - start +1));
			
			while(counter > k)
			{
				char c1 = str.charAt(start);
				map[c1-'a']--;
				if(map[c1-'a'] ==0)
					counter--;
				start++;
			}
			
			end++;
		}
		
		return maxLength;
	}
	
	public static int findLength(String str, int k) {
	    int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0;
	    Map<Character, Integer> letterFrequencyMap = new HashMap<>();
	    // try to extend the range [windowStart, windowEnd]
	    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
	      char rightChar = str.charAt(windowEnd);
	      letterFrequencyMap.put(rightChar, letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
	      maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap.get(rightChar));

	      // current window size is from windowStart to windowEnd, overall we have a letter which is
	      // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter 
	      // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
	      // if the remaining letters are more than 'k', it is the time to shrink the window as we
	      // are not allowed to replace more than 'k' letters
	      if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
	        char leftChar = str.charAt(windowStart);
	        letterFrequencyMap.put(leftChar, letterFrequencyMap.get(leftChar) - 1);
	        windowStart++;
	      }

	      maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
	    }

	    return maxLength;
	  }
	
	public int characterReplacements(String s, int k)
	{
	    int[] count = new int[128];
	    int max=0;
	    int start=0;
	    for(int end=0; end<s.length(); end++)
	    {
	        max = Math.max(max, ++count[s.charAt(end)]);
	        if(max+k<=end-start)
	            count[s.charAt(start++)]--;
	    }
	    return s.length()-start;
	}

	
	public static void main(String[] args) {
		System.out.println(findLength("aabccbb", 2));
		System.out.println(findLength("abbcb", 1));
		System.out.println(findLength("abccde", 1));
	}
}