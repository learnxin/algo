package algo.backtracking;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子
 * 0 4 7 5 2 6 1 3
 * @Date: 2021/10/28 下午8:56
 */
public class Cal8queens {

    public int[] queens = new int[8];

    @Test
    public void testCal8queens(){
        calOneQueen(0);
    }

    public void calOneQueen(int row){
        if(row == 8){
            printQueens();
            return;
        }
        for (int column = 0; column < 8; column++) {

            if(checkRow(row,column)){
                if(row==3&&column==5){
                    System.out.print("");
                }
                queens[row] = column;
                calOneQueen(row+1);
            }
        }

    }

    private boolean checkRow(int row, int column) {
        //逐行验证
        for (int r = 0; r < row; r++) {
            int c = queens[r];
            //列的校验
            if(c == column){
                return false;
            }
            //对角验校验
            if((row - r) == Math.abs(column - c)){
                return false;
            }
        }
        return true;
    }

    private void printQueens() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(j==queens[i]){
                    System.out.print("Q ");
                }else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


}
