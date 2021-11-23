package com.union.find;

/*
 * Given an array equations of strings that represent relationships between variables,
 *  each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".
 *  Here, a and b are lowercase letters (not necessarily different) that represent one-letter 
 *  variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy 
all the given equations.

 

Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true

 */
public class EqualityEquations {
	/*
	 * Intuition: We have 26 nodes in the graph. All "==" equations actually
	 * represent the connection in the graph. The connected nodes should be in
	 * the same color/union/set.
	 * 
	 * Then we check all inequations. Two inequal nodes should be in the
	 * different color/union/set.
	 * 
	 * Explanation : We can solve this problem by DFS or Union Find.
	 * 
	 * Firt pass all "==" equations. Union equal letters together Now we know
	 * which letters must equal to the others.
	 * 
	 * Second pass all "!=" inequations, Check if there are any contradict
	 * happens.
	 * 
	 * Time Complexity: Union Find Operation, amortized O(1) First pass all
	 * equations, O(N) Second pass all inequations, O(N)
	 * 
	 * Overall O(N)
	 * 
	 * 
	 */
	public boolean equationsPossible(String[] equations) {

		int[] p = new int[26];
		for (int i = 0; i < 26; i++) {
			p[i] = i;
		}

		for (String e : equations) {
			char[] c = e.toCharArray();
			if (c[1] == '=') {
				int x = find(p, c[0] - 'a');
				int y = find(p, c[3] - 'a');
				if (x != y) {
					p[y] = x;
				}
			}
		}

		for (String e : equations) {
			char[] c = e.toCharArray();
			if (c[1] == '!') {
				int x = find(p, c[0] - 'a');
				int y = find(p, c[3] - 'a');
				if (x == y) {
					return false;
				}
			}
		}

		return true;

	}

	int find(int[] p, int i) {
		while (p[i] != i) {
			i = p[p[i]];
		}
		return p[i];
	}

	public static void main(String[] args) {
		EqualityEquations e = new EqualityEquations();
		System.out.println(e.equationsPossible(new String[] { "a==b", "b!=a" }));
		System.out.println(e.equationsPossible(new String[] { "a==b", "b==a" }));
		System.out.println(e.equationsPossible(new String[] { "a==b", "b==c", "a==c" }));
		System.out.println(e.equationsPossible(new String[] { "a==b", "b!=c", "c==a" }));
		System.out.println(e.equationsPossible(new String[] { "c==c", "b==d", "x!=z" }));
	}
}
