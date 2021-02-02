package algo.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: wangran
 * @Description: 简单排序
 * @Date: 2021/2/1 下午7:39
 */
public class TestSort {

    /**
     * Bubble Sort 冒泡排序
     */
    @Test
    public void bubbleSort(){
        int[] sort={1,2,3,5,4};
        bubbleSort(sort,sort.length);
        System.out.println(Arrays.toString(sort));
    }

    public void bubbleSort(int[] a, int n) {
        if(n <= 1){
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n-i-1; j++) {
                if(a[j] > a[j+1]){
                    flag = false;
                    int l =  a[j+1];
                    a[j+1] = a[j];
                    a[j] = l;
                }
            }
            //某次循环没有交换则认为数组有序
            if(flag) break;
        }

    }

    /**
     * insert sort
     * 插入排序
     */
    @Test
    public void insertSortTest(){
        int[] sort={3,2,5,4,3,2,1};
        insertSort(sort,sort.length);
        System.out.println(Arrays.toString(sort));
    }

//    public void insertSort1(int[] a, int n) {
//        for (int i = 1; i < n; i++) {
//            int num =  a[i];
//            int j = 0;
//            for (; j < i; j++) {
//                if(a[i]<a[j]){
//                    break;
//                }
//            }
//            for (int p = i; p > j ; p--) {
//                a[p] =a[p-1];
//            }
//            a[j] = num;
//        }
//    }

    public void insertSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int num =  a[i];
            int j = i-1;
            for (; j >=0 ; j--) {
                if(num<a[j]){
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            a[j+1] = num;
        }
    }




    /**
     * maybe s sort
     * 选择排序
     */

    @Test
    public void chooseSortTest(){
        int[] sort={3,2,5,4,3,2,1};
        chooseSort(sort,sort.length);
        System.out.println(Arrays.toString(sort));
    }

    public void chooseSort(int[] a, int n) {
        if(n <= 1){
            return;
        }

        for (int i = 0; i < n; i++) {
            int num = a[i];
            int p = i,j = i;
            for (; j < n; j++) {
                if(a[j] < a[p]){
                    p = j;
                }
            }
            if(p == i){
               continue;
            }
            a[i] = a[p];
            a[p] = num;
        }
    }

    @Test
    public void mergeSortTest(){
        int[] sort={8,3,2,5,4,3,2,1};
        mergeSort(sort,0,sort.length-1);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 对数组下标p到r的部分进行归并排序 merge sort
     * @param a
     * @param p
     * @param r
     */
    public void mergeSort(int[] a,int p,int r){
        if(p>=r){
            return;
        }
        int q = (p+r)/2;
        mergeSort(a,p,q);
        mergeSort(a,q+1,r);
        merge(a,p,q,r);
    }

    private void merge(int[] a, int p, int q, int r) {
        System.out.println(Arrays.toString(a));
        int[] c = new int[r-p+1];
        // c的指针
        int cp = 0;
        int i = p;
        int j = q+1;
        while (i<=q && j<= r){
            if(a[i]<a[j]){
                c[cp++]=a[i++];
            }else {
                c[cp++]=a[j++];
            }
        }
        if(j>r){
            for (;i<=q;i++){
                c[cp++]=a[i];
            }
        }else {
            for (;j<=r;j++){
                c[cp++]=a[j];
            }
        }
        //复制数组
        for (int k = 0; k < c.length; k++) {
            a[p+k]=c[k];
        }
    }

    private static void merge2(int[] arr, int left, int q, int right) {
        int[] leftArr = new int[q - left + 2];
        int[] rightArr = new int[right - q + 1];

        for (int i = 0; i <= q - left; i++) {
            leftArr[i] = arr[left + i];
        }
        // 第一个数组添加哨兵（最大值）
        leftArr[q - left + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < right - q; i++) {
            rightArr[i] = arr[q + 1 + i];
        }
        // 第二个数组添加哨兵（最大值）
        rightArr[right - q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = left;
        while (k <= right) {
            // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    }

    /**
     * 快速排序
     */
    @Test
    public void quickSortTest(){
        int[] sort={8,3,2,5,4,3,2,1,4};
        quickSort(sort,0,sort.length-1);
        System.out.println(Arrays.toString(sort));
    }

    public void quickSort(int[] a,int p,int r){
        if(p>=r){
            return;
        }
        int q= partition(a,p,r);
        quickSort(a,q+1,r);
        quickSort(a,p,q-1);

    }

    private int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i=p,j=p;
        for (;j<r;j++){
            if(a[j]<pivot){
                int b = a[j];
                a[j] = a[i];
                a[i++] = b;
            }
        }

        a[r] = a[i];
        a[i] = pivot;
        return i;
    }
}
