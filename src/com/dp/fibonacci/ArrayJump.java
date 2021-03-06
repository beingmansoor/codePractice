package com.dp.fibonacci;

public class ArrayJump {

	public int countMinJumps1(int[] jumps) {
		return this.countMinJumpsRecursive(jumps, 0);
	}

	/*
	 * Let?s try to populate our dp[] array from the above solution, working in
	 * the bottom-up fashion. As we saw in the above code, we were trying to
	 * find the minimum jumps needed to reach every index (if it is within the
	 * range) from the current index. We can use this fact to populate our
	 * array.
	 * 
	 * As we know, every index within the range of current index can be reached
	 * in one jump. Therefore, we can say that we can reach every index (within
	 * the range of current index) in:
	 * 
	 * 'jumps to reach current index' + 1 So, 
	 * 
	 * while going through all the
	 * indexes, we will take the minimum value between the current jump-count
	 * and the jumps needed to reach the current index + 1.
	 */
	public int countMinJumps(int[] jumps) {
		int[] dp = new int[jumps.length];

		// initialize with infinity, except the first index which should be zero
		// as we start from there
		for (int i = 1; i < jumps.length; i++)
			dp[i] = Integer.MAX_VALUE;

		for (int start = 0; start < jumps.length - 1; start++) {
			for (int end = start + 1; end <= start + jumps[start] && end < jumps.length; end++)
				dp[end] = Math.min(dp[end], dp[start] + 1);
		}

		return dp[jumps.length - 1];
	}/*
		 * The above solution has a time complexity of O(n^2) (because of the
		 * two for loops) and space complexity of O(n) to store dp[].
		 */

	/*
	 * The main idea is based on greedy. Let's say the range of the current jump
	 * is [curBegin, curEnd], curFarthest is the farthest point that all points
	 * in [curBegin, curEnd] can reach. Once the current point reaches curEnd,
	 * then trigger another jump, and set the new curEnd with curFarthest, then
	 * keep the above steps, as the following:
	 */
	public int countMinJumps2(int[] jumps) {

		int jump = 0, currentEnd = 0;

		int currentFarthest = 0;

		for (int i = 0; i < jumps.length; i++) {
			currentFarthest = Math.max(currentFarthest, i + jumps[i]);
			if (i == currentEnd) {
				jump++;
				currentEnd = currentFarthest;
			}
			if (currentEnd >= jumps.length - 1)
				break;
		}

		return jump;
	}

	private int countMinJumpsRecursive(int[] jumps, int currentIndex) {
		// if we have reached the last index, we don't need any more jumps
		if (currentIndex == jumps.length - 1)
			return 0;

		if (jumps[currentIndex] == 0)
			return Integer.MAX_VALUE;

		int totalJumps = Integer.MAX_VALUE;
		int start = currentIndex + 1;
		int end = currentIndex + jumps[currentIndex];
		while (start < jumps.length && start <= end) {
			// jump one step and recurse for the remaining array
			int minJumps = countMinJumpsRecursive(jumps, start++);
			if (minJumps != Integer.MAX_VALUE)
				totalJumps = Math.min(totalJumps, minJumps + 1);
		}
		return totalJumps;
	}
	/*
	 * The time complexity of the above algorithm is O(2^n), where ?n? is the
	 * size of the input array. The ?while loop? can execute a maximum of ?n?
	 * times (for the case where we can jump to all the steps ahead) and since
	 * in each iteration, the function recursively calls itself, therefore, the
	 * time complexity is O(2^n). The space complexity is O(n) which is used to
	 * store the recursion stack.
	 */

	public static void main(String[] args) {
		ArrayJump aj = new ArrayJump();
		int[] jumps = { 2, 1, 1, 1, 4 };
		System.out.println(aj.countMinJumps2(jumps));
		jumps = new int[] { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
		System.out.println(aj.countMinJumps2(jumps));
	}
}