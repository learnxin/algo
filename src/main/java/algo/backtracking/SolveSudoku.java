package algo.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: wangran
 * @Description: 编写一个程序，通过填充空格来解决数独问题。
 *
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date: 2021/11/3 下午10:37
 */
public class SolveSudoku {
    @Test
    public void solveSudokuTest(){
        char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

    private boolean valid = false;
    public void solveSudoku(char[][] board) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.'){
                    points.add(new Point(i,j));
                }
            }
        }
        setPoint(0,points,board);

    }

    private void setPoint(int n,List<Point> points,char[][] board){
        if (n == points.size()){
            valid =true;
            return;
        }
        Point next = points.get(n);
        int x = next.i;
        int y = next.j;
        for (int i = 1; i <= 9 && !valid; i++) {
            if(checkPoint(i,x,y,board)){
                board[x][y] = Character.forDigit(i,10);
                setPoint(n+1,points,board);
                //当回溯到当前递归层时
                int max = Math.max(n - 1, 0);
                board[points.get(max).i][points.get(max).j] = '.';
            }
        }


    }
    //char x = Character.forDigit(1,10);
    //int x1 = Character.getNumericValue(x);
    private boolean checkPoint(int i, int x, int y,char[][] board) {
        //校验行
        for (int j = 0; j < board[x].length; j++) {
            if(board[x][j] == Character.forDigit(i,10)){
                return  false;
            }
        }
        //校验列
        for (int j = 0; j < board.length; j++) {
            if(board[j][y] == Character.forDigit(i,10)){
                return  false;
            }
        }
        //粗实线分隔的 3x3 宫内
        int rx = ((x/3)+1)*3-1;
        int cx = ((y / 3) + 1) * 3 - 1;
        for (int j = rx; j > rx-3; j--) {
            for (int k = cx; k > cx - 3 ; k--) {
                if(board[j][k] == Character.forDigit(i,10)){
                    return  false;
                }
            }

        }
        return true;
    }

    class Point{
        public int i;
        public int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
