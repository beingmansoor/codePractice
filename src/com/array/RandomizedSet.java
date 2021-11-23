package com.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {

	List<Integer> arr = null;
	Map<Integer, Integer> map = null;

	/** Initialize your data structure here. */
	public RandomizedSet() {
		arr = new ArrayList<Integer>();
		map = new HashMap<Integer, Integer>();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already
	 * contain the specified element.
	 */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}
		int index = arr.size();
		arr.add(index, val);
		map.put(val, index);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the
	 * specified element.
	 */
	public boolean remove(int val) {

		System.out.println("map: " + map);

		if (!map.containsKey(val)) {
			return false;
		}


		int i = map.get(val);

		if (i != arr.size() - 1) {
			int v = arr.get(arr.size() - 1);
			arr.set(i, v);
			map.put(v, i);
		}
		arr.remove(arr.size() - 1);
		map.remove(val);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {

		if (arr.isEmpty()) {
			return 0;
		}
		Random random = new Random();
		int i = random.nextInt(arr.size());
		return arr.get(i);
	}

	public static void main(String[] args) {
		RandomizedSet r = new RandomizedSet();
		r.remove(0);
		r.remove(0);

		r.insert(0);
		r.getRandom();

		r.remove(0);
		System.out.println(r.insert(0));
	}
}