package com.array;

import java.util.Collections;
import java.util.PriorityQueue;

public class Max3Product {

	public static int product(int[]a)
	{
		PriorityQueue<Integer> poheap = new PriorityQueue<>();
        PriorityQueue<Integer> neheap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : a) {
            poheap.offer(num);
            neheap.offer(num);
            if (poheap.size() > 3) {
                poheap.poll();
            }
            if (neheap.size() > 2) {
                neheap.poll();
            }
        }
        int c1 = 1;
        int max = 0;
        while (!poheap.isEmpty()) {
            max = poheap.poll();
            c1 *= max;
        }
        while (!neheap.isEmpty()) {
            max *= neheap.poll();
        }
        return Math.max(c1, max);
	}
	
	public static void main(String[] args) {
		System.out.println(product(new int[]{-1,9,22,3,-15,-7}));
	}
}
