package algo.sort;

/**
 * @Author: wangran
 * @Description: 计数排序
 * @Date: 2021/5/20 上午11:03
 */
public class CountingSort {


    public static void countingSort(int[] arr){
        int n = arr.length;
        if(n < 2){
            return;
        }
        int max = arr[0];
        //明确数据范围
        for (int i = 1; i < n; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }
        int[] count = new int[max+1];
        //计数
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }
        //累加计算下标识
        for (int i = 1; i < n; i++) {
            count[i] = count[i] + count[i-1];
        }
        //临时存放数据
        int[] tem = new int[n];
        for (int i = n-1; i >=0 ; i--) {
            int index = --count[arr[i]];
            tem[index] = arr[i];
        }
        for (int i = 0; i < n; i++) {
            arr[i] = tem[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {12,5,11,11,15,16,19,10,7,8,10,17,10,5,4,3,2,2,2,2};
        countingSort(a);
    }
}
