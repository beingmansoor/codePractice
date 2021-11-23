package com.union.find;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

 */
public class Accounts_Merge {

	public List<List<String>> accountsMerge(List<List<String>> acts) {
		Map<String, String> owner = new HashMap<>();
		Map<String, String> parents = new HashMap<>();
		Map<String, TreeSet<String>> unions = new HashMap<>();
		for (List<String> a : acts) {
			for (int i = 1; i < a.size(); i++) {
				parents.put(a.get(i), a.get(i));
				owner.put(a.get(i), a.get(0));
			}
		}
		for (List<String> a : acts) {
			String p = find(a.get(1), parents);
			for (int i = 2; i < a.size(); i++) {
				parents.put(find(a.get(i), parents), p);
			}
		}
		for (List<String> a : acts) {
			String p = find(a.get(1), parents);
			if (!unions.containsKey(p)) {
				unions.put(p, new TreeSet<>());
			}
			for (int i = 1; i < a.size(); i++) {
				unions.get(p).add(a.get(i));
			}
		}
		List<List<String>> res = new ArrayList<>();
		for (String p : unions.keySet()) {
			List<String> emails = new ArrayList(unions.get(p));
			emails.add(0, owner.get(p));
			res.add(emails);
		}
		return res;
	}

	private String find(String s, Map<String, String> p) {
		return p.get(s) == s ? s : find(p.get(s), p);
	}

	public List<List<String>> accountsMerge_dfs(List<List<String>> accounts) {

		Map<String, Set<String>> graph = new HashMap<>(); // <email node,
															// neighbor nodes>
		Map<String, String> name = new HashMap<>(); // <email, username>
		// Build the graph;
		for (List<String> account : accounts) {
			String userName = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				if (!graph.containsKey(account.get(i))) {
					graph.put(account.get(i), new HashSet<>());
				}
				name.put(account.get(i), userName);

				if (i == 1) {
					continue;
				}
				graph.get(account.get(i)).add(account.get(i - 1));
				graph.get(account.get(i - 1)).add(account.get(i));
			}
		}

		Set<String> visited = new HashSet<>();
		List<List<String>> res = new LinkedList<>();
		// DFS search the graph;
		for (String email : name.keySet()) {
			List<String> list = new LinkedList<>();
			if (visited.add(email)) {
				dfs(graph, email, visited, list);
				Collections.sort(list);
				list.add(0, name.get(email));
				res.add(list);
			}
		}

		return res;
	}

	public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
		list.add(email);
		for (String next : graph.get(email)) {
			if (visited.add(next)) {
				dfs(graph, next, visited, list);
			}
		}
	}
}
