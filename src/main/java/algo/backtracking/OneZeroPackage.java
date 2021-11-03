package algo.backtracking;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: wangran
 * @Description: 0-1背包问题
 * 实质是穷举趋近于限重 w的所有组合；从这些组合结果中选取重量最大的一组
 * 变种:现在我们对今天讲到的 0-1 背包问题稍加改造，如果每个物品不仅重量不同，价值也不同。如何在不超过背包重量的情况下，让背包中的总价值最大
 * 背包中的总价值最大的组合一定在'趋近于限重 w的所有组合'中(多一件货物价值总是更大)；那么从这些组合中选取总价值最大的即可
 * @Date: 2021/10/29 下午6:00
 */
public class OneZeroPackage {
    /**
     * 最终组合
     */
    public boolean[] result;

    /**
     * 最终组合重量
     */
    public int resultW;

    /**
     * 临时组合
     */
    public boolean[] resultTemp;


    /**
     * goods为货物重量，w为背包限额
     */
    int[] goods;

    int w;

    @Test
    public void testOneZeroPackage(){
        goods = new int[]{4,2,5,2,1,2,1,9};
        w = 20;
        result = new boolean[goods.length];
        resultTemp = new boolean[goods.length];
        resultW = 0;
        Arrays.sort(goods);
        oneZeroPackage(0,0);
        for (int i = 0; i < result.length; i++) {
            if(result[i]) {
                System.out.println(goods[i] +" ");
            }
        }
    }

    public void oneZeroPackage(int n,int resultwtemp){
        if(n == 8 || resultwtemp == w){
            if(resultwtemp>resultW){
                resultW = resultwtemp;
                System.arraycopy(resultTemp,0,result,0,result.length);
            }
            return;
        }
        //每个货物分别考察 放置/不放置两种情况
        if(w >= resultwtemp + goods[n]){
            resultTemp[n] = true;
            oneZeroPackage(n+1,resultwtemp+goods[n]);
        }
        resultTemp[n] = false;
        oneZeroPackage(n+1,resultwtemp);

    }

    private boolean checkWeight(int n,int resultwtemp) {
        if(w >= resultwtemp + goods[n]){
            return true;
        }else {
            if(resultwtemp>resultW){
                resultW = resultwtemp;
                System.arraycopy(resultTemp,0,result,0,result.length);
            }
            return false;
        }

    }



    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值
    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
// w背包重量；items表示每个物品的重量；n表示物品个数
// 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
// f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i+1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i+1,cw + items[i], items, n, w);
        }
    }

    @Test
    public void testOneZeroPackage1(){
        goods = new int[]{4,1,2,5,2,1,2,12,9};
        w = 20;
        result = new boolean[goods.length];
        resultTemp = new boolean[goods.length];
        resultW = 0;
        Arrays.sort(goods);
        f(0, 0, goods, 9, 20);
        System.out.println(maxW);

    }
}
