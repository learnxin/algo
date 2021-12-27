package algo.topologicalsorting;

import lombok.Data;

import java.util.LinkedList;

/**
 * @Author: wangran
 * @Description: 邻接表存储有图
 * @Date: 2021/7/14 下午10:03
 */
@Data
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
     *有向图 s->t
     */
    public void addEdge(int s,int t){
        adj[s].add(t);
    }


}
