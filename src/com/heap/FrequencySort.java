package com.heap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class FrequencySort {

	public String frequencySort(String s) {
		int l = s.length();
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>(l);
		char[] chars = s.toCharArray();

		int max = -1;
		for (char c : chars) {
			map.put(c, map.getOrDefault(c, 0) + 1);
			max = Math.max(max, map.get(c));
		}

		List<Character>[] frqBucket = new List[max + 1];

		Set<Character> keys = map.keySet();
		for (Character c : keys) {
			Integer freq = map.get(c);
			List<Character> list = frqBucket[freq];
			if (list == null) {
				list = new ArrayList<Character>();
			}
			list.add(c);
			frqBucket[freq] = list;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = max; i >= 0; i--) {
			List<Character> list = frqBucket[i];
			if (null != list) {
				for (Character character : list) {
					for (int j = 0; j < i; j++) {
						sb.append(character);
					}
				}
			}
		}
		return sb.toString();

	}

	public String frequencySort1(String s) {
		int l = s.length();
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>(l);
		char[] chars = s.toCharArray();
		for (char c : chars) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Entry<Character, Integer>> pq = new PriorityQueue<Entry<Character, Integer>>(
				(a, b) -> b.getValue() - a.getValue());
		Set<Entry<Character, Integer>> entrySet = map.entrySet();
		for (Entry<Character, Integer> entry : entrySet) {
			pq.add(entry);
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Entry<Character, Integer> poll = pq.poll();
			for (int i = 0; i < poll.getValue(); i++) {
				sb.append(poll.getKey());
			}
		}

		return sb.toString();

	}

	public static void main(String[] args) {
		FrequencySort s = new FrequencySort();
		System.out.println(s.frequencySort("Aabb"));
		System.out.println(s.frequencySort("cccaaa"));
		System.out.println(s.frequencySort("tree"));
	}
}
