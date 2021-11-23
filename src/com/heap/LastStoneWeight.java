package com.heap;

import java.util.PriorityQueue;

public class LastStoneWeight {

	public static void main(String[] args) {

		final LastStoneWeight l = new LastStoneWeight();

		System.out.println(l.lastStoneWeight(new int[] { 2, 7, 4, 1, 8, 1 }));
	}

	public int lastStoneWeight(int[] stones) {

		if (stones.length == 1) {
			return stones[0];
		}

		final PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);
		for (final int stone : stones) {
			queue.offer(stone);
		}

		while (queue.size() > 1) {
			final int y = queue.poll();
			final int x = queue.poll();

			if (x != y) {
				queue.offer(y - x);
			}
		}
		if (queue.isEmpty()) {
			return 0;
		}

		return queue.peek();

	}

}
