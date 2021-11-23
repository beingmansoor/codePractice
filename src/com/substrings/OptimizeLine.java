package com.substrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OptimizeLine
{

	public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets)
	{
		List<int[]> indexList = new LinkedList<>();
		for (int i = 0; i < indexes.length; i++)
		{
			indexList.add(new int[] { indexes[i], i });
		}
		Collections.sort(indexList, (a, b) -> a[0] - b[0]);
		StringBuilder sb = new StringBuilder();
		int last = 0;
		for (int[] index : indexList)
		{
			int strIndex = index[0];
			int sourceIndex = index[1];
			if (S.substring(strIndex).indexOf(sources[sourceIndex]) == 0)
			{
				sb.append(S.substring(last, strIndex));
				sb.append(targets[sourceIndex]);
				last = strIndex + sources[sourceIndex].length();
			}
		}
		if (last != S.length())
		{
			sb.append(S.substring(last));
		}
		return sb.toString();
	}

	public static void main(String args[])
	{
		String line = "foo(input, i);";
		int[] indices = { 0, 11 };
		String[] sources = { "foo", "i" };
		String[] targets = { "foobar", "j+1" };

		System.out.println(findReplaceString(line, indices, sources, targets));
	}
}
