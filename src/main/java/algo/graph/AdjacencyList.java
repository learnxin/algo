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
     *
     * 广度优先搜索，通俗的理解就是，地毯式层层推进，从起始顶点开始，依次往外遍历。
     * 广度优先搜索需要借助队列来实现，遍历得到的路径就是，起始顶点到终止顶点的最短路径。
     * 广度优先搜索的时间复杂度都是 O(E)，空间复杂度是 O(V)
     *
     * 对于求取n度友好关系的问题(使用数组记录v与s的距离/层数):
     * 1.添加另一queue，add下一层至queue当前层遍历完再从下一层取值；类似二叉树层次遍历
     * 2.记录每一层入队和出队情况:一个变量记录每种边长的入队数量(每层的个数)，一个变量记录已出队的个数，一个变量记录当前的度数
     * 此处为2的实现
     * 3.当前节点与s的距离是prev距离+1，最为简洁
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
        //记录已出队数量(对头位置) 初始化为0层队头位置
        int front = 0;
        //记录'当前层'队中数量，此处为0层
        int behind = queue.size();
        //记录度数
        int degree = 0;
        //记录每个vertex的层数(度数)
        int[] vdegree = new int[v];

        while (queue.size()>0){
            Integer poll = queue.poll();
            //记录层数
            vdegree[poll] = degree;
            //队头后移(出队数+1)
            front++;
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
            //相等即为当前层已遍历完，此时对中为下一层数据；
            if(front == behind){
                front = 0;
                behind = queue.size();
                degree++;
            }
        }
    }

    /**
     * 深度优先搜索
     * 深度优先搜索用的是回溯思想，非常适合用递归实现。
     * 换种说法，深度优先搜索是借助栈来实现的。
     * 在执行效率方面，深度优先搜索的时间复杂度都是 O(E)，空间复杂度是 O(V)。
     *
     * 对于求取n度友好关系的问题(使用数组记录v与s的距离):
     * 递归式向下传当前度数，记录即可
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
