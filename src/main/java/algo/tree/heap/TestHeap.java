package algo.tree.heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
}
