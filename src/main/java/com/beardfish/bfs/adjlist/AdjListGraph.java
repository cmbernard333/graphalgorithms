package com.beardfish.bfs.adjlist;



import java.util.*;

/**
 * Created by christian on 3/11/14.
 */
public class AdjListGraph<E,V> {

    public final Map<V,Vertex> graph;

    public AdjListGraph () {
        this.graph = new HashMap<V,Vertex>();
    }

    public boolean addEdge(V src, V dst) {
        return this.addEdge(src,dst,0);
    }

    public boolean addEdge(V src, V dst, double weight) {
        /* verify the vertices */
        Vertex srcVert = this.getVertex(src);
        if(srcVert==null) {
            srcVert = new Vertex(src);
            this.graph.put(src,srcVert);
        }
        Vertex dstVert = this.getVertex(dst);
        if(dstVert==null) {
            dstVert = new Vertex(dst);
            this.graph.put(dst,new Vertex(dst));
        }
        /* verify the edge */
        Edge edge = this.getEdge(srcVert,dstVert);
        if(edge!=null) {
            return false;
        } else {
            edge = new Edge(srcVert,dstVert,weight);
            srcVert.edges.put(dstVert,edge);
            dstVert.edges.put(srcVert,edge);
        }
        return true;
    }

    public Edge getEdge(V source, V target) {
        Vertex srcVert = getVertex(source);
        if(srcVert==null) {
            return null;
        }
        Vertex dstVert = getVertex(target);
        if(dstVert==null) {
            return null;
        }
        return this.getEdge(srcVert,dstVert);
    }

    public Edge getEdge(Vertex src, Vertex dst) {
        return src.edges.get(dst);
    }

    public Vertex getVertex(V source) {
        return this.graph.get(source);
    }

    public boolean removeEdge(V src, V dst) {
        Edge edge = this.getEdge(src,dst);
        if(edge!=null) {
            edge.getSrc().edges.remove(edge);
            edge.getDst().edges.remove(edge);
            return true;
        }
        return false;
    }

    /**
     * Represents an edge between two vertices in a graph
     */
    public final class Edge {

        private final Vertex src;
        private final Vertex dst;
        private double weight;

        public Edge(Vertex src, Vertex dst, double weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        public Vertex getSrc() {
            return this.src;
        }

        public Vertex getDst() {
            return this.dst;
        }

    }

    /**
     * Represents a vertex in a graph with a particular value
     */
    public final class Vertex {

        private final V value;
        private final Map<Vertex,Edge> edges;

        public Vertex(V value) {
            this.value = value;
            this.edges = new HashMap<Vertex,Edge>();
        }

        public V getValue() {
            return this.value;
        }

        public Collection<Edge> getNeighbors() {
            return edges.values();
        }
    }

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
}
