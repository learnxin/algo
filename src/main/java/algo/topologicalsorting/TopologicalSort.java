package algo.topologicalsorting;


import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: wangran
 * @Description: 拓扑排序
 * @Date: 2021/12/24 上午9:29
 *  0->1
 *  1->3->5->7
 *  2->4->6->8
 *
 */
public class TopologicalSort {

    public AdjacencyList initGraph(){
        //a->b 表示a需要在b之前执行
        AdjacencyList adjL = new AdjacencyList(9);
        adjL.addEdge(1,0);
        adjL.addEdge(3,1);
        adjL.addEdge(5,3);
        adjL.addEdge(7,5);
        adjL.addEdge(4,2);
        adjL.addEdge(6,4);
        adjL.addEdge(8,6);
        return adjL;
    }
    @Test
    public void topoSortByKahn(){
        AdjacencyList adjacencyList = initGraph();
        //统计入度
        int[] degree = new int[adjacencyList.getV()];
        LinkedList<Integer>[] adj = adjacencyList.getAdj();
        for (int i = 0; i < adjacencyList.getV(); i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                Integer integer = adj[i].get(j);
                degree[integer]++;
            }
        }
        //引入queue顺序打印
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if(degree[i] == 0){
                deque.add(i);
            }
        }
        while (!deque.isEmpty()){
            Integer poll = deque.poll();
            System.out.print("->"+poll);
            //a->b b的入度-1
            for (int j = 0; j < adj[poll].size(); j++) {
                Integer integer = adj[poll].get(j);

                if(--degree[integer] == 0){
                    deque.add(integer);
                }
            }
        }

    }

    /**
     * 遍历当前角i的所有依赖项，最后输出i
     * @param i
     * @param adj
     * @param visit
     */
    private void dfs(int i, LinkedList<Integer>[] adj, boolean[] visit) {
        for (int j = 0; j < adj[i].size(); j++) {
            Integer integer = adj[i].get(j);
            if (!visit[integer]){
                visit[integer] = Boolean.TRUE;
                dfs(integer,adj,visit);
            }
        }
        System.out.print("->"+i);
    }

    @Test
    public void topoSortByDFS(){
        AdjacencyList adjacencyList = initGraph();
        int v = adjacencyList.getV();
        LinkedList<Integer>[] adj = adjacencyList.getAdj();
        boolean[] visit = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visit[i]){
                visit[i] = Boolean.TRUE;
                dfs(i,adj,visit);
            }
        }
    }

    @Test
    public void topoSortByBFS() {
        AdjacencyList adjacencyList = initGraph();
        int v = adjacencyList.getV();
        LinkedList<Integer>[] adj = adjacencyList.getAdj();
        boolean[] visit = new boolean[v];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < visit.length; i++) {
            if(visit[i]){
               continue;
            }
            deque.add(i);
            visit[i]= true;
            while (!deque.isEmpty()){
                Integer poll = deque.poll();
                System.out.print("->"+i);
                for (int j = 0; j < adj[poll].size(); j++) {
                    Integer w = adj[poll].get(j);
                    if(visit[w]){
                        continue;
                    }
                    deque.add(w);
                }

            }
        }

    }
}
