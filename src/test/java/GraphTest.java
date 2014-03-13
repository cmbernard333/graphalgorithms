/**
 * Created by christian on 3/11/14.
 */

import com.beardfish.bfs.Graph;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class GraphTest {

    @Test
    public void testGraphBFS() {
        Graph<Character> charGraph = new Graph<Character>();
        /* add nodes to the graph */
        charGraph.addVertex('a', 'b');
        charGraph.addVertex('a', 'c');
        charGraph.addVertex('b', 'd');
        charGraph.addVertex('b', 'e');
        charGraph.addVertex('c', 'f');
        charGraph.addVertex('c', 'g');
        charGraph.addVertex('e', 'h');
        Queue<Character> queue = new LinkedList<Character>();
        /* add the root */
        queue.add('a');
        /* do the bfs */
        System.out.println("BFS");
        while (!queue.isEmpty()) {
            Character node = queue.poll();
            System.out.println(node);
            Set<Character> children = charGraph.getNodes(node);
            if (children == null) {
                continue;
            } else {
                queue.addAll(children);
            }
        }
    }

    @Test
    public void testGraphDFS() {
        Graph<Character> charGraph = new Graph<Character>();
        Map<Character, Boolean> discovered = new HashMap<Character, Boolean>();
        Stack<Character> nodeStack = new Stack<Character>();
        /* add nodes to the graph */
        charGraph.addVertex('a', 'b');
        charGraph.addVertex('a', 'c');
        charGraph.addVertex('b', 'd');
        charGraph.addVertex('b', 'e');
        charGraph.addVertex('c', 'f');
        charGraph.addVertex('c', 'g');
        charGraph.addVertex('e', 'h');
        /* add the root */
        nodeStack.push('a');
        System.out.println("DFS");
        while (!nodeStack.isEmpty()) {
            Character node = nodeStack.pop();
            /* check to make sure the node hasn't already been discovered */
            if (discovered.get(node)==null || !discovered.get(node).equals(true)) {
                System.out.println(node);
                discovered.put(node, true);
                Set<Character> adjacentNodes = charGraph.getNodes(node);
                if (adjacentNodes != null) {
                    /* this loop will pick right nodes as opposed to left nodes first */
                    for (Character adjNode : adjacentNodes) {
                        nodeStack.push(adjNode);
                    }
                }
            }
        }


    }
}
