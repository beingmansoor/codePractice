package com.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestToOrigin {
	public int[][] kClosest(int[][] points, int K) {

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (dist(b) - dist(a)));
		for (int[] p : points) {
			if (pq.size() < K) {
				pq.offer(p);
			} else if (dist(pq.peek()) > dist(p)) {
				pq.poll();
				pq.offer(p);
			}
		}
		int[][] res = new int[K][2];
		int i = 0;
		while (!pq.isEmpty()) {
			res[i++] = pq.poll();
		}

		return res;

	}

	public int dist(int[] p) {
		return p[0] * p[0] + p[1] * p[1];
	}

	public static void main(String[] args) {
		KClosestToOrigin k = new KClosestToOrigin();

		System.out.println(Arrays.deepToString(k.kClosest(new int[][] { { 1, 3 }, { -2, 2 } }, 1)));
	}
}
