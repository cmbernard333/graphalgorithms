package com.beardfish.bfs.adjlist;



import java.util.*;

/**
 * Created by christian on 3/11/14.
 */
public class AdjListGraph<V> {

    public final Map<V,Vertex> vertices;

    public AdjListGraph () {
        this.vertices = new HashMap<V,Vertex>();
    }

    public boolean addEdge(V src, V dst) {
        return this.addEdge(src,dst,0);
    }

    public boolean addEdge(V src, V dst, double weight) {
        /* verify the vertices */
        Vertex srcVert = this.getVertex(src);
        if(srcVert==null) {
            srcVert = new Vertex(src);
            this.vertices.put(src, srcVert);
        }
        Vertex dstVert = this.getVertex(dst);
        if(dstVert==null) {
            dstVert = new Vertex(dst);
            this.vertices.put(dst, new Vertex(dst));
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
        return this.vertices.get(source);
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
     * Return a path object repesenting the shortest path between two values
     * @param src - value
     * @param dst - value
     * @return Path object
     */
    public Path getShortestPath(V src, V dst) {
        /* check to make sure vertices exist */
        Vertex srcVert = this.getVertex(src);
        if(srcVert==null) { return null; }
        Vertex dstVert = this.getVertex(dst);
        if(dstVert==null) { return null; }
        /* check for direct edge between nodes */
        Edge edge = this.getEdge(srcVert,dstVert);
        Path path = new Path();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue(new EdgeComparator<);
        if(edge!=null) {
            path.addEdge(edge);
            return path;
        }
        /* build the min prority queue */
        priorityQueue.add(srcVert.getNeighbors());
        while(!priorityQueue.isEmpty())
        {
            priorityQueue.poll();
        }
    }

    private class EdgeComparatorMin implements Comparator<Edge>
    {
        @Override
        public int compare(Edge a, Edge b)
        {
            return !Edge.compareTo(a,b);
        }
    }

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
            return Double.compareTo(this.getWeight(),edge.getWeight()); // if left comes before right (>0)
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

    /**
     * Represents a vertex in a vertices with a particular value
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

    /**
     * Node class representing a vertex with a distance from another vertex
     */
    public final class Node implements Comparable<Node>{
        public Vertex value;
        public Node previous;
        private long distance = Integer.MAX_VALUE;

        public Node(Vertex v) {
            this.value = v;
        }

        @Override
        public int compareTo(Node node) {
            if(this.distance>node.distance) {
                return 1;
            } else if(this.distance==node.distance) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    private final class VertexPair {

        private Vertex src;
        private Vertex dst;

        private VertexPair(Vertex src, Vertex dst) {
            this.src = src;
            this.dst = dst;
        }

        @Override
        public boolean equals(Object o) {
            if(!this.getClass().equals(o.getClass())) {
                return false;
            }
            VertexPair pair = (VertexPair) o;
            return this.src == pair.src && this.dst == pair.dst;
        }

        @Override
        public int hashCode() {
            int h = 0;
            h+= this.src == null ? 0 : src.hashCode();
            h+= this.dst == null ? 0 : dst.hashCode();
            return h;
        }
    }
}
