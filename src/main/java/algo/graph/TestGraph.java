package algo.graph;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: test graph
 * 0 1 2
 * 3 4 5
 *   6 7
 * @Date: 2021/7/14 下午10:25
 */
public class TestGraph {

    public AdjacencyList initGraph(){
        AdjacencyList adjL = new AdjacencyList(8);
        adjL.addEdge(0,1);
        adjL.addEdge(0,3);
        adjL.addEdge(1,2);
        adjL.addEdge(1,4);
        adjL.addEdge(2,5);
        adjL.addEdge(3,4);
        adjL.addEdge(4,5);
        adjL.addEdge(4,6);
        adjL.addEdge(5,7);
        adjL.addEdge(6,7);
        return adjL;
    }

    @Test
    public void testBFS(){
        AdjacencyList adjacencyList = initGraph();
        adjacencyList.breadthFirstSearch(0,6);
    }


    @Test
    public void testDFS(){
        AdjacencyList adjacencyList = initGraph();
        adjacencyList.depthFirstSearch(1,7);
    }

}
