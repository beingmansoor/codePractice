package com.array;

public class MinJumps {

	static int minJumps(int arr[]) {

		if (arr.length <= 1) {
			return 0;
		}

		// Return -1 if not possible to jump
		if (arr[0] == 0) {
			return -1;
		}

		// initialization
		int maxReach = arr[0];
		int step = arr[0];
		int jump = 1;

		// Start traversing array
		for (int i = 1; i < arr.length; i++) {
			// Check if we have reached the end of the array
			if (i == arr.length - 1) {
				return jump;
			}

			// updating maxReach
			maxReach = Math.max(maxReach, i + arr[i]);

			// we use a step to get to the current index
			step--;

			// If no further steps left
			if (step == 0) {
				// we must have used a jump
				jump++;

				// Check if the current index/position or lesser index
				// is the maximum reach point from the previous indexes
				if (i >= maxReach) {
					return -1;
				}

				// re-initialize the steps to the amount
				// of steps to reach maxReach from position i.
				step = maxReach - i;
			}
		}

		return -1;
	}

	static int minJumpsUsingDP(int[] arr) {
		int n = arr.length;
		// jumps[0] will
		// hold the result
		int[] jumps = new int[n];
		int min;

		// Minimum number of jumps
		// needed to reach last
		// element from last elements
		// itself is always 0
		jumps[n - 1] = 0;

		// Start from the second
		// element, move from right
		// to left and construct the
		// jumps[] array where jumps[i]
		// represents minimum number of
		// jumps needed to reach arr[m-1]
		// from arr[i]
		for (int i = n - 2; i >= 0; i--) {
			// If arr[i] is 0 then arr[n-1]
			// can't be reached from here
			if (arr[i] == 0) {
				jumps[i] = Integer.MAX_VALUE;
			} else if (arr[i] >= n - i - 1) {
				jumps[i] = 1;
			} else {
				// initialize min value
				min = Integer.MAX_VALUE;

				// following loop checks
				// with all reachable points
				// and takes the minimum
				for (int j = i + 1; j < n && j <= arr[i] + i; j++) {
					if (min > jumps[j]) {
						min = jumps[j];
					}
				}

				// Handle overflow
				if (min != Integer.MAX_VALUE) {
					jumps[i] = min + 1;
				}
				else {
					jumps[i] = min; // or Integer.MAX_VALUE
				}
			}
		}

		return jumps[0];
	}

	static boolean canJump(int nums[]) {
		int reachable = 0;
		if (nums[0] == 0) {
			return false;
		}
		for (int i = 0; i < nums.length; i++) {
			if (reachable < i) {
				return false;
			}

			reachable = Math.max(reachable, nums[i] + i);
		}

		return true;
	}
	
	static int min(int[] a)
	{
		int sc=0,e=0,max=0;
		for(int i=0;i<a.length-1;i++)
		{
			max = Math.max(max, i+a[i]);
			if(i ==e)
			{
				sc++;
				e = max;
			}
		}
		return sc;
	}

	public static void main(String[] args) {
		
		System.out.println(min(new int[]{2,3,1,1,4}));
		int arr[] = new int[] { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };

		// calling minJumps method
		System.out.println(minJumps(arr));
		System.out.println(minJumpsUsingDP(arr));
		System.out.println(min(arr));

		System.out.println(canJump(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println(canJump(new int[] { 3, 2, 1, 0, 4 }));
	}

}
