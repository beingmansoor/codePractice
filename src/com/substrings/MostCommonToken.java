package com.substrings;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class MostCommonToken
{
	public static String mostCommonToken(String code, String[] keywords)
	{
		// Replacing the syntax with spaces
		String normalizedCode = code.replaceAll("[^a-zA-Z0-9]", " ");

		String[] tokens = normalizedCode.split("\\s+");
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		Set<String> bannedWords = new HashSet<String>(Arrays.asList(keywords));

		// Count occurence of each token, excluding the keywords
		for (String token : tokens)
		{
			if (!bannedWords.contains(token))
			{
				if (!count.containsKey(token))
				{
					count.put(token, 0);
				}
				count.put(token, count.get(token) + 1);
			}
		}
		// return the maximum valued key
		Entry<String, Integer> maxCountEntry = Collections.max(count.entrySet(),
				(entry1, entry2) -> entry1.getValue() - entry2.getValue());
		return maxCountEntry.getKey();
	}

	public static void main(String args[])
	{
		// Driver code
		String code = "int main() {\n" + "int value = getValue();\n" + "int sum = value + getRandom();\n"
				+ "int subs = value - getRandom();\n" + "return 0;\n" + "}";
		String[] keywords = { "int", "main", "return" };
		System.out.println(mostCommonToken(code, keywords));
	}
}
