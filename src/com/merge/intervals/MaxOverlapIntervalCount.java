package com.merge.intervals;

import java.util.Arrays;

public class MaxOverlapIntervalCount {
	/*
	 * Given a list of intervals, find the point where the maximum number of
	 * intervals overlap.
	 */

	public static int maxOverlapIntervalCount(int[] start, int[] end) {
		int maxOverlap = 0;
		int currentOverlap = 0;

		Arrays.sort(start);
		Arrays.sort(end);

		int i = 0;
		int j = 0;
		int m = start.length, n = end.length;
		while (i < m && j < n) {
			if (start[i] < end[j]) {
				currentOverlap++;
				maxOverlap = Math.max(maxOverlap, currentOverlap);
				i++;
			} else {
				currentOverlap--;
				j++;
			}
		}

		return maxOverlap;
	}
}
