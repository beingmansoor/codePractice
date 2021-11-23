package com.tree.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class IdentifyConcatenation
{
	public static List<String> identifyConcatenations(String[] words)
	{
		List<String> res = new ArrayList<>();
		// Set for O(1) lookups
		HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
		HashMap<String, Boolean> cache = new HashMap<>();

		// Process for each word
		for (String word : words)
			if (dfs(word, wordSet, cache))
				res.add(word);
		return res;
	}

	public static boolean dfs(String word, HashSet<String> wordSet, HashMap<String, Boolean> cache)
	{
		// If result for current word already calculated then
		// return from cache
		if (cache.containsKey(word))
			return cache.get(word);

		// Traverse over the word to generate all combinations
		for (int i = 1; i < word.length(); i++)
		{
			// Divide the word into prefix and suffix
			String prefix = word.substring(0, i);
			String suffix = word.substring(i);

			if (wordSet.contains(prefix))
			{
				if (wordSet.contains(suffix) || dfs(suffix, wordSet, cache))
				{

					cache.put(word, true);
					return true;
				}
			}
		}
		cache.put(word, false);
		return false;
	}

	public static void main(String args[])
	{
		// Driver code

		String[] fileWords = { "n", "cat", "cats", "dog", "catsndog" };
		System.out.println("The following words will be compressed: " + identifyConcatenations(fileWords));
	}
}
