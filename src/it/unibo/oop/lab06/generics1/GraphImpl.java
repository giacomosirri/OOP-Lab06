package it.unibo.oop.lab06.generics1;

import java.util.*;

public class GraphImpl<N> implements Graph<N> {

	private final Map<N, Set<N>> adjacencyMatrix = new HashMap<>();
	private final boolean direct;

	public GraphImpl(final boolean direct) {
		this.direct = direct;
	}
	
	public void addNode(N node) {
		if (node != null) {
			// add the node only if it is not already present in the matrix
			this.adjacencyMatrix.putIfAbsent(node, new HashSet<>());
		}
	}

	private boolean nodeExists(N node) {
		return this.adjacencyMatrix.keySet().contains(node);
	}
	
	public void addEdge(N source, N target) {
		// add the edge only if both nodes have already been added to the graph
		if (nodeExists(source) && nodeExists(target)) {
			this.adjacencyMatrix.get(source).add(target);
			// an indirect graph also has an edge from *target* to *source* 
			if (this.direct == UseGraph.INDIRECT_GRAPH) {
				this.adjacencyMatrix.get(target).add(source);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Set<N> nodeSet() {
		return new HashSet<N>(this.adjacencyMatrix.keySet());
	}

	public Set<N> linkedNodes(N node) {
		if (nodeExists(node)) {
			return new HashSet<N>(this.adjacencyMatrix.get(node));
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private static class BfsAttributes<N> {
		
		private boolean reachable = false;
		private N father = null;
		
		private BfsAttributes(boolean reached, N father) {
			this.reachable = reached;
			this.father = father;
		}
		
		private boolean isReachable() {
			return this.reachable;
		}
		
		private N father() {
			return this.father;
		}
	}
	
	private HashMap<N, BfsAttributes<N>> bfs(N source) {
		final var bfs = new HashMap<N, BfsAttributes<N>>();
		for (N node : this.adjacencyMatrix.keySet()) {
			bfs.put(node, new BfsAttributes<N>(false, null)); 	// nodes initialization
		}
		bfs.replace(source, new BfsAttributes<N>(true, null)); 	// source initialization
		Queue<N> queue = new ArrayDeque<N>();
		queue.add(source);
		// expansion of reachable nodes
		while (queue.peek() != null) {							
			final var currentNode = queue.poll();
			for (N node : this.adjacencyMatrix.get(currentNode)) {
				if (bfs.get(node).isReachable() == false) {
					bfs.put(node, new BfsAttributes<N>(true, currentNode));
					queue.add(node);
				}
			}
		}
		return bfs;
	}
	
	public List<N> getPath(N source, N target) {
		// it makes sense to find a path only if both *source* and *target* are in the graph
		if (nodeExists(source) && nodeExists(target)) {
			final var path = new LinkedList<N>();
			final var bfs = this.bfs(source);
			var currentNode = target;
			// recursively backtracks until it gets to the root of the predecessor-tree
			while (currentNode != null) {   
				path.addFirst(currentNode);
				currentNode = bfs.get(currentNode).father();
			}
			// if *target* can be reached from *source*, the root of the predecessor-tree must be *source*
			if (path.getFirst().equals(source)) {	
				return path;
			} else {
				return null;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
