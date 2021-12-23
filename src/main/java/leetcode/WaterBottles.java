package leetcode;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: 换酒问题
 * @Date: 2021/12/24 上午5:20
 *
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 *
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 *
 * 请你计算 最多 能喝到多少瓶酒。
 *
 * 示例 1：
 *
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-bottles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WaterBottles {
    @Test
    public void test(){
        int i = numWaterBottles(15, 4);
        System.out.println(i);
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        int drink = 0;
        int nullBottles = 0;

        while (numBottles>0){
            //drink
            drink += numBottles;
            nullBottles += numBottles;
            //exchange
            numBottles = nullBottles / numExchange;
            nullBottles = nullBottles % numExchange;
        }
        return drink;
    }
}
