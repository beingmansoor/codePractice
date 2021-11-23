package com.topK;

import java.util.*;
import java.util.Map.Entry;

public class FrequencySort {

	/*
	 * Given a string, sort it based on the decreasing frequency of its characters.

	Example 1:
	
	Input: "Programming"
	Output: "rrggmmPiano"
	Explanation: 'r', 'g', and 'm' appeared twice, so they need to appear before any other character.
	Example 2:
	
	Input: "abcbab"
	Output: "bbbaac"
	Explanation: 'b' appeared three times, 'a' appeared twice, and 'c' appeared only once.
	 */
	public static String sortCharacterByFrequency1(String str) {

		if (str != null && str.length() > 0) {

			char[] charArray = str.toCharArray();

			HashMap<Character, String> freqMap = new HashMap<Character, String>();
			for (char c : charArray) {
				freqMap.put(c, freqMap.getOrDefault(c, "") + c);
			}

			PriorityQueue<Entry<Character, String>> pq = new PriorityQueue<>(
					(a, b) -> b.getValue().length() - a.getValue().length());
			Set<Entry<Character, String>> entrySet = freqMap.entrySet();
			for (Entry<Character, String> entry : entrySet) {
				pq.add(entry);
			}

			StringBuilder sb = new StringBuilder();
			while (!pq.isEmpty()) {
				sb.append(pq.poll().getValue());
			}

			return sb.toString();

		}
		return "";
	}

	public static String sortCharacterByFrequency(String str) {
		// find the frequency of each character
		Map<Character, Integer> characterFrequencyMap = new HashMap<>();
		for (char chr : str.toCharArray()) {
			characterFrequencyMap.put(chr, characterFrequencyMap.getOrDefault(chr, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
				(e1, e2) -> e2.getValue() - e1.getValue());

		// add all characters to the max heap
		maxHeap.addAll(characterFrequencyMap.entrySet());

		// build a string, appending the most occurring characters first
		StringBuilder sortedString = new StringBuilder(str.length());
		while (!maxHeap.isEmpty()) {
			Map.Entry<Character, Integer> entry = maxHeap.poll();
			for (int i = 0; i < entry.getValue(); i++)
				sortedString.append(entry.getKey());
		}
		return sortedString.toString();
	}

	public static void main(String[] args) {
		String result = FrequencySort.sortCharacterByFrequency("Programming");
		System.out.println("Here is the given string after sorting characters by frequency: " + result);

		result = FrequencySort.sortCharacterByFrequency("abcbab");
		System.out.println("Here is the given string after sorting characters by frequency: " + result);
	}
}