package com.merge.intervals;

import java.util.Arrays;

public class BallonsArrows {
	
	
	public int findMinArrowShots1(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }
	
	// Greedy  || 452. Minimum Number of Arrows to Burst Balloons
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        int count = 0;      // results
        // minEnd : Key parameter "active set" for overlapping intervals, 
        // e.g. the minimum ending point among all overlapping intervals;
        int minEnd = Integer.MAX_VALUE;     
        Arrays.sort(points, (a,b) -> (a[0] - b[0]));   // Sorting the intervals/pairs in ascending order of its starting point
        for (int[] in : points) {
            // If the changing some states, record some information, and start a new active set "new arrow"
            if (in[0] > minEnd) {
                count++;
                minEnd = in[1];
            } else {
                // renew key parameters of the active set
                minEnd = Math.min(minEnd, in[1]);
            }
        }
        return count + 1;
    }
    
    
 // Greedy || 435. Non-overlapping Intervals
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int count = 0;          // result
        // minEnd is Key parameter "active set" for overlapping intervals;
        int minEnd = Integer.MAX_VALUE;
        Arrays.sort(intervals, (a,b) -> (a.start - b.start));  // ascending order
        for (Interval in : intervals) {
            if (in.start >= minEnd) {        // changing some start , start new " don't overlapp "
                count++;
                minEnd = in.end;
            } else {
                minEnd = Math.min(minEnd, in.end);
            }
        }
        return intervals.length - count - 1;        // there are count + 1 : overlapping 
    }
}
