package com.merge.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PeakMemoryUsage {

	/*
	 * Given memory usage of process with start, end and memory usage of this
	 * process. Find the interval where the memory usage is maximum.
	 *  Input:
	 * [4,8,19], [1,5,8], [2,8,10], [20,30,10]
	 *  Output: [4,5] with 37 {[4,8,19],[2,8,10], [1,5,8] } 
	 *  Input: [4,8,19], [1,5,8], [2,8,10], [20,30,80]
	 * Output: [20,30] with 80 {[20,30,80] }
	 */
	public static void main(String[] args) {

		boolean test = test(new int[][] { { 4, 8, 19 }, { 1, 5, 8 }, { 2, 8, 10 }, { 20, 30, 10 } },
				new int[] { 4, 5, 37 })
				&& test(new int[][] { { 4, 8, 19 }, { 1, 5, 8 }, { 2, 8, 10 }, { 20, 30, 80 } },
						new int[] { 20, 30, 80 });
		System.out.println("Tests :" + (test ? "Passed" : "Failed"));
	}

	private static boolean test(int[][] intervals, int[] expected) {
		System.out.println("\nIntervals :" + Arrays.toString(intervals));
		System.out.println("Expected :" + Arrays.toString(expected));

		int[] obtained = peakMemoryUsage(intervals);
		System.out.println("Obtained :" + Arrays.toString(obtained));

		return Arrays.equals(expected, obtained);

	}

	/**
	 * Algorithm:
	 * <p>
	 * Sort start times with an nlogn sorting algorithm and store it. Sort end
	 * times with an nlogn sorting algorithm and store it.
	 * <p>
	 * We traverse sorted start times and add to an accumulator, but after
	 * traversing one start time, we traverse as many end times as needed to
	 * "catch up" to the start time and we subtract that from the accumulator.
	 * Take the max of accumulator vs the current max at each traversal.
	 *
	 * @param intervals
	 * @return
	 */
	private static int[] peakMemoryUsage(int[][] intervals) {
		if (null == intervals || intervals.length == 0)
			return new int[0];
		int n = intervals.length;

		class Interval {

			int id;
			int memoryUsage;
			int interval;

			public Interval(int id, int memoryUsage, int interval) {
				this.id = id;
				this.memoryUsage = memoryUsage;
				this.interval = interval;
			}
		}
		// contains start time and its id
		List<Interval> start = new ArrayList<>(n);
		List<Interval> end = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {

			start.add(new Interval(i, intervals[i][2], intervals[i][0]));
			end.add(new Interval(i, intervals[i][2], intervals[i][1]));
		}

		// Sort start times .
		start.sort(Comparator.comparingInt(o -> o.interval));

		// Sort end times .
		end.sort(Comparator.comparingInt(o -> o.interval));

		int maxMemory = 0;
		int currentMemory = 0;
		int si = -1;
		int ei = -1;

		int i = 0;
		int j = 0;
		while (i < n && j < n) {

			int s = start.get(i).interval;
			int e = end.get(j).interval;

			if (s <= e) {
				currentMemory += start.get(i).memoryUsage;
				i++;
			} else {
				currentMemory -= end.get(j).memoryUsage;
				j++;
			}

			if (currentMemory > maxMemory) {
				maxMemory = currentMemory;
				si = s;
				ei = e;
			}
		}

		return new int[] { si, ei, maxMemory };

	}
}