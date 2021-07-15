package algo.tree.heap;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: wangran
 * @Description: heap test
 * @Date: 2021/7/6 下午7:53
 */
public class TestHeap {


    public Heap getHeap(){
        Heap heap = new Heap(10);
        Stream.iterate(0,e->e+1).limit(10).forEach(heap::insert);
        return heap;
    }

    @Test
    public void testRemoveTop(){
        Heap heap = getHeap();
        heap.removeTop();
        heap.removeTop();
        heap.removeTop();
    }
    @Test
    public void testHeapSort(){
//        int[] ints = new Random().ints(10, 1, 100).toArray();
        int[] array = IntStream.iterate(0, e -> e + 1).limit(11).toArray();
        //build heap
        Heap.buildHeap(array,array.length-1);
        for (int i = array.length-1; i >0 ; ) {
            Heap.swap(array,1,i);
            i--;
            Heap.heapify(array,1,i);
        }
        System.out.println(Arrays.toString(array));
//        Heap.buildHeap(array,array.length-1);
    }

    @Test
    public void testTopK(){
        int[] ints = new Random().ints(100, 1, 100).toArray();
        topK(ints,10);

        nPersent(ints,49);

    }

    /**
     * 此处使用java优先队列实现
     * 假设取100随机数中的top10
     * 维护一个size=10的小顶堆，遍历array:大于堆顶则删除堆顶元素，入堆
     */
    public void topK(int[] array,int k){
        List<Integer> collect = Arrays.stream(array).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        collect.forEach(
                e->{
                    if(heap.size()<k){
                        heap.add(e);
                    }else {
                        //获取堆顶元素
                        Integer peek = heap.peek();
                        if(e>peek){
                            //移除堆顶元素
                            heap.poll();
                            heap.add(e);
                        }

                    }
                }
        );
        System.out.println(heap.toString());
    }

    /**
     * 我们维护两个堆，一个大顶堆，一个小顶堆。假设当前总数据的个数是 n，大顶堆中保存 n*99% 个数据，小顶堆中保存 n*1% 个数据。
     * 大顶堆堆顶的数据就是我们要找的 99% 响应时间。
     * 每次插入一个数据的时候，我们要判断这个数据跟大顶堆和小顶堆堆顶数据的大小关系，然后决定插入到哪个堆中。
     * 如果这个新插入的数据比大顶堆的堆顶数据小，那就插入大顶堆；
     * 如果这个新插入的数据比小顶堆的堆顶数据大，那就插入小顶堆。
     * 但是，为了保持大顶堆中的数据占 99%，小顶堆中的数据占 1%，
     * 在每次新插入数据之后，我们都要重新计算，这个时候大顶堆和小顶堆中的数据个数，
     * 是否还符合 99:1 这个比例。
     * 如果不符合，我们就将一个堆中的数据移动到另一个堆，直到满足这个比例。
     * @param array
     * @param k
     */
    public void nPersent(int[] array,int k){
        List<Integer> collect = Arrays.stream(array).boxed().collect(Collectors.toList());
        //大顶堆
        PriorityQueue<Integer> min = new PriorityQueue<>(k, Comparator.comparingInt(Integer::intValue).reversed());
        //小顶堆
        int i = array.length - k;
        PriorityQueue<Integer> max = new PriorityQueue<>(i);
        collect.forEach(
                e->{
                    if(min.size()<k){
                        min.add(e);
                    }else {
                        Integer peek = min.peek();
                        if(e>peek){
                            max.add(e);
                        }else {
                            min.add(e);
                        }
                    }
                }
        );
        //调整堆的大小
        do {
            while (min.size() > k) {
                max.add(min.poll());
            }
            while (max.size() > i) {
                min.add(max.poll());
            }
        } while (min.size() != k && max.size() != i);
        System.out.println(min.peek());
    }
}
