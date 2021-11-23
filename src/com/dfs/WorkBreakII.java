package com.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkBreakII
{
	public static void main( String args[] ) {
        String query = "vegancookbook";
        String[] dict = {"an", "book", "car", "cat", "cook", "cookbook", "crash", 
                "cream", "high", "highway", "i", "ice", "icecream", "low", 
                "scream", "veg", "vegan", "way"};
        
        System.out.println(Arrays.toString(breakQuery(query, dict)));
        query = "highwaycarcrash";
        System.out.println(Arrays.toString(breakQuery(query, dict)));
    }

	private static String[] breakQuery(String query, String[] dict)
	{
		
		Map<String, List<String>> cache = new HashMap<String, List<String>>();
		Set<String> words = new HashSet<String>(Arrays.asList(dict));
		List<String> result = wordBreak(query,words,cache);
		return result.toArray(new String[0]);
	}

	private static List<String> wordBreak(String query, Set<String> words, Map<String, List<String>> cache)
	{
		ArrayList<String> result = new ArrayList<String>();
		if(cache.containsKey(query))
		{
			return cache.get(query);
		}
		
		if(query.length() == 0)
		{
			result.add("");
			return result;
		}
		
		for (String word : words)
		{
			if(query.startsWith(word))
			{
				List<String> subResult = wordBreak(query.substring(word.length()), words, cache);
				for (String sub : subResult)
				{
					result.add(word + (sub.isEmpty() ?"" :" " + sub));
				}
			}
		}
		return result;
	}
}
