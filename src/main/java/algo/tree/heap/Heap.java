package algo.tree.heap;

/**
 * @Author: wangran
 * @Description: 堆
 * @Date: 2021/7/6 下午7:24
 */
public class Heap {

    private int[] a;

    /**
     * 数组容量
     */
    private int n;

    /**
     * 个数
     */
    private int count;

    public Heap(int capacity) {
        //下标为0的位置空置；初始化时+1
        n = capacity;
        a = new int[capacity+1];
        count = 0;
    }

    public void insert(int data){
        if(count >= n ){
            return;
        }
        a[++count] = data;
        //自下往上堆化 heapify i 为下标
        int i = count;
        //父节点
        while (i > 1){
            if(a[i/2] < a[i]){
                swap(a,i,i/2);
                i = i/2;
            }
        }
    }

    /**
     * 删除堆顶元素 此时是大顶堆
     */
    public void removeTop(){
        //赋值，维护堆是完全二叉树的结构
        a[1] = a[count];
        count--;
        //自上而下堆化
        heapify(a,1,count);

    }

    public static void buildHeap(int[] a,int n){
        /*我们对下标从 2n​ 开始到 1 的数据进行堆化，
         下标是 2n​+1 到 n 的节点是叶子节点，我们不需要堆化。
         实际上，对于完全二叉树来说，下标从 2n​+1 到 n 的节点都是叶子节点*/
        for (int i = n/2; i > 0 ; i--) {
            heapify(a,i,n);
        }


    }

    public static void heapify(int[] a, int i, int count) {
        while (true){
            int point = i;
            if(2*i<=count && a[i]<a[2*i]){
                point = 2*i;
            }
            if(2*i+1<=count && a[point]<a[2*i+1]){
                point = 2*i+1;
            }
            if(point == i){
                break;
            }
            swap(a,i,point);
            i = point;
        }

    }

    /**
     *交换位置
     */
    public static void swap(int[] a,int b,int c){
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
}
