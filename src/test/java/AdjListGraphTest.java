/**
 * Created by christian on 3/11/14.
 */

import com.beardfish.adjlist.AdjListGraph;
import org.junit.*;

import java.util.PriorityQueue;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

public class AdjListGraphTest {

    /* simple comparator to allow entries in AdjListGraph to be sorted in a PriorityQueue by smallest value */
    private class EdgeComparator<V> implements Comparator<Map.Entry<V,Double>>
    {
        @Override
        public int compare(Map.Entry<V,Double> edgeA, Map.Entry<V,Double> edgeB) {
            return edgeA.getValue().compareTo(edgeB.getValue());
        }
    }

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
        PriorityQueue<Map.Entry<Character,Double>> pq = new PriorityQueue<>(new EdgeComparator<Character>());
        pq.add(new SimpleEntry<Character,Double>('c',9.0));
        pq.add(new SimpleEntry<Character,Double>('b',7.0));
        org.junit.Assert.assertTrue(pq.poll().getValue().equals(7.0));
    }

    @Test
    public void testDjikstras() {
        AdjListGraph<Character> adjListGraph = new AdjListGraph(); // the graph
        Map<Character,Double> dist = new HashMap<>(); // maps the vertex and the distance from the source
        Set<Character> explored = new HashSet<>(); // set of explored vertices
        PriorityQueue<Map.Entry<Character,Double>> pq = new PriorityQueue<>(new EdgeComparator<Character>()); // the priority queue to keep track of minimum edges
        Map.Entry<Character,Double> edge = new SimpleEntry<Character,Double>('a',0.0); // the first edge to explore from

        Character vertex = null;
	Character vertexNeighbor = null;
        Double weight = null;
	Double vertexNeighborWeight = null;
	Double altWeight = null;

        adjListGraph.addEdge('a','b',7);
        adjListGraph.addEdge('b','d',15);
        adjListGraph.addEdge('d','e',5);
        adjListGraph.addEdge('e','f',9);
        adjListGraph.addEdge('f','a',14);
        adjListGraph.addEdge('c','a',9);
        adjListGraph.addEdge('c','b',10);
        adjListGraph.addEdge('c','d',11);
        adjListGraph.addEdge('c','f',2);

	/* distance from itself to all other nodes is current infinity */
	for(Character vertex : adjListGraph.getVertices())
	{
		dist.put(vertex, Double.POSITIVE_INFINITY);
	}	

        /* distance to itself is 0 */
        dist.put('a',0.0);
        /* append self (i.e. src) to start here */
        pq.add(edge);

        /* NOTE: java does not have a built in priority queue with decrease key - you can just add the new ndoe again but with a lower weight */
        /* we are exploring the neighbors as Map.Entry<Character,Double> where the double represents the weight from the current vertex */
        while(!pq.isEmpty())
        {
            edge = pq.poll();
            vertex = edge.getKey();
            weight = edge.getValue();
            explored.add(vertex); // append the vertex we've already explored
            for(Map.Entry<Character,Double> edge : adjListGraph.getNeighbors(vertex)) 
            {
		vertexNeighbor = edge.getKey();
		vertexNeighborWeight = edge.getValue();
                if(!explored.contains(vertexNeighbor) {
		    /* distance of vertex from src + distance between current vertex and neighbor < distance from src */
		    altWeight = dist.get(vertex).getValue() + vertexNeighborWeight;
		    if(altWeight < dist.get(vertex).getValue()) 
		    {
		    	pq.add(new SimpleEntry());	    
		    }
                }
            }
        }
    }
}
