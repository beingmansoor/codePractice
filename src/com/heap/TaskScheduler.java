package com.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler
{
	public static int leastInterval(char[] tasks, int n) {
	     Map<Character, Integer> map = new HashMap<>();
	    for (int i = 0; i < tasks.length; i++) {
	        map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
	    }
	    PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
	            (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

	    q.addAll(map.entrySet());

	    int count = 0;
	    while (!q.isEmpty()) {
	        int k = n + 1;
	        List<Map.Entry> tempList = new ArrayList<>();
	        while (k > 0 && !q.isEmpty()) {
	            Map.Entry<Character, Integer> top = q.poll(); // most frequency task
	            top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
	            tempList.add(top); // collect task to add back to queue
	            k--;
	            count++; //successfully executed task
	        }

	        for (Map.Entry<Character, Integer> e : tempList) {
	            if (e.getValue() > 0) q.add(e); // add valid tasks 
	        }

	        if (q.isEmpty()) break;
	        count = count + k; // if k > 0, then it means we need to be idle
	    }
	    return count;
	}
	
	 public static int leastTime(char[] stocks, int sTime) {
	        // frequencies of the stocks
	        int[] frequencies = new int[26];
	        for (int s : stocks) {
	            frequencies[s - 'A']++;
	        }
	        
	        Arrays.sort(frequencies);

	        // max frequency
	        int fMax = frequencies[25];
	        int idleIntervals = (fMax - 1) * sTime;
	        
	        for (int i = frequencies.length - 2; i >= 0 && idleIntervals > 0; --i) {
	            idleIntervals -= Math.min(fMax - 1, frequencies[i]); 
	        }
	        
	        if (idleIntervals > 0)
	            return idleIntervals + stocks.length;
	        else
	            return stocks.length;
	    }

	    public static void main(String[] args) {
	        // Driver code
	        
	        char[] transaction = {'A', 'A', 'A', 'T', 'T', 'M', 'A'};
	        int k = 2;
	        System.out.println("Time requires to trade all stocks: " + leastTime(transaction, k) + " intervals");   
	        System.out.println("Time requires to trade all stocks: " + leastInterval(transaction, k) + " intervals");   
	  }
}
