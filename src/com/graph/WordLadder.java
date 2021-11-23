package com.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Basically I keep two sets of words, one set reached that represents the borders that have been 
 * reached with "distance" steps; another set wordDict that has not been reached. In the while loop, 
 * for each word in the reached set, I give all variations and check if it matches anything from 
 * wordDict, if it has a match, I add that word into toAdd set, which will be my "reached" set in 
 * the next loop, and remove the word from wordDict because I already reached it in this step. 
 * And at the end of while loop, I check the size of toAdd, which means that if I can't reach any
 *  new String from wordDict, I won't be able to reach the endWord, then just return 0. 
 *  Finally if the endWord is in reached set, I return the current steps "distance".

The idea is that reached always contain only the ones we just reached in the last step, and wordDict
 always contain the ones that haven't been reached. This is pretty much what Dijkstra's algorithm does,
  or you can see this as some variation of BFS.
 */
public class WordLadder
{
	public int ladderLength(String beginWord, String endWord, Set<String> wordDict)
	{
		Set<String> reached = new HashSet<String>();
		reached.add(beginWord);
		wordDict.add(endWord);
		int distance = 1;
		while (!reached.contains(endWord))
		{
			Set<String> toAdd = new HashSet<String>();
			for (String each : reached)
			{
				for (int i = 0; i < each.length(); i++)
				{
					char[] chars = each.toCharArray();
					for (char ch = 'a'; ch <= 'z'; ch++)
					{
						chars[i] = ch;
						String word = new String(chars);
						if (wordDict.contains(word))
						{
							toAdd.add(word);
							wordDict.remove(word);
						}
					}
				}
			}
			distance++;
			if (toAdd.size() == 0)
				return 0;
			reached = toAdd;
		}
		return distance;
	}

	public int ladderLength_bfs(String beginWord, String endWord, Set<String> wordDict)
	{
		int depth = 0;
		Queue<String> queue = new LinkedList<>();
		queue.add(beginWord);
		while (!queue.isEmpty())
		{
			depth++;
			int lSize = queue.size();
			for(int i=0; i<lSize;i++)
			{
				String current = queue.poll();
				if(current.equals(endWord))
				{
					return depth+1;
				}
				wordMatch(wordDict, queue, current);
			}
		}
		return depth;
	}
	
	public int ladderLength2(String start, String end, Set<String> dict) {
		  // Use queue to help BFS
		  Queue<String> queue = new LinkedList<String>();
		  queue.add(start);
		  queue.add(null);
		  
		  // Mark visited word
		  Set<String> visited = new HashSet<String>();
		  visited.add(start);
		  
		  int level = 1;
		  
		  while (!queue.isEmpty()) {
		    String str = queue.poll();
		    
		    if (str != null) {
		      // Modify str's each character (so word distance is 1)
		      for (int i = 0; i < str.length(); i++) {
		        char[] chars = str.toCharArray();
		        
		        for (char c = 'a'; c <= 'z'; c++) {
		          chars[i] = c;
		          
		          String word = new String(chars);
		          
		          // Found the end word
		          if (word.equals(end)) return level + 1;
		          
		          // Put it to the queue
		          if (dict.contains(word) && !visited.contains(word)) {
		            queue.add(word);
		            visited.add(word);
		          }
		        }
		      }
		    } else {
		      level++;
		      
		      if (!queue.isEmpty()) { 
		        queue.add(null);
		      }
		    }
		  }
		  
		  return 0;
		}

	private void wordMatch(Set<String> wordDict, Queue<String> queue, String current)
	{
		char[] cWord = current.toCharArray();
		for(int j=0; j<cWord.length;j++)
		{
			char c = cWord[j];
			for(char k='a'; k<='z';k++)
			{
				if(k == c)
					continue;
				cWord[j] = k;
				String candidate = String.valueOf(cWord);
				if(wordDict.contains(candidate))
				{
					wordDict.remove(candidate);
					queue.add(candidate);
				}
			}
		}
	}
}
