package com.beardfish.adjmatrix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Adjacency Matrix Representation of Graph
 * Allows negative weights
 * Created by Christian on 4/27/2014.
 */
public class AdjMatrixGraph {

    public int adjMatrix [][];

    public AdjMatrixGraph(int size) {
        if(size<0 || size>Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        this.adjMatrix = new int[size][size];
    }

    public boolean addEdge(int origin, int destination, int weight) {
        if(origin >adjMatrix.length || destination>adjMatrix.length) {
            return false;
        }
        this.adjMatrix[origin][destination]=weight;
        return true;
    }

    public boolean hasEdge(int origin, int destination) {
        return this.adjMatrix[origin][destination]!=0;
    }

    /**
     * Returns a list of the weights for each edge between the origin and all its neighbors
     * @param origin
     * @return List<Integer> of neighbors or null if vertex doesn't exist
     */
    public List<Integer> getWeightsForNeighbors(int origin) {
        if(origin<0 || origin>this.adjMatrix.length) {
            return null;
        }
        List<Integer> weights = new ArrayList<Integer>();
        for(int i = 0; i<this.adjMatrix[origin].length;i++) {
            if(this.adjMatrix[origin][i]!=0) {
                weights.add(this.adjMatrix[origin][i]);
            }
        }
        return weights;
    }

    /**
     * Returns a list of the neighbors for a particular vertex
     * @param origin
     * @return List<Integer> of neighbors or null if vertex doesn't exist
     */
    public List<Integer> getNeighbors(int origin) {
        if(origin<0 || origin>this.adjMatrix.length) {
            return null;
        }
        List<Integer> neighbors = new ArrayList<Integer>();
        for(int i = 0; i<this.adjMatrix[origin].length;i++) {
            if(this.adjMatrix[origin][i]!=0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    /**
     * Retrieves the weight for a particular set of vertices
     * @param origin
     * @param destination
     * @return
     */
    public int getWeight(int origin, int destination) {
        if(origin>this.adjMatrix.length || destination>this.adjMatrix.length || origin <0 || destination<0) {
            throw new IllegalArgumentException();
        }
        return this.adjMatrix[origin][destination];
    }

}
