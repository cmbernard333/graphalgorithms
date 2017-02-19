package com.beardfish.adjlist;

import java.util.*;

public class AdjListGraph<V> {
    /* maps a Vertex to its neighbors paired with the weight between them */
    public Map<V, Map<V,Double>> adjList;

    public AdjListGraph() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(V src, V dst, double weight) {
        /* check if an edge exists already */
        Map<V,Double> srcEdgeList = this.adjList.get(src);
        Map<V,Double> dstEdgeList = this.adjList.get(dst);
        if(srcEdgeList == null){
            srcEdgeList = new HashMap<>();
            this.adjList.put(src, srcEdgeList);
        }
        if(dstEdgeList == null){
            dstEdgeList = new HashMap<>();
            this.adjList.put(dst, dstEdgeList);
        }
        srcEdgeList.put(dst, weight);
        dstEdgeList.put(src, weight);
    }

    public boolean removeEdge(V src, V dst) {
        Map<V,Double> srcEdgeList = this.adjList.get(src);
        Map<V,Double> dstEdgeList = this.adjList.get(dst);
        return srcEdgeList.remove(dst) != null && dstEdgeList.remove(src) != null;
    }

    public boolean hasEdge(V src, V dst) {
        /* retrieve the edge list for the source and check to see if it contains dst */
        return (this.adjList.get(src) == null) ? false: this.adjList.get(src).containsKey(dst);
    }

    public Set<Map.Entry<V,Double>> getNeighbors(V src) {
        return this.adjList.get(src).entrySet();
    }
}