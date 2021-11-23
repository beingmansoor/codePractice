package com.merge.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.

Example 1:

Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
Output: [[1,3], [4,7], [8,12]]
Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
Example 2:

Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
Output: [[1,3], [4,12]]
Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].
Example 3:

Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
Output: [[1,4], [5,7]]
Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
 */
public class InsertInterval
{

	public static List<Interval> insert(List<Interval> intervals, Interval newInterval)
	{
		if (intervals == null || intervals.isEmpty())
			return Arrays.asList(newInterval);

		List<Interval> mergedIntervals = new ArrayList<>();

		int i = 0;
		// skip (and add to output) all intervals that come before the
		// 'newInterval'
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			mergedIntervals.add(intervals.get(i++));

		// merge all intervals that overlap with 'newInterval'
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end)
		{
			newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
			newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
			i++;
		}

		// insert the newInterval
		mergedIntervals.add(newInterval);

		// add all the remaining intervals to the output
		while (i < intervals.size())
			mergedIntervals.add(intervals.get(i++));

		return mergedIntervals;
	}

	public static List<Interval> insert_mine(List<Interval> intervals, Interval newInterval)
	{
		List<Interval> mergedIntervals = new ArrayList<>();

		int start = newInterval.start, end = newInterval.end;
		int i = 0;
		while (i < intervals.size())
		{
			Interval interval = intervals.get(i);
			if (interval.start < start)
			{
				if (interval.end < end)
				{
					mergedIntervals.add(interval);
				}
				else
				{
					start = interval.start;
					end = interval.end;
				}
			}
			else
			{
				if (interval.start > end)
				{
					mergedIntervals.add(new Interval(start, end));
					mergedIntervals.add(interval);
				}
				else
				{
					end = Math.max(end, interval.end);
				}
			}
			i++;
		}

		return mergedIntervals;
	}

	public static void main(String[] args)
	{
		List<Interval> input = new ArrayList<Interval>();
		input.add(new Interval(1, 3));
		input.add(new Interval(5, 7));
		input.add(new Interval(8, 12));
		System.out.print("Intervals after inserting the new interval: ");
		for (Interval interval : InsertInterval.insert(input, new Interval(4, 6)))
			System.out.print("[" + interval.start + "," + interval.end + "] ");
		System.out.println();

		input = new ArrayList<Interval>();
		input.add(new Interval(1, 3));
		input.add(new Interval(5, 7));
		input.add(new Interval(8, 12));
		System.out.print("Intervals after inserting the new interval: ");
		for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
			System.out.print("[" + interval.start + "," + interval.end + "] ");
		System.out.println();

		input = new ArrayList<Interval>();
		input.add(new Interval(2, 3));
		input.add(new Interval(5, 7));
		System.out.print("Intervals after inserting the new interval: ");
		for (Interval interval : InsertInterval.insert(input, new Interval(1, 4)))
			System.out.print("[" + interval.start + "," + interval.end + "] ");
		System.out.println();
	}
}
