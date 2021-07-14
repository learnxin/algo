package algo.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: wangran
 * @Description: 邻接表存储无向图
 * @Date: 2021/7/14 下午10:03
 */
public class AdjacencyList {
    /**
     * 顶点 vertex
     */
    private int v;

    /**
     * 邻接表
     */
    private LinkedList<Integer>[] adj;


    public AdjacencyList(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList();
        }
    }

    /**
     *无向图，有向图/逆邻接表添加对应向即可
     */
    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索类似二叉树层次遍历
     * queue,遍历完一个vertex的edge后存放下次遍历的vertex
     * visited 顶点是否遍历过
     * prev  顶点的上顶点
     * @param s
     * @param t
     */
    public void breadthFirstSearch(int s,int t){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean[] visited = new boolean[v];
        visited[s] = true;
        int[] prev = new int[v];
        Arrays.fill(prev, -1);

        while (queue.size()>0){
            Integer poll = queue.poll();
            LinkedList<Integer> edges = adj[poll];
            for (Integer integer:edges
                 ) {
                if(!visited[integer]){
                    visited[integer] = true;
                    prev[integer] = poll;
                    queue.add(integer);
                    if(integer == t){
                        print(prev,t,s);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 深度优先搜索
     * @param s
     * @param t
     */
    public void depthFirstSearch(int s,int t){
        boolean[] visited = new boolean[v];
        visited[s] = true;
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        boolean found = false;
        recurDfs(found,prev,visited,s,t);
        print(prev,t,s);
    }

    private void recurDfs(boolean found, int[] prev, boolean[] visited, int s, int t) {
        if(found){
            return;
        }
        LinkedList<Integer> edges = adj[s];
        for (Integer integer :edges
             ) {
            if(!visited[integer]){
                visited[integer] =true;
                prev[integer] = s;
                if(integer == t){
                    found = true;
                }
                recurDfs(found,prev,visited,integer,t);
            }
        }

    }

    /**
     * 从t打印到s
     * @param prev
     * @param t
     * @param s
     */
    private void print(int[] prev, int t, int s) {
//        while (prev[t] != -1 && t!=s){
//            System.out.print(t+" ");
//            t = prev[t];
//        }
//        System.out.print(t+" ");

        if (prev[t] != -1 && t!=s){
            print(prev,prev[t],s);
        }
        System.out.print(t+" ");
    }


}
