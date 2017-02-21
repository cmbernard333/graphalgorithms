/**
 * Created by christian on 3/11/14.
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
    public void testDjikstras() {
        AdjListGraph<Character> adjListGraph = new AdjListGraph(); // the graph
        List<Map.Entry<Character, Double>> solution = null;
        
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
        System.out.println(Arrays.toString(solution.toArray()));
    }
}
