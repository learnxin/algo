package algo.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: wangran
 * @Description: 杨辉三角，最短路径
 *“杨辉三角”不知道你听说过吗？
 * 我们现在对它进行一些改造。
 * 每个位置的数字可以随意填写，经过某个数字只能到达下面一层相邻的两个数字。
 * 假设你站在第一层，往下移动，我们把移动到最底层所经过的所有数字之和，定义为路径的长度。
 * 请你编程求出从最高层移动到最底层的最短路径长度。
 *'.','.','5','.','.'
 *'.','.'7'.'8'.','.'
 *'.','2','3','4','.'
 *'.'4'.'9'.'6'.'1'.'
 *'2','7','9','4','5'
 *
 * 动态规划合并状态
 * @Date: 2021/11/17 上午12:52
 */
public class YanghuiTriangle {
    int w = 0;
    @Test
    public void yanghuiTriangleTest(){
        //left = a[i+1][j] right = a[i+1][j+1]
        int[][] board = new int[][]{
                {5},
                {7,8},
                {2,3,4},
                {4,9,6,1},
                {2,7,9,4,5},
                };
//        yanghuiTriangle(board,0,0,0);

        yanghuiTriangleDc(board);
    }

    /**
     * 回溯backtracking 的实现
     * @param board
     * @param i
     * @param j
     * @param tempW
     */
    private void yanghuiTriangle(int[][] board,int i,int j,int tempW){
        if(i>4){
            if(w == 0 ){
                w = tempW;
            }else if(tempW < w){
                w = tempW;
            }
            return;
        }
        //go left
        yanghuiTriangle(board,i+1,j,tempW + board[i][j]);
        //go right
        yanghuiTriangle(board,i+1,j+1,tempW + board[i][j]);


    }

    /**
     * dynamic programing动态规划的实现
     * 从上往下推
     * @param board
     */
    private void yanghuiTriangleDc(int[][] board){
        int[][] a = new int[board.length][board.length];
        a[0][0] = board[0][0];
        for (int i = 0; i < board.length-1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int left = board[i+1][j];
                int right = board[i+1][j+1];
                int now = a[i][j];
                //确定下层左节点的值
                a[i+1][j] = a[i+1][j] == 0?left +now:Math.min(left +now,a[i+1][j]);
                //确定下层右节点的值
                a[i+1][j+1] = a[i+1][j+1] == 0?right + now:Math.min(right + now,a[i+1][j+1]);
            }
        }
        System.out.println(Arrays.deepToString(a));
        //取最后一层的最小值即可
    }

    /**
     * dynamic programming
     * 从上往下推考虑边界值
     * @param matrix
     * @return
     */
    public int yanghuiTrianglePlanB(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        state[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) state[i][j] = state[i - 1][j] + matrix[i][j];
                else if (j == matrix[i].length - 1) state[i][j] = state[i - 1][j - 1] + matrix[i][j];
                else {
                    int top1 = state[i - 1][j - 1];
                    int top2 = state[i - 1][j];
                    state[i][j] = Math.min(top1, top2) + matrix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            int distance = state[matrix.length - 1][i];
            if (distance < minDis) minDis = distance;
        }
        return minDis;
    }

    /**
     * dynamic programming
     * 从下往上推，上层从下层左右节点取较小值；比较节省空间
     * 动态规划转移方程是：S[i][j] = min(S[i-1][j],S[i-1][j-1]) + a[i][j]。
     * 其中a表示到这个点的value值，S表示到a[i][j]这个点的最短路径值。
     * @param matrix
     * @return
     */
    public int yanghuiTriangle(int[][] matrix) {
        int length = matrix.length;
        // 用于存储每一层每列的状态
        int[] min = new int[length + 1];
        for (int i = length - 1; i >= 0; i--) {
            int[] rawNums = matrix[i];
            int rowLength = rawNums.length;
            for (int j = 0; j < rowLength; j++) {
                min[j] = Math.min(min[j], min[j + 1]) + rawNums[j];
            }
        }
        return min[0];
    }
}
