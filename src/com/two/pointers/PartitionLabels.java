package com.two.pointers;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels
{
	public static List<Integer> partitionLabels(String S)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int[] last = new int[26];

		for (int i=0; i< S.length(); i++)
		{
			last[S.charAt(i)-'a'] = i;
		}
		
		int start = 0, end =0;
		
		for (int i=0; i< S.length(); i++)
		{
			char c = S.charAt(i);
			end = Math.max(end, last[c-'a']);
			
			if(end == i)
			{
				result.add(end- start +1);
				System.out.println(S.substring(start, end + 1));
				start = end +1;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args)
	{
		System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
	}
}
