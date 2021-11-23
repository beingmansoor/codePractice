package com.merge.intervals;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 Given two lists of intervals, find the intersection of these two lists. 
 Each list consists of disjoint intervals sorted on their start time.

Example 1:

Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
Output: [2, 3], [5, 6], [7, 7]
Explanation: The output list contains the common intervals between the two lists.
Example 2:

Input: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]
Output: [5, 7], [9, 10]
Explanation: The output list contains the common intervals between the two lists.
 */

public class IntervalsIntersection {

	public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
		List<Interval> result = new ArrayList<Interval>();
		int i = 0, j = 0;
		while (i < arr1.length && j < arr2.length) {
			// check if the interval arr[i] intersects with arr2[j]
			// check if one of the interval's start time lies within the other
			// interval
			if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
					|| (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
				// store the intersection part
				result.add(new Interval(Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
			}

			// move next from the interval which is finishing first
			if (arr1[i].end < arr2[j].end)
				i++;
			else
				j++;
		}

		return result.toArray(new Interval[result.size()]);
	}

	public static Interval[] merge_mine(Interval[] arr1, Interval[] arr2) {
		List<Interval> intervalsIntersection = new ArrayList<Interval>();
		for (Interval interval : arr1) {
			for (Interval interval2 : arr2) {
				if (((interval.end <= interval2.end || interval.start <= interval2.end)
						&& interval.end >= interval2.start)) {
					intervalsIntersection.add(new Interval(Math.max(interval.start, interval2.start),
							Math.min(interval.end, interval2.end)));
				}
			}
		}
		return intervalsIntersection.toArray(new Interval[intervalsIntersection.size()]);
	}

	public static void main(String[] args) {
		Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), new Interval(7, 9) };
		Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
		Interval[] result = IntervalsIntersection.merge(input1, input2);
		System.out.print("Intervals Intersection: ");
		for (Interval interval : result)
			System.out.print("[" + interval.start + "," + interval.end + "] ");
		System.out.println();

		input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7), new Interval(9, 12) };
		input2 = new Interval[] { new Interval(5, 10) };
		result = IntervalsIntersection.merge(input1, input2);
		System.out.print("Intervals Intersection: ");
		for (Interval interval : result)
			System.out.print("[" + interval.start + "," + interval.end + "] ");
	}
}
