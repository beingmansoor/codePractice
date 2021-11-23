package com.two.heaps;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinVariation
{
	public static int miniVariationLength(int[] nums, int threshold)
	{

		Deque<Integer> maxDeque = new ArrayDeque<>();
		Deque<Integer> minDeque = new ArrayDeque<>();
		int start = 0, end = 0;
		int ans = 0;

		while (end < nums.length)
		{
			// All elements greater than current index element gets removed from
			// minDeque
			while (!minDeque.isEmpty() && nums[end] < nums[minDeque.peekLast()])
				minDeque.pollLast(); // pop from end

			// All elements smaller than current index element gets removed from
			// minDeque
			while (!maxDeque.isEmpty() && nums[end] > nums[maxDeque.peekLast()])
				maxDeque.pollLast(); // pop from end

			// append at end of both deques
			minDeque.add(end);
			maxDeque.add(end);

			int variation = nums[maxDeque.peek()] - nums[minDeque.peek()];
			if (variation > threshold)
			{
				start++;
				// A new sub-array is starting so elements from previous one
				// should
				// be removed from both the deques
				if (start > minDeque.peek())
					minDeque.poll(); // pop from front
				if (start > maxDeque.peek())
					maxDeque.poll(); // pop from front
			}

			ans = Math.max(ans, (end - start + 1));
			end++;
		}
		return ans;
	}

	public static void main(String args[])
	{
		// Driver code

		int[] trafficRates = { 10, 1, 2, 4, 7, 2 };
		int thresholdMiniVal = 5;

		System.out.println("The traffic of this customer changes by less than or equal to " + thresholdMiniVal
				+ " Gbps in a " + miniVariationLength(trafficRates, thresholdMiniVal) + " day window");
	}
}
