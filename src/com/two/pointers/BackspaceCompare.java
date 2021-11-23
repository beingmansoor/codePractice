package com.two.pointers;

/*
 
 Comparing Strings containing Backspaces (medium) #
Given two strings containing backspaces (identified by the character ‘#’), 
check if the two strings are equal.

Example 1:

Input: str1="xy#z", str2="xzz#"
Output: true
Explanation: After applying backspaces the strings become "xz" and "xz" respectively.
Example 2:

Input: str1="xy#z", str2="xyz#"
Output: false
Explanation: After applying backspaces the strings become "xz" and "xy" respectively.
Example 3:

Input: str1="xp#", str2="xyz##"
Output: true
Explanation: After applying backspaces the strings become "x" and "x" respectively.
In "xyz##", the first '#' removes the character 'z' and the second '#' removes the character 'y'.

Example 4:

Input: str1="xywrrmp", str2="xywrrmu#p"
Output: true
Explanation: After applying backspaces the strings become "xywrrmp" and "xywrrmp" respectively.
  
  
 */
public class BackspaceCompare {

	public static boolean compareByMe(String str1, String str2) {

		int l1 = str1.length() - 1;
		int l2 = str2.length() - 1;
		int p1 = l1, p2 = l2;

		while (p1 > 0 & p2 > 0) {
			int c1=0,c2=0;
			while ( p1 <= l1 && str1.charAt(p1) == '#') {
				c1++;
				p1--;
			}
			p1 = p1 - c1;
			while (p2 <= l2&&str2.charAt(p2) == '#') {
				c2++;
				p2--;
			}
			p2 = p2 - c2;
			if (str1.charAt(p1) != str2.charAt(p2))
				return false;
			p1--;
			p2--;
		}
		return true;
	}
	
	public static boolean compare(String str1, String str2) {
	    // use two pointer approach to compare the strings
	    int index1 = str1.length() - 1, index2 = str2.length() - 1;
	    while (index1 >= 0 || index2 >= 0) {

	      int i1 = getNextValidCharIndex(str1, index1);
	      int i2 = getNextValidCharIndex(str2, index2);

	      if (i1 < 0 && i2 < 0) // reached the end of both the strings
	        return true;

	      if (i1 < 0 || i2 < 0) // reached the end of one of the strings
	        return false;

	      if (str1.charAt(i1) != str2.charAt(i2)) // check if the characters are equal
	        return false;

	      index1 = i1 - 1;
	      index2 = i2 - 1;
	    }

	    return true;
	  }

	private static int getNextValidCharIndex(String str, int index) {
	    int backspaceCount = 0;
	    while (index >= 0) {
	      if (str.charAt(index) == '#') // found a backspace character
	        backspaceCount++;
	      else if (backspaceCount > 0) // a non-backspace character
	        backspaceCount--;
	      else
	        break;

	      index--; // skip a backspace or a valid character
	    }
	    return index;
	  }
	public static void main(String[] args) {
	    System.out.println(BackspaceCompare.compareByMe("xy#z", "xzz#"));
	    System.out.println(BackspaceCompare.compareByMe("xy#z", "xyz#"));
	    System.out.println(BackspaceCompare.compareByMe("xp#", "xyz##"));    
	    System.out.println(BackspaceCompare.compareByMe("xywrrmp", "xywrrmu#p"));
	  }
}