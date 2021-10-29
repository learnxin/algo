package leetcode.divide;

import org.junit.Test;

import java.util.Arrays;

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
 * 1.归并排序修改merge统计逆序数
 * 2.桶排序统计逆序数，范围大需要离散和二分
 */
public class InversionPair {
    public int num = 0;
    @Test
    public void reversePairs(){
        int[] nums = {7,5,6,4};
        int i = reversePairs(nums);
        System.out.println(i);
    }


    public int reversePairs(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        return num;
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
        int[] temp = new int[r-p+1];
        int i = p,j=q+1,k=0;
        while (i<=q&&j<=r){
            if(nums[i]<=nums[j]){
                temp[k++] = nums[i++];
            }else {
                num += q-i+1;
                temp[k++] = nums[j++];
            }
        }
        while (i<=q){
//            num += r-j+1; //此行无用 后一数组已空 此时 j= r+1
            temp[k++]= nums[i++];
        }
        while (j<=r){
            temp[k++]= nums[j++];
        }
        for (int l = 0; l < k; l++) {
            nums[p+l] = temp[l];
        }
    }
}
