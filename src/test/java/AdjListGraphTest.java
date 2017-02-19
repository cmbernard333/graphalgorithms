/**
 * Created by christian on 3/11/14.
 */

import com.beardfish.adjlist.AdjListGraph;
import org.junit.*;

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
}
