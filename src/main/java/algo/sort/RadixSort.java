package algo.sort;

/**
 * @Author: wangran
 * @Description: 基数排序
 * @Date: 2021/5/20 下午3:26
 */
public class RadixSort {

    public static void radixSort(int[] arr){
        if(arr.length<2){
            return;
        }
        int max = arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }
        for (int digits = 1; max/digits > 0 ; digits=digits*10) {
            countingSort(arr,digits);
        }
    }

    /**
     * 按某一位进行排序
     * @param arr
     * @param digits
     */
    private static void countingSort(int[] arr, int digits) {
        int[] counting = new int[10];
        for (int i = 0; i < arr.length; i++) {
            //取某一位
            int dig = (arr[i] / digits) % 10;
            counting[dig]++;
        }
        //累加
        for (int i = 1; i < counting.length; i++) {
            counting[i] = counting[i] + counting[i-1];
        }
        //临时存放排序后数据
        int[] temp = new int[arr.length];
        for (int i = arr.length-1; i >=0 ; i--) {
            //取某一位
            int dig = (arr[i] / digits) % 10;
            temp[counting[dig]-- - 1] = arr[i];
        }
        //拷贝数据
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1399799,185726,138868,257,725};
        radixSort(arr);
    }
}
