package com.beardfish.bfs.adjlist;

import java.util.*;
/**
 * Represents a vertex in a vertices with a particular value
 */
public final class Vertex {

	private final V value;
	private final List<Edge> edges;

	public Vertex(V value) {
		this.value = value;
		this.edges = new LinkedList();
	}

	public V getValue() {
		return this.value;
	}

	public Collection<Edge> getNeighbors() {
		return this.edges;
	}
	
	@Override
	public boolean equals(Object obj) {
		Vertex ver  = null;
		if(obj == null || !obj instanceof Vertex)
		{
			return false;
		}
		ver = (Vertex) obj;
		
		return this.getValue().equals(ver);
	}
	
	@Override
	public int hashCode() {
		return this.getValue().hashCode() + this.edges.hashCode();
	}
}