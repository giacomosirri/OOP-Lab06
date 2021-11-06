package it.unibo.oop.lab06.generics1;

import java.util.*;

public class GraphImpl<N> implements Graph<N> {

	private final Map<N, Set<N>> adjacencyMatrix = new HashMap<>();
	private boolean direct = false;
	
	public GraphImpl(final boolean direct) {
		this.direct = direct;
	}
	
	public void addNode(N node) {
		if (node != null) {
			// adds the node only if it is not already present in the matrix
			this.adjacencyMatrix.putIfAbsent(node, new HashSet<>());
		}
	}

	private void setTargetNode(N source, N target) {
		Set<N> newSetValues = this.adjacencyMatrix.get(source);
		newSetValues.add(target);
		this.adjacencyMatrix.put(source, newSetValues);
	}
		
	public void addEdge(N source, N target) {
		if (source != null & target != null) {
			this.setTargetNode(source, target);
			if (this.direct == false) {
				this.setTargetNode(target, source);
			}
		}
	}

	public Set<N> nodeSet() {
		return new HashSet<N>(this.adjacencyMatrix.keySet());
	}

	public Set<N> linkedNodes(N node) {
		Set<Map.Entry<N, Set<N>>> entries = this.adjacencyMatrix.entrySet();
		for (final var entry : entries) {
			if (entry.getKey().equals(node)) {
				return new HashSet<N>(entry.getValue());
			}
		}
		return null;
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
			var currentNode = queue.poll();
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
		final var path = new LinkedList<N>();
		var bfs = this.bfs(source);
		var currentNode = target;
		// recursively backtracks until it gets to the root of the predecessor-tree
		while (currentNode != null) {   
			path.addFirst(currentNode);
			currentNode = bfs.get(currentNode).father();
		}
		// if the target can be reached from source, the root of the predecessor-tree must be source
		if (path.getFirst().equals(source)) {	
			return path;
		} else {
			return null;
		}
	}
}
