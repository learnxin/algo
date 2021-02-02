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
}
