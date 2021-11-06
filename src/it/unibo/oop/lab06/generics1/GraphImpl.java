package it.unibo.apice.oop.p11gencoll2.exercise;

import java.util.*;
import it.unibo.apice.oop.p11gencoll2.abstractions.Pair;

public class GraphImpl<N> implements Graph<N> {

	private final Map<N, Set<N>> adjacencyMatrix = new HashMap<>();
	private boolean direct = false;
	
	public GraphImpl(final boolean direct) {
		this.direct = direct;
	}

	public void addNode(N node) {
		if (node != null) {
			this.adjacencyMatrix.put(node, new HashSet<>());
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
		final var nodes = this.adjacencyMatrix.keySet();
		return nodes;
	}

	public Set<N> linkedNodes(N node) {
		for (N currentNode : this.adjacencyMatrix.keySet()) {
			if (node.equals(currentNode)) {
				return this.adjacencyMatrix.get(currentNode);
			}
		}
		return null;
	}
	
	private HashMap<N, Pair<Boolean, N>> bfs(N source) {
		final var bfs = new HashMap<N, Pair<Boolean, N>>();
		for (N node : this.adjacencyMatrix.keySet()) {
			bfs.put(node, new Pair<Boolean, N>(false, null)); // inizializzazione dei nodi
		}
		bfs.replace(source, new Pair<Boolean, N>(true, null)); // inizializzazione della sorgente
		Queue<N> queue = new ArrayDeque<N>();
		queue.add(source);
		while (queue.peek() != null) {
			var currentNode = queue.poll();
			for (N node : this.adjacencyMatrix.get(currentNode)) {
				if (bfs.get(node).getFirst() == false) {
					bfs.put(node, new Pair<Boolean, N>(true, currentNode));
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
		while (currentNode != null) {
			path.addFirst(currentNode);
			currentNode = bfs.get(currentNode).getSecond();
		}
		if (path.contains(source)) {
			return path;
		} else {
			return null;
		}
	}
}
