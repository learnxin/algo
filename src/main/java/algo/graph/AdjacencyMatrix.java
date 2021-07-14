package algo.graph;

/**
 * @Author: wangran
 * @Description: 领接矩阵
 * @Date: 2021/7/14 下午10:33
 */
public class AdjacencyMatrix {

    private int[][] vertexes;

    public AdjacencyMatrix(int v) {
        this.vertexes = new int[v][v];
    }

    /**
     *有向图s->t 即 a[s][t] = 1;
     */
    public void addEdge(int s,int t){
        vertexes[s][t] = 1;
    }
}
