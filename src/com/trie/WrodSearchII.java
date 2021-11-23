package com.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WrodSearchII {

	public List<String> findWords(char[][] board, String[] words) {
		Set<String> result = new HashSet<String>();
		// HashSet<String> result = new HashSet<String>();
		TrieDS trie = new TrieDS();
		for (String word : words) {
			trie.insert(word);
		}
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(board, visited, "", i, j, trie, result);
			}
		}
		return new ArrayList<String>(result);
	}

	public void dfs(char[][] board, boolean[][] visited, String str, int i, int j, TrieDS trie, Set<String> result) {
		int m = board.length;
		int n = board[0].length;
		if (i < 0 || j < 0 || i >= m || j >= n) {
			return;
		}
		if (visited[i][j]) {
			return;
		}
		str = str + board[i][j];
		if (!trie.startsWith(str)) {
			return;
		}
		if (trie.search(str)) {
			result.add(str);
		}
		visited[i][j] = true;
		dfs(board, visited, str, i - 1, j, trie, result);
		dfs(board, visited, str, i + 1, j, trie, result);
		dfs(board, visited, str, i, j - 1, trie, result);
		dfs(board, visited, str, i, j + 1, trie, result);
		visited[i][j] = false;
	}
}

// Trie Node
class TrieNodeDS {
	public TrieNodeDS[] children = new TrieNodeDS[26];
	public String item = "";
}

// Trie
class TrieDS {
	public TrieNodeDS root = new TrieNodeDS();

	public void insert(String word) {
		TrieNodeDS node = root;
		for (char c : word.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				node.children[c - 'a'] = new TrieNodeDS();
			}
			node = node.children[c - 'a'];
		}
		node.item = word;
	}

	public boolean search(String word) {
		TrieNodeDS node = root;
		for (char c : word.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				return false;
			}
			node = node.children[c - 'a'];
		}
		if (node.item.equals(word)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean startsWith(String prefix) {
		TrieNodeDS node = root;
		for (char c : prefix.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				return false;
			}
			node = node.children[c - 'a'];
		}
		return true;
	}
}
