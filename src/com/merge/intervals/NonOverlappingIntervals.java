package com.merge.intervals;

import java.util.Arrays;

/*
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

	
	public static int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0)  return 0;

        Arrays.sort(intervals, (a,b)-> a.end -b.end);
        int end = intervals[0].end;
        int count = 1;        

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;
            }
        }
        return intervals.length - count;
    }
	
	public static void main(String[] args) {
		Interval[] input1 = new Interval[] { new Interval(1,2), new Interval(2,3), new Interval(3,4) , new Interval(1,3)};
		System.out.println(eraseOverlapIntervals(input1));
		Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };	
		System.out.println(eraseOverlapIntervals(input2));
	}
}
