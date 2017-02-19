package com.beardfish.bfs.adjlist;

import java.util.*;
/**
 * Represents an edge between two vertices in a vertices
 */
public final class Edge implements Comparable<Edge>{

	private final Vertex src;
	private final Vertex dst;
	private double weight;

	public Edge(Vertex src, Vertex dst, double weight) {
		this.src = src;
		this.dst = dst;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge edge)
	{
		return Double.compareTo(this.getWeight(),edge.getWeight()); // if left comes before right (<0)
	}

	public Vertex getSrc() {
		return this.src;
	}

	public Vertex getDst() {
		return this.dst;
	}

	public double getWeight()
	{
		return this.weight;
	}

}