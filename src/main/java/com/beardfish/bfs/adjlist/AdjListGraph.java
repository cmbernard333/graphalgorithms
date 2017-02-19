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
		
    }
}
