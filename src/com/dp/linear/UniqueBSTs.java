package com.dp.linear;

import java.util.HashMap;
import java.util.Map;

public class UniqueBSTs {
	/*
	 * The idea is to use each number i as root node, then the left branch will
	 * be what's less than i, the right branch will be what's larger than i. The
	 * total number of distinct structure is their product. Thus, sum up the
	 * product for all numbers. Use a map to memorize the visited number.
	 * 
	 * Why are we multiplying these 2 values ? numTrees(i-1, map) *
	 * numTrees(n-i, map)? Ans : because the number of different trees is
	 * depending on the number of different left subtrees * the number of
	 * different right subtrees.
	 */
	public int numTrees(int n) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);
		map.put(1, 1);
		return numTrees(n, map);
	}

	private int numTrees(int n, Map<Integer, Integer> map) {
		// check memory
		if (map.containsKey(n))
			return map.get(n);
		// recursion
		int sum = 0;
		for (int i = 1; i <= n; i++)
			sum += numTrees(i - 1, map) * numTrees(n - i, map);
		map.put(n, sum);
		return sum;
	}
}
