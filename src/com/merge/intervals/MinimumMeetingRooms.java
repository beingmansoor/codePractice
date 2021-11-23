package com.merge.intervals;

/*
 * Given a list of intervals representing the start and end time of ‘N’ meetings, 
 * find the minimum number of rooms required to hold all the meetings.

Example 1:

Meetings: [[1,4], [2,5], [7,9]]
Output: 2
Explanation: Since [1,4] and [2,5] overlap, we need two rooms to hold these two meetings. [7,9] can 
occur in any of the two rooms later.
Example 2:

Meetings: [[6,7], [2,4], [8,12]]
Output: 1
Explanation: None of the meetings overlap, therefore we only need one room to hold all meetings.
Example 3:

Meetings: [[1,4], [2,3], [3,6]]
Output:2
Explanation: Since [1,4] overlaps with the other two meetings [2,3] and [3,6], we need two rooms to 
hold all the meetings.
 
Example 4:

Meetings: [[4,5], [2,3], [2,4], [3,5]]
Output: 2
Explanation: We will need one room for [2,3] and [3,5], and another room for [2,4] and [4,5].
 * 
 */
import java.util.*;

class Meeting {
	int start;
	int end;

	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}
};

public class MinimumMeetingRooms {
	
	public static int minRooms(List<Meeting> meets)
	{

		ArrayList<Integer> starts = new ArrayList<Integer>();
		ArrayList<Integer> ends = new ArrayList<Integer>();
		for (Meeting meeting : meets) {
			starts.add(meeting.start);
			ends.add(meeting.end);
		}
		
		Collections.sort(starts);
		Collections.sort(ends);
		
		int n = ends.size();
		
		int i=0,j=0;
		int  minRooms= 0,rooms =0;
		while(i<n && j<n)
		{
			if(starts.get(i) <ends.get(j))
			{
				rooms++;
				i++;
			}
			else
			{
				rooms--;
				j++;
			}
			minRooms = Math.max(minRooms, rooms);
		}
		return minRooms;
	}

	public static int findMinimumMeetingRooms(List<Meeting> meetings) {
		if (meetings == null || meetings.size() == 0)
			return 0;

		// sort the meetings by start time
		Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

		int minRooms = 0;
		PriorityQueue<Meeting> minHeap = new PriorityQueue<>(meetings.size(), (a, b) -> Integer.compare(a.end, b.end));
		for (Meeting meeting : meetings) {
			// remove all meetings that have ended
			while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end)
				minHeap.poll();
			// add the current meeting into the minHeap
			minHeap.offer(meeting);
			// all active meeting are in the minHeap, so we need rooms for all
			// of them.
			minRooms = Math.max(minRooms, minHeap.size());
		}
		return minRooms;
	}

	/*
	 * Time complexity # The time complexity of the above algorithm is
	 * O(N*logN), where ‘N’ is the total number of meetings. This is
	 * due to the sorting that we did in the beginning. Also, while iterating
	 * the meetings we might need to poll/offer meeting to the priority queue.
	 * Each of these operations can take O(logN). Overall our algorithm
	 * will take O(NlogN).
	 * 
	 * Space complexity # The space complexity of the above algorithm will be
	 * O(N)O(N) which is required for sorting. Also, in the worst case scenario,
	 * we’ll have to insert all the meetings into the Min Heap (when all
	 * meetings overlap) which will also take O(N)O(N) space. The overall space
	 * complexity of our algorithm is O(N)O(N).
	 */

	public static int findMinimumMeetingRooms1(List<Meeting> meetings) {

		int minNumberOfMeetings = 0;
		Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

		PriorityQueue<Meeting> pd = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));

		for (Meeting meeting : meetings) {

			boolean isRemoved = false;
			if (!pd.isEmpty()) {
				while (!pd.isEmpty() && pd.peek().end <= meeting.start) {
					pd.poll();
					isRemoved = true;
				}
			}
			pd.offer(meeting);
			if (!isRemoved) {
				minNumberOfMeetings++;
			}
		}

		return minNumberOfMeetings;
	}

	public static void main(String[] args) {
		List<Meeting> input = new ArrayList<Meeting>() {
			{
				add(new Meeting(4, 5));
				add(new Meeting(2, 3));
				add(new Meeting(2, 4));
				add(new Meeting(3, 5));
			}
		};
		int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
		System.out.println("Minimum meeting rooms required: " + result +"\t"+ minRooms(input));

		input = new ArrayList<Meeting>() {
			{
				add(new Meeting(1, 4));
				add(new Meeting(2, 5));
				add(new Meeting(7, 9));
			}
		};
		result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
		System.out.println("Minimum meeting rooms required: " + result+"\t"+ minRooms(input));

		input = new ArrayList<Meeting>() {
			{
				add(new Meeting(6, 7));
				add(new Meeting(2, 4));
				add(new Meeting(8, 12));
			}
		};
		result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
		System.out.println("Minimum meeting rooms required: " + result+"\t"+ minRooms(input));

		input = new ArrayList<Meeting>() {
			{
				add(new Meeting(1, 4));
				add(new Meeting(2, 3));
				add(new Meeting(3, 6));
			}
		};
		result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
		System.out.println("Minimum meeting rooms required: " + result+"\t"+ minRooms(input));

		input = new ArrayList<Meeting>() {
			{
				add(new Meeting(4, 5));
				add(new Meeting(2, 3));
				add(new Meeting(2, 4));
				add(new Meeting(3, 5));
			}
		};
		result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
		System.out.println("Minimum meeting rooms required: " + result +"\t"+ minRooms(input));
	}
}