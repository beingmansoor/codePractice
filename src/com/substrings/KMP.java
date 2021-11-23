package com.substrings;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KMP
{
	static class Gnome{
		  
		  private final String s;
		  private int index;
		  public Gnome(String s ) {
			  this.s = s;
		  }
		  
		  public Character nextItem() {
			  if(index < s.length())
				   return s.charAt(index++);
			  return null;
		  }
	  }

	  static class Pattern{
		  
		  int[] lookup;
		  String pattern;
		  int index;
		  
		  public Pattern(int[] lookup , String pattern) {
			  this.lookup = lookup;
			  this.pattern = pattern;
			  index = 0;
		  }
		  
		  public void match(char c) {
			   if(index == lookup.length) return;
			   if(pattern.charAt(index) == c) {
				   index ++;
			   }else if(index > 0){
				       while(index > 0) {
				    	 index = lookup[index -1];
				    	 if(pattern.charAt(index) == c) {
				    		 this.match(c);
				    		 break;
				    	 }
				     }
			   }
		  }
	  }
	  public boolean matchesPattern(List<String> patterns , Gnome G) {
		  Queue<Pattern> Q1 = new LinkedList<>() , Q2 = new LinkedList<>() , add = Q1 , remove = Q2 , temp;
		  for(String s : patterns) {
			    Pattern p = new Pattern(this.lookup(s) , s);
			    remove.add(p);
		  }
		  Character c = null;
		  while(null != (c=G.nextItem()) && !remove.isEmpty()) {
			     while(!remove.isEmpty()) {
			    	  Pattern p = remove.poll();
			    	  p.match(c);
			    	  if(p.index == p.lookup.length)
			    		  continue;
			    	  add.offer(p);
			     }
			     temp = add;
			     add = remove;
			     remove = temp;
		  }
		  return remove.size() == 0;
	  }
	  
	  
	  private int[] lookup(String s) {
		  int[] L = new int[s.length()];
		  int i = 0 , j = 1;
		  while(j < s.length()) {
			  if(s.charAt(i) == s.charAt(j))
				  L[j++] = ++i;
			  else if(i > 0)
				  i = L[i-1];
			  else
				  j ++;
		  }
		  return L;
	  }

}
