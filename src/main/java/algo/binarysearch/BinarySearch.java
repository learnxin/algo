package algo.binarysearch;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: 二分查找；
 * 适合于有序、静态；可随机访问的数据结构；数据量不宜太大/太小
 * @Date: 2021/4/22 下午11:47
 */
public class BinarySearch {


    /**
     * >> 优先级低于 + -
     */
    @Test
    public void binary() {
        System.out.print(2 + 2 >> 1);
    }

    @Test
    public void testBsearch(){
        int[] a = {1, 2, 3, 4, 5};
        int n =  bSearch(a,6);
//        int n = bSearchInternally(a, 0, a.length - 1, 2);
        System.out.print(n);
    }

    /**
     * 二分查找
     * @param a
     * @param value
     */
    public int bSearch(int[] a,int value){
        int n = a.length;
        int low = 0;
        int high = n-1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] < value){
                low = mid + 1;
            }else if(a[mid] > value){
                high = mid -1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找递归实现
     * @param a
     * @param low
     * @param high
     * @param value
     * @return
     */
    public int bSearchInternally(int[] a,int low,int high,int value){
        int mid = low + ((high - low) >> 1);
//        if(low > high){
//            return -1;
//        }
        if(a[mid] == value){
            return mid;
        }else if(a[mid] > value && a[mid] >= a[low]){
            return bSearchInternally(a,low,mid - 1 ,value);
        }else if(a[mid] < value && value <= a[high]) {
            return bSearchInternally(a,mid + 1 ,high,value);
        }
        return -1;
    }



    @Test
    public void testBSearch(){
        int[] a = {1, 3, 3, 4, 4, 5, 10};
//        int n = bSearchFindFirst(a, 0, a.length - 1, 4);
//        int n = bSearchFinalFirst(a, 0, a.length - 1, 9);
//        int n = bSearchOneFirst(a, 0, a.length - 1, 3);
        int n = bSearchLastFirst(a, 0, a.length - 1, 2);
        System.out.print(n);
    }

    /**
     * 查找第一个值等于给定值的元素
     */
    public int bSearchFindFirst(int[] a,int low,int high,int value){
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] > value){
                high = mid -1;
            }else if(a[mid] < value){
                low = mid + 1;
            }else {
                if(mid == 0 || a[mid - 1] != value) {
                    return mid;
                }
                high = mid -1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     */
    public int bSearchFinalFirst(int[] a,int low,int high,int value){
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] > value){
                high = mid -1;
            }else if(a[mid] < value){
                low = mid + 1;
            }else {
                if(mid == a.length -1 || a[mid + 1] != value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */
    public int bSearchOneFirst(int[] a,int low,int high,int value) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if(a[mid] >= value){
                if(mid == 0 || a[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            }else {
                low = mid +1;
            }
        }
        return -1;
    }

    /**
     * 找到最后一个小于等于给定值的元素
     */
    public int bSearchLastFirst(int[] a,int low,int high,int value) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if(a[mid] <= value){
                if(mid == a.length-1 || a[mid + 1] > value) {
                    return mid;
                }
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
        return -1;
    }

    /**
     * 如果有序数组是一个循环有序数组，比如 4，5，6，1，2，3。针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
     */
    @Test
    public void testLoopBSearch(){
        int[] a = {5,6,1,2,3,4};
        int n = bSearchLoopBSearch(a, 0, a.length - 1, 1);
        System.out.print(n);
    }

    /**
     有三种方法查找循环有序数组

     一、
     1. 找到分界下标，分成两个有序数组
     2. 判断目标值在哪个有序数据范围内，做二分查找

     二、
     1. 找到最大值的下标 x;
     2. 所有元素下标 +x 偏移，超过数组范围值的取模;
     3. 利用偏移后的下标做二分查找；
     4. 如果找到目标下标，再作 -x 偏移，就是目标值实际下标。

     两种情况最高时耗都在查找分界点上，所以时间复杂度是 O(N）。

     复杂度有点高，能否优化呢？

     三、
     我们发现循环数组存在一个性质：以数组中间点为分区，会将数组分成一个有序数组和一个循环有序数组。

     如果首元素小于 mid，说明前半部分是有序的，后半部分是循环有序数组；
     如果首元素大于 mid，说明后半部分是有序的，前半部分是循环有序的数组；
     如果目标元素在有序数组范围中，使用二分查找；
     如果目标元素在循环有序数组中，设定数组边界后，使用以上方法继续查找。

     时间复杂度为 O(logN)。

     此实现为三，每次淘汰一半元素
     */
    private int bSearchLoopBSearch(int[] a, int low, int high, int value) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            //判断在顺序递增部分还是在循环数组部分
            if(a[mid] == value){
                return mid;
            }
            if(a[low] <= a[mid]){
                //循环段在右侧
                if(a[low] <= value && value < a[mid]){
                    high = mid -1;
                }else{
                    low = mid + 1;
                }
            }else {
                //循环段在左侧，
                if(a[mid] < value && value <= a[high]){
                    low = mid + 1;
                }else {
                    high = mid -1;
                }

            }
        }
        return -1;
    }
}
