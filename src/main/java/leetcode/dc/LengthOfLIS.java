package leetcode.dc;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。longestIncreaseSubArrayDP
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
 *
 *示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]1,3,4,5,2
 * 输出：4
 *
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 *
 *
 * @Date: 2021/12/2 上午12:50
 */
public class LengthOfLIS {

    @Test
    public void test() {
        int[] a= new int[]{0,1,0,3,2,3};
        int i = lengthOfLIS(a);
        System.out.println(i);
    }

    public int lengthOfLIS(int[] nums) {

        //此数组为i为子串起点，记录长度为下标时的最小后缀
        int[] dep = new int[nums.length+1];
        //当前最大子串长度
        int nowLength =1;
        //子串尾部位置
        dep[nowLength] = nums[0];
        for (int j = 1; j < nums.length; j++) {
            if(nums[j]>dep[nowLength]){
                dep[++nowLength] = nums[j];
            }else if (nums[j]<dep[nowLength]){
                //查询数在dep的位置并替换
                int i1 = bSearchOneFirst(dep, 1, nowLength, nums[j]);
                if(i1==-1){
                    dep[1] = nums[j];
                }else {
                    dep[i1] = nums[j];
                }

            }
        }



        return nowLength;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */
    private int bSearchOneFirst(int[] a,int low,int high,int value) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if(a[mid] >= value){
                if(mid == low || a[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            }else {
                low = mid +1;
            }
        }
        return -1;
    }
}
