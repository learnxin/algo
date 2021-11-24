package algo.dynamicprogramming;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: 硬币找零
 *
 * 硬币找零问题，我们在贪心算法那一节中讲过一次。
 * 我们今天来看一个新的硬币找零问题。
 * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。
 * 如果我们要支付 w 元，求最少需要多少个硬币。
 * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
 * @Date: 2021/11/25 上午3:36
 */
public class CoinChange {

    @Test
    public void  coinChangeTest(){
        System.out.println(min(9));
    }

    /**
     * 状态方程
     * @param n
     * @return
     */
    public int min(int n){
        if(n<0){
            return Integer.MAX_VALUE;
        }
        if(n==1||n==3|n==5){
            return 1;
        }
        return Math.min(min(n-5),Math.min(min(n-1),min(n-3)))+1;
    }

    /**
     * 状态转移表，i为步骤，j为钱；每一步都从1、3、5选一个
     * @param money
     * @return
     */
    public int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) return 1;
        boolean [][] state = new boolean[money][money + 1];
        if (money >= 1) state[0][1] = true;
        if (money >= 3) state[0][3] = true;
        if (money >= 5) state[0][5] = true;
        for (int i = 1; i < money; i++) {
            for (int j = 1; j <= money; j++) {
                if (state[i - 1][j]) {
                    if (j + 1 <= money) state[i][j + 1] = true;
                    if (j + 3 <= money) state[i][j + 3] = true;
                    if (j + 5 <= money) state[i][j + 5] = true;
                    if (state[i][money]) return i + 1;
                }
            }
        }
        return money;
    }
}
