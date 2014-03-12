/**
 * Created by christian on 3/11/14.
 */

import com.beardfish.bfs.Graph;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphTest {

    @Test
    public void testGraphBFS() {
        Graph<Character> charGraph = new Graph<Character>();
        /* add nodes to the graph */
        charGraph.addVertex('a','b');
        charGraph.addVertex('a','c');
        charGraph.addVertex('b','d');
        charGraph.addVertex('b','e');
        charGraph.addVertex('c','f');
        charGraph.addVertex('c','g');
        charGraph.addVertex('e','h');
        /* do the bfs */
        Queue<Character> queue = new LinkedList<Character>();
        /* add the root */
        queue.add('a');
        while(!queue.isEmpty()) {
            Character node = queue.poll();
            System.out.println(node);
            Set<Character> children = charGraph.getNodes(node);
            if(children==null) {
                continue;
            } else {
                queue.addAll(children);
            }
        }
    }
}
