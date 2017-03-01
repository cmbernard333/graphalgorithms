package com.beardfish.adjlist;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.AbstractMap.SimpleEntry;

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

    public Set<V> getVertices() {
        return this.adjList.keySet();
    }

    /* simple comparator to allow entries in AdjListGraph to be sorted in a PriorityQueue by smallest value */
    public static class EdgeComparator<E> implements Comparator<Map.Entry<E,Double>>
    {
        @Override
        public int compare(Map.Entry<E,Double> edgeA, Map.Entry<E,Double> edgeB) {
            return edgeA.getValue().compareTo(edgeB.getValue());
        }
    }

    public Map<V,Map.Entry<V,Double>> shortestPath(V src, V dst) {
        Map<V,Double> dist = new HashMap<>(); // maps the vertex and the distance from the source
        Map<V,Map.Entry<V,Double>> prev = new HashMap<>();
        Set<V> explored = new HashSet<>(); // set of explored vertices
        PriorityQueue<Map.Entry<V,Double>> pq = new PriorityQueue<>(new EdgeComparator<V>()); // the priority queue to keep track of minimum edges
        Map.Entry<V,Double> edge = new SimpleEntry<V,Double>(src,0.0); // the first edge to explore from

        V vertex = null;
        V vertexNeighbor = null;
        Double weight = null;
        Double vertexNeighborWeight = null;
        Double altWeight = null;

        /* distance from itself to all other nodes is current infinity */
        for(V vertexInGraph : this.getVertices())
        {
            dist.put(vertexInGraph, Double.POSITIVE_INFINITY);
        }   

        /* distance to itself is 0 */
        dist.put(src,0.0);
        /* append self (i.e. src) to start here */
        pq.add(edge);

        /* NOTE: java does not have a built in priority queue with decrease key - you can just add the new ndoe again but with a lower weight */
        /* we are exploring the neighbors as Map.Entry<Character,Double> where the double represents the weight from the current vertex */
        while(!pq.isEmpty())
        {
            edge = pq.poll(); // extract the minimum
            vertex = edge.getKey();
            weight = edge.getValue();
            explored.add(vertex); // append the vertex we've already explored
            /* check to see if we should stop */
            if(vertex.equals(dst))
            {
                return prev;
            }

            /* 
                TODO: not catching the case where a previous link is smaller than a new minimum being considered 
                TODO: this needs to remove a node from the solution if a previous link is smaller than adduing a new minimum 
                i.e. one of the new neighbors connects back to the src and has a smaller distance
            */
            for(Map.Entry<V,Double> neighbor : this.getNeighbors(vertex))
            {
                vertexNeighbor = neighbor.getKey();
                vertexNeighborWeight = neighbor.getValue();
                if(!explored.contains(vertexNeighbor)) {
                    altWeight = dist.get(vertex) + vertexNeighborWeight; // for the base case: distance to self will be 0 at the beginning
                    /* distance of vertex from src + distance between current vertex and neighbor < distance from src */
                    /* more simply: if new route is shorter than the route directly from src to the current neighbor */
                    if(altWeight < dist.get(vertexNeighbor)) {
                        dist.put(vertexNeighbor, altWeight);
                        prev.put(vertexNeighbor, edge);
                        pq.add(new SimpleEntry<V,Double>(vertexNeighbor, dist.get(vertexNeighbor))); /* add into the queue the new minimum distance */
                    }   
                }
            }
        }
        /* TODO */
        return null;
    }

    public Map<V,Map.Entry<V,Double>> minimumSpanningTree(V src) {
        Map<V,Double> dist = new HashMap<>(); // maps the vertex and the distance from the source
        Map<V,Map.Entry<V,Double>> parent = new HashMap<>();
        Set<V> explored = new HashSet<>(); // set of explored vertices
        PriorityQueue<Map.Entry<V,Double>> pq = new PriorityQueue<>(new EdgeComparator<V>()); // the priority queue to keep track of minimum edges
        Map.Entry<V,Double> edge = new SimpleEntry<V,Double>(src,0.0); // the first edge to explore from

        V vertex = null;
        V vertexNeighbor = null;
        Double weight = null;
        Double vertexNeighborWeight = null;
        Double altWeight = null;

        /* distance from itself to all other nodes is current infinity */
        for(V vertexInGraph : this.getVertices())
        {
            dist.put(vertexInGraph, Double.POSITIVE_INFINITY);
        }   

        /* distance to itself is 0 */
        dist.put(src,0.0);
        /* append self (i.e. src) to start here */
        pq.add(edge);

        /* NOTE: java does not have a built in priority queue with decrease key - you can just add the new ndoe again but with a lower weight */
        /* we are exploring the neighbors as Map.Entry<Character,Double> where the double represents the weight from the current vertex */
        while(!pq.isEmpty())
        {
            edge = pq.poll(); // extract the minimum
            vertex = edge.getKey();
            weight = edge.getValue();
            explored.add(vertex); // append the vertex we've already explored

            for(Map.Entry<V,Double> neighbor : this.getNeighbors(vertex))
            {
                vertexNeighbor = neighbor.getKey();
                vertexNeighborWeight = neighbor.getValue();
                if(!explored.contains(vertexNeighbor)) {
		    /* minimum spanning tree is trying to find the minimum tree composed of minimum weights of the edges */
                    if(vertexNeighborWeight < dist.get(vertexNeighbor)) {
                        dist.put(vertexNeighbor, vertexNeighborWeight);
                        parent.put(vertexNeighbor, edge);
                        pq.add(new SimpleEntry<V,Double>(vertexNeighbor, dist.get(vertexNeighbor))); /* add into the queue the new minimum distance */
                    }   
                }
            }
        }
        /* TODO */
        return parent;  
    }
}
