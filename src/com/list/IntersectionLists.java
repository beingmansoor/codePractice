package com.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntersectionLists {
	
	public List<List<Integer>> getIntersectionLists(Node[] array) {

		HashMap<Node, List<Integer>> sol = new HashMap<Node, List<Integer>>();

		for (int i = 0; i < array.length; i++) {
			Node node = array[i];

			while (node.next != null) {
				node = node.next;
			}

			List<Integer> list = sol.get(node);
			if (list == null) {
				list = new ArrayList<Integer>();
			}

			list.add(i);
			sol.put(node, list);

		}

		return (List<List<Integer>>) sol.values();

	}
}
