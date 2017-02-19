package com.beardfish.adjlist;

import java.util.*;

/**
 * Represents a path from a source vertex to a dst vertex
 */
public final class Path {
	public List<Edge> edges;
	public double weight;

	public Path() {
		this.edges = new LinkedList<Edge>();
		this.weight = 0;
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
		this.weight += edge.weight;
	}
}