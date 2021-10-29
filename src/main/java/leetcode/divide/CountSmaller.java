package leetcode.divide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你`一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 示例 2：
 *
 * 输入：nums = [-1]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSmaller {
    public int[] index;
    public int[] result;
    //重复申请临时数组比较耗时
    public int[] temp;
    public int[] tempIndex;
    @Test
    public void reversePairs(){
        int[] nums = {5,2,6,1};
        List<Integer> integers = countSmaller(nums);
        System.out.println(integers);
    }


    public List<Integer> countSmaller(int[] nums) {
        index = new int[nums.length];
        /**
         * [0,1,2,3]
         * [3,1,0,2]
         */
        result = new int[nums.length];
        temp = new int[nums.length];
        tempIndex = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        mergeSort(nums,0,nums.length-1);
        List<Integer> list = new ArrayList<Integer>();
        for (int num : result) {
            list.add(num);
        }
//        int[] ints = Arrays.stream(result).distinct().sorted().toArray(); //数组去重加排序
        return list;
    }

    private void mergeSort(int[] nums, int p, int r) {
        if(p>=r){
          return;
        }
        //中间点
        int q = p+(r-p)/2;
        mergeSort(nums,p,q);
        mergeSort(nums,q+1,r);
        merge(nums,p,q,r);

    }
    // [4, 5, 6, 8][1, 2, 2, 3, 3,]
    private void merge(int[] nums, int p, int q, int r) {
        int i = p,j=q+1,k=p;
        while (i<=q&&j<=r){
            if(nums[i]<=nums[j]){
                //j-q-1 为此时从后array所取出的元素数
                result[index[i]] += (j-q-1);
                tempIndex[k] = index[i];
                temp[k++] = nums[i++];
            }else {
                //i及以后全都+1  计算result时使用递归，效率太低
//                for (int l = i; l <=q ; l++) {
//                    result[index[l]]++;
//                }
                tempIndex[k] = index[j];
                temp[k++] = nums[j++];
            }
        }
        while (i<=q){
//            result[index[i]] += r-j+1; //此行无用 后一数组已空 此时 j= r+1
            result[index[i]] += (j-q-1);
            tempIndex[k] = index[i];
            temp[k++]= nums[i++];
        }
        while (j<=r){
            tempIndex[k] = index[j];
            temp[k++]= nums[j++];
        }
        for (int l = p; l <= r; l++) {
            index[l] = tempIndex[l];
            nums[l] = temp[l];
        }
    }
}
