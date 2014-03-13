package com.beardfish.bfs;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by christian on 3/11/14.
 */
public class Graph<E> {

    Map<E,Set<E>> adjList;

    public Graph() {
        this.adjList = new LinkedHashMap<E,Set<E>>();
    }

    /**
     * Adds a new vertex to the graph with the specified GNode<E> objects
     * @param left - the node to add a child to
     * @param right - the child node
     * @return true if no vertex previously existed; else false
     */
    public boolean addVertex(E left, E right) {
        if(!this.adjList.containsKey(left)) {
            this.adjList.put(left,new TreeSet<E>());
        }
        return this.adjList.get(left).add(right);
    }

    public Set<E> getNodes(E node) {
        return this.adjList.get(node);
    }

}
