package com.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class KSmallerElements {

	public static int segment(int x, List<Integer> arr) {
		int n = arr.size();
		int[] r = new int[n - x + 1];
		int ri = 0;
		int max = Integer.MIN_VALUE;
		// store index
		Deque<Integer> q = new ArrayDeque<Integer>();
		for (int i = 0; i < n; i++) {
			// remove numbers out of range x
			while (!q.isEmpty() && q.peek() < i - x + 1) {
				q.poll();
			}
			// remove larger numbers in x range as they are useless
			while (!q.isEmpty() && arr.get(q.peekLast()) > arr.get(i)) {
				q.pollLast();
			}
			// q contains index... r contains min element of each x subarray.
			q.offer(i);
			if (i >= x - 1) {
				r[ri++] = arr.get(q.peek());
				max = Math.max(max, arr.get(q.peek()));
			}
		}

		System.out.println(Arrays.toString(r));

		return max;

	}

	public static void main(String[] args) {
		Integer[] integers = new Integer[] { 1, 2, 3, 1, 2 };
		List<Integer> asList = Arrays.asList(integers);
		System.out.println(segment(2, asList));
	}

}
