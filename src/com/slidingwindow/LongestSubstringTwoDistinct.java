package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringTwoDistinct {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int start = 0, end = 0, counter = 0, len = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) == 1) counter++;//new char
            end++;
            while(counter > 2){
                char cTemp = s.charAt(start);
                map.put(cTemp, map.get(cTemp) - 1);
                if(map.get(cTemp) == 0){
                    counter--;
                }
                start++;
            }
            len = Math.max(len, end-start);
        }
        return len;
    }
	
//	int lengthOfLongestSubstringTwoDistinct(string s) {
//        vector<int> map(128, 0);
//        int counter=0, begin=0, end=0, d=0; 
//        while(end<s.size()){
//            if(map[s[end++]]++==0) counter++;
//            while(counter>2) if(map[s[begin++]]--==1) counter--;
//            d=max(d, end-begin);
//        }
//        return d;
//    }
	
}
