package leetcode.divide;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 2.桶排序统计逆序数，范围大需要离散和二分； 为了快速求取前缀和此处引入树状数组
 */
public class ReversePairs {

    @Test
    public void reversePairs(){
        int[] nums = {7,5,6,4};
        int i = reversePairs(nums);
        System.out.println(i);
    }


    public int reversePairs(int[] nums) {
        //使用桶排序的方法；为防止范围过大，此处排序去重离散化，使用二分查找找到每个元素的相对位置
//        int[] temp = Arrays.stream(nums).distinct().sorted().toArray();
        int[] temp = sortAndDistinct(nums);
        int length = nums.length;
        Bit bit = new Bit(length);
        int result = 0;
        //从最后一位开始桶排序，前缀和(桶中小于x的数)即为x的逆序数；求和则是总的逆序数
        for (int i = length-1; i >=0 ; i--) {
            //树状数组从a1开始累加
            int index = Arrays.binarySearch(temp, nums[i])+1;
            //前缀和 5则取4
            result += bit.query(index-1);
            bit.update(index);
        }
        return result;
    }

    private int[] sortAndDistinct(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length ; i++) {
            set.add(nums[i]);
        }
        int[] temp = new int[set.size()];
        int index = 0;
        for (int i :set
             ) {
            temp[index++] =i;
        }
        Arrays.sort(temp);
        return temp;
    }

    static class Bit{
        public int n;
        public int[] tree;

        public Bit(int n) {
            this.n = n;
            //不存在a[0];
            this.tree = new int[n+1];
        }

        public static int lowBit(int x){
            return x&(-x);
        }

        public void update(int x){
            for (int i = x; i <= n; i+=lowBit(i)) {
                //此处默认+1;若为k则+1
                tree[i]++;
            }
        }

        public int query(int x){
            int result = 0;
            for (int i = x; i > 0 ; i-= lowBit(i)) {
                result+=tree[i];
            }
            return result;
        }
    }
}
