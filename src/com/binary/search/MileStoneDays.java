package com.binary.search;

import java.util.Arrays;

public class MileStoneDays
{
	public static int search(int[] milestones, int n)
	{
		int first = 0;
		int last = milestones.length;
		while (first < last)
		{
			int mid = (first + last) / 2;
			if (milestones[mid] >= n)
				last = mid;
			else
				first = mid + 1;
		}
		return first;
	}

	public static int[] milestoneDays(int[] milestones, int target)
	{
		int first_day = search(milestones, target);
		if (target == milestones[first_day])
		{
			int last_day = search(milestones, target + 1) - 1;
			return new int[] { first_day, last_day };
		}
		else
			return new int[] { -1, -1 };

	}

	public static void main(String args[])
	{
		int[] milestones = { 0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, 6, 7 };
		int target = 4;
		System.out.println(Arrays.toString(milestoneDays(milestones, target)));
	}
}
