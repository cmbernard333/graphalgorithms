package com.beardfish.test.adjlist;
/**
 * Created by christian on 2/20/17.
 */

import com.beardfish.adjlist.AdjListGraph;
import com.beardfish.adjlist.AdjListGraph.EdgeComparator;
import org.junit.*;

import java.util.PriorityQueue;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class AdjListGraphTest {



    @Test
    public void testAddEdges(){
        AdjListGraph<Character> adjListGraph = new AdjListGraph();
        adjListGraph.addEdge('a','b',7);
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('a','b'));
    }

    @Test
    public void testBuildSimpleGraph(){
        AdjListGraph<Character> adjListGraph = new AdjListGraph();
        adjListGraph.addEdge('a','b',7);
        adjListGraph.addEdge('b','d',15);
        adjListGraph.addEdge('d','e',5);
        adjListGraph.addEdge('e','f',9);
        adjListGraph.addEdge('f','a',14);
        adjListGraph.addEdge('c','a',9);
        adjListGraph.addEdge('c','b',10);
        adjListGraph.addEdge('c','d',11);
        adjListGraph.addEdge('c','f',2);

        /* check a neighbors */
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('a','b'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('a','f'));

        /* check b neighbors */
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('b','a'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('b','d'));

        /* check c neighbors */    
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('c','a'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('c','b'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('c','d'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('c','f'));

        /* check d neighbors */
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('d','b'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('d','e'));

        /* check e neighbors */
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('e','d'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('e','f'));

        /* check f neighbors */
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('f','a'));
        org.junit.Assert.assertTrue(adjListGraph.hasEdge('f','c'));
    }

    @Test
    public void testInternalEdgeComparator()
    {
        PriorityQueue<Map.Entry<Character,Double>> pq = new PriorityQueue<>(new AdjListGraph.EdgeComparator<Character>());
        pq.add(new SimpleEntry<Character,Double>('c',9.0));
        pq.add(new SimpleEntry<Character,Double>('b',7.0));
        org.junit.Assert.assertTrue(pq.poll().getValue().equals(7.0));
    }

    @Test
    public void testPrimms() {
        AdjListGraph<Integer> adjListGraph = new AdjListGraph(); // the graph
        Map<Integer,Map.Entry<Integer,Double>> solution = null;

    	adjListGraph.addEdge(0, 1, 4);
    	adjListGraph.addEdge(0, 7, 8);
    	adjListGraph.addEdge(1, 2, 8);
    	adjListGraph.addEdge(1, 7, 11);
    	adjListGraph.addEdge(2, 3, 7);
    	adjListGraph.addEdge(2, 8, 2);
    	adjListGraph.addEdge(2, 5, 4);
    	adjListGraph.addEdge(3, 4, 9);
    	adjListGraph.addEdge(3, 5, 14);
    	adjListGraph.addEdge(4, 5, 10);
    	adjListGraph.addEdge(5, 6, 2);
    	adjListGraph.addEdge(6, 7, 1);
    	adjListGraph.addEdge(6, 8, 6);
    	adjListGraph.addEdge(7, 8, 7);

	solution = adjListGraph.minimumSpanningTree(0);
	System.out.println(solution);
    }



    @Test
    public void testDjikstrasComplexGraph() {
        AdjListGraph<Integer> adjListGraph = new AdjListGraph(); // the graph
        Map<Integer,Map.Entry<Integer,Double>> solution = null;

        adjListGraph.addEdge(0,1,4);
        adjListGraph.addEdge(0,7,8);
        adjListGraph.addEdge(1,2,8);
        adjListGraph.addEdge(1,7,11);
        adjListGraph.addEdge(6,5,2);
        adjListGraph.addEdge(7,8,7);
        adjListGraph.addEdge(7,6,1);
        adjListGraph.addEdge(2,3,7);
        adjListGraph.addEdge(2,5,4);
        adjListGraph.addEdge(2,8,2);
        adjListGraph.addEdge(8,6,6);
        adjListGraph.addEdge(3,5,14);
        adjListGraph.addEdge(3,4,4);
        adjListGraph.addEdge(5,4,10);

	solution = adjListGraph.shortestPath(0,4);
	printSolution(4,solution);
    }

    @Test
    public void testDjikstras() {
        AdjListGraph<Character> adjListGraph = new AdjListGraph(); // the graph
        Map<Character,Map.Entry<Character,Double>> solution = null;
        
        adjListGraph.addEdge('a','b',7);
        adjListGraph.addEdge('b','d',15);
        adjListGraph.addEdge('d','e',5);
        adjListGraph.addEdge('e','f',9);
        adjListGraph.addEdge('f','a',14);
        adjListGraph.addEdge('c','a',9);
        adjListGraph.addEdge('c','b',10);
        adjListGraph.addEdge('c','d',11);
        adjListGraph.addEdge('c','f',2);

        solution = adjListGraph.shortestPath('a','e');
        printSolution('e',solution);
    }

    public <V> void printSolution(V dst, Map<V, Map.Entry<V,Double>> solution)
    {
        Map.Entry<V,Double> edge = solution.get(dst);
        V prevVertex = dst;
	System.out.print(dst+"->");
        do {
            System.out.print(edge.getKey()+"->");
            prevVertex = edge.getKey();
            edge = solution.get(edge.getKey());
        } while(edge!=null);
    }
}
