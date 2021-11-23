package com.stack;

import java.util.HashMap;
import java.util.Stack;

public class MaxFrequencyStack
{
	HashMap<Integer, Integer> frqs = new HashMap();
	
	HashMap<Integer, Stack<Integer>> freqsToElements = new HashMap<>();

	int maxFreq = -1;

	public MaxFrequencyStack()
	{
	}

	public void push(int x)
	{
		Integer f = frqs.getOrDefault(x, 0)+1;
		Stack<Integer> elments = freqsToElements.getOrDefault(f, new Stack<Integer>());
		if(maxFreq < f)
			maxFreq = f;
		elments.add(x);
		frqs.put(x, f);
		freqsToElements.put(f, elments);
		
	}

	public int pop()
	{
		if(maxFreq == -1)
			return -1;
		Stack<Integer> stack = freqsToElements.get(maxFreq);
		Integer removedElement = stack.pop();
		if(stack.isEmpty())
			maxFreq--;
		Integer f = frqs.get(removedElement)-1;
		if(f == 0)
		{
			frqs.remove(removedElement);
		}
		else
		{
			frqs.put(removedElement, f);
		}
		
		return removedElement;
	}
	public static void main(String[] args)
	{
		MaxFrequencyStack s = new MaxFrequencyStack();
		s.push(5);
		s.push(7);
		s.push(5);
		s.push(7);
		s.push(4);
		s.push(5);
		
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}
