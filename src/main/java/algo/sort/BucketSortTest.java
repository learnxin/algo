package algo.sort;

/**
 * @Author: wangran
 * @Description:
 * @Date: 2021/5/20 上午12:41
 */
public class BucketSortTest {

    /**
     * 桶排序
     *
     * @param arr 数组
     * @param bucketCount 桶数量
     */
    public static void bucketSort(int[] arr, int bucketCount) {
        if (arr.length < 2) {
            return;
        }

        // 数组最小值
        int minValue = arr[0];
        // 数组最大值
        int maxValue = arr[1];
        //记录每个桶的数据量
        int[] indexCount = new int[bucketCount];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        //每个桶的范围数
        int scope = (maxValue - minValue) / 4 +1;
        for (int i = 0; i < arr.length; i++) {
            //每个桶的实际数据量
            indexCount[(arr[i] - minValue)/scope]++;
        }
        int[][] buckets = new int[bucketCount][];
        //为第二维数据赋值
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new int[indexCount[i]];
        }
        //放置数据
        int[] indexCount1 = new int[bucketCount];
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - minValue) / scope;
            buckets[index][indexCount1[index]++] = arr[i];
        }
        //排序及取值
        int k = 0;
        for (int i = 0; i < indexCount.length; i++) {
            if(indexCount[i] == 0){
                continue;
            }
            quickSortC(buckets[i],0,buckets[i].length-1);
            for (int j = 0; j < indexCount[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }



    /**
     * 快速排序递归函数
     *
     * @param arr
     * @param p
     * @param r
     */
    private static void quickSortC(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }

    /**
     * 分区函数
     *
     * @param arr
     * @param p
     * @param r
     * @return 分区点位置
     */
    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);
        return i;
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {100,22,5,11,41,45,26,29,10,7,8,30,27,42,43,40,5,4,3,2,2,2,2};
        bucketSort(a,5);
    }
}
