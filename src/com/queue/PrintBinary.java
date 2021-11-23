package com.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PrintBinary {
	
	static String[] findBinary(int n)
	{
		Queue<String> q = new LinkedList<>();
		
		String[] res = new String[n];
		q.offer("1");
		
		int i=0;
		while(!q.isEmpty())
		{
			res[i] = q.poll();
			if(i == n-1) break;
			q.offer(res[i] +"0");
			q.offer(res[i] +"1");
			i++;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(findBinary(3)));
		System.out.println(Arrays.toString(findBinary(4)));
		System.out.println(Arrays.toString(findBinary(5)));
	}

}
