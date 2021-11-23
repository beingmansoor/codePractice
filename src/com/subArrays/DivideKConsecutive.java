package com.subArrays;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DivideKConsecutive
{
	public static boolean isPossibleDivide(int[] nums, int k)
	{
		int len = nums.length;
		if (len % k != 0)
			return false;
		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int n : nums)
			map.put(n, map.getOrDefault(n, 0) + 1);
		for (int n : map.keySet())
			pq.add(n);
		while (!pq.isEmpty())
		{
			int cur = pq.poll();
			if (map.get(cur) == 0)
				continue;
			int times = map.get(cur);
			for (int i = 0; i < k; i++)
			{
				if (!map.containsKey(cur + i) || map.get(cur + i) < times)
					return false;
				map.put(cur + i, map.get(cur + i) - times);
			}
			len -= k * times;
		}
		return len == 0;
	}
	
	public static void main(String[] args)
	{
		System.out.println(isPossibleDivide(new int[]{1,2,3,3,4,4,5,6}, 4));
		System.out.println(isPossibleDivide(new int[]{3,2,1,2,3,4,3,4,5,9,10,11}, 3));
		System.out.println(isPossibleDivide(new int[]{3,3,2,2,1,1}, 3));
		System.out.println(isPossibleDivide(new int[]{1,2,3,4}, 3));
	}
}
