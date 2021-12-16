package algo.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: wangran
 * @Description: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * char[] a,b
 * 插入一个字符 插入a ->a[i]b[i+1] 插入b ->a[i+1]b[i]
 * 删除一个字符 删除a ->a[i+1]b[i] 删除b ->a[i]b[i+1]
 * 替换一个字符 替换a ->a[i+1]b[i+1] 替换b ->a[i+1]b[i+1]
 *输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/edit-distance 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date: 2021/12/15 上午6:56
 */
public class EditDistance {
    @Test
    public void test(){
        int i = minDistance("intention", "execution");
        System.out.println(i);
    }


    public int minDistance(String word1, String word2) {
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        //有二维数组min[i][j] [0][0]认为为空串 注意次数组填写的是[i][j]匹配完的结果而不是上一次的传值
        //运算是只涉及当前行和上一行，故最多初始化两行
        int row = Math.min(a.length + 1, 2);
        int[][] min = new int[row][b.length+1];
        //初始化数据 第0行对应空串直接全插入的场景
        for (int k = 0; k <= b.length; k++) {
            min[0][k] = k;
        }
        for (int k = 1; k < a.length+1; k++) {
            //第二行覆盖第一行
            if (k>1){
                System.arraycopy(min[1],0,min[0],0,b.length+1);
            }
            //需要初始化第一列数据
            min[1][0] = k;
            for (int l = 1; l < b.length+1; l++) {
                if(a[k-1] == b[l-1]){
                    min[1][l] = getMin(min[0][l]+1,min[1][l-1]+1,min[0][l-1]);
                }else {
                    min[1][l] = getMin(min[0][l]+1,min[1][l-1]+1,min[0][l-1]+1);
                }
            }
        }

        return min[row-1][b.length];
    }

    private int getMin(int a,int b, int c){
        int min = Math.min(a, b);
        return Math.min(min, c);
    }


    /**
     * 默认实现
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        if(n ==0 || m ==0){
            return Math.abs(n-m);
        }
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j-1]+1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i-1][0]+1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]);
                else minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]+1);
            }
        }
        return minDist[n-1][m-1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }
}
