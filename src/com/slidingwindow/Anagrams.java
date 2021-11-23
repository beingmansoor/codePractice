package com.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Anagrams {
	
	public static List<Integer> findStringAnagrams(String str, String pattern) {
	    int windowStart = 0, matched = 0;
	    Map<Character, Integer> charFrequencyMap = new HashMap<>();
	    for (char chr : pattern.toCharArray())
	      charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

	    List<Integer> resultIndices = new ArrayList<Integer>();
	    // our goal is to match all the characters from the map with the current window
	    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
	      char rightChar = str.charAt(windowEnd);
	      // decrement the frequency of the matched character
	      if (charFrequencyMap.containsKey(rightChar)) {
	        charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
	        if (charFrequencyMap.get(rightChar) == 0)
	          matched++;
	      }

	      if (matched == charFrequencyMap.size()) // have we found an anagram?
	        resultIndices.add(windowStart);

	      if (windowEnd >= pattern.length() - 1) { // shrink the window
	        char leftChar = str.charAt(windowStart++);
	        if (charFrequencyMap.containsKey(leftChar)) {
	          if (charFrequencyMap.get(leftChar) == 0)
	            matched--; // before putting the character back, decrement the matched count
	          // put the character back
	          charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
	        }
	      }
	    }

	    return resultIndices;
	  }
	
	public static void main(String[] args) {
	    System.out.println(findStringAnagrams("ppqp", "pq"));
	    System.out.println(findStringAnagrams("abbcabc", "abc"));
	  }
	
	public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        
        int begin = 0, end = 0;
        
        
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;
            
            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }
            
        }
        return result;
    }
}
