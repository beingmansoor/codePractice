package com.topological.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ReconstructItinerary {

	public List<String> findItinerary(List<List<String>> tickets) {

		HashMap<String, PriorityQueue<String>> flights = new HashMap<>();
		LinkedList<String> path = new LinkedList<>();
		for (List<String> ticket : tickets) {
			flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
			flights.get(ticket.get(0)).add(ticket.get(1));
		}
		dfs("JFK", flights, path);
		return path;
	}

	public void dfs(String departure, HashMap<String, PriorityQueue<String>> flights, LinkedList<String> path) {
		PriorityQueue<String> arrivals = flights.get(departure);
		while (arrivals != null && !arrivals.isEmpty()) {
			dfs(arrivals.poll(), flights, path);
		}
		path.addFirst(departure);
	}

	public static void main(String[] args) {
		ReconstructItinerary r = new ReconstructItinerary();

		List<List<String>> tickets = new ArrayList<>();
		tickets.add(Arrays.asList(new String[] { "JFK", "SFO" }));
		tickets.add(Arrays.asList(new String[] { "JFK", "ATL" }));
		tickets.add(Arrays.asList(new String[] { "SFO", "ATL" }));
		tickets.add(Arrays.asList(new String[] { "ATL", "JFK" }));
		tickets.add(Arrays.asList(new String[] { "ATL", "SFO" }));

		System.out.println(r.findItinerary(tickets));
	}

}
