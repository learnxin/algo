package algo.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 位运算优化数独解法，
 * 在方法一中，我们使用了长度为 9 的数组表示每个数字是否出现过。我们同样也可以借助位运算，仅使用一个整数表示每个数字是否出现过。
 *
 *
 * 我们可以顺着方法二的思路继续优化下去：
 *
 * 如果一个空白格只有唯一的数可以填入，也就是其对应的 b（此处b为mask） 值和 b−1 进行按位与运算后得到 0（即 b 中只有一个二进制位为 1）。此时，我们就可以确定这个空白格填入的数，而不用等到递归时再去处理它。
 * 这样一来，我们可以不断地对整个数独进行遍历，将可以唯一确定的空白格全部填入对应的数。随后我们再使用与方法二相同的方法对剩余无法唯一确定的空白格进行递归 + 回溯。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SolveSudokuSolution {
    private int[] line = new int[9];
    private int[] column = new int[9];
    private int[][] block = new int[3][3];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    @Test
    public void solveSudokuTest(){
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
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

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
            }
        }
        while (true) {
            boolean modified = false;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
                        //对应的 b（此处b为mask） 值和 b−1 进行按位与运算后得到 0（即 b 中只有一个二进制位为 1）此时就可以确认填入的值
                        if ((mask & (mask - 1)) == 0) {
                            int digit = Integer.bitCount(mask - 1);
                            flip(i, j, digit);
                            board[i][j] = (char) (digit + '0' + 1);
                            modified = true;
                        }
                    }
                }
            }
            if (!modified) {
                break;
            }
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }

        dfs(board, 0);

    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        //~(b)&(111111111) 取反高位也会为1； &消除了高位的1
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        //mask &= (mask - 1)我们可以用 b 和最低位的1 进行按位异或运算，就可以将其从 b 中去除，这样就可以枚举下一个 1。
        // 同样地，我们也可以用 b 和 b-1 进行按位与运算达到相同的效果

        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            int digitMask = mask & (-mask);
            //Integer.bitCount(); int,long类型的数值在二进制下“1”的数量。  4=(100) 4-1=(011); 记得得到4在第几位
            int digit = Integer.bitCount(digitMask - 1);
            flip(i, j, digit);
            board[i][j] = (char) (digit + '0' + 1);
            dfs(board, pos + 1);
            flip(i, j, digit);
        }
    }

    public void flip(int i, int j, int digit) {
        //异或^相同为0 不同为1
        //我们可以使用按位异或运算 ∧，将第 i 位从 0 变为 1，或从 1 变为 0。具体地，与数 1 << i 进行按位异或运算即可，其中 << 表示左移运算；
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }
}
