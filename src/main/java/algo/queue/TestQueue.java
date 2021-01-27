package algo.queue;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @Author: wangran
 * @Description: 测试queue
 * @Date: 2021/1/28 上午12:42
 */
public class TestQueue {
    @Test
    public void testLinkedQueue(){
        QueueBaseLinkedList queue = new QueueBaseLinkedList(5);
        Stream.iterate(1,x->x+1).limit(4).forEach(
                e-> queue.enqueue(String.valueOf(e))

        );
        queue.printAll();
        boolean enqueue = queue.enqueue("1");
        boolean enqueue1 = queue.enqueue("2");
        while (queue.getN() != 0){
            System.out.println(queue.dequeue());
        }
    }

    @Test
    public void testDynamicArrayQueue(){
        DynamicArrayQueue queue = new DynamicArrayQueue(5);
        Stream.iterate(1,x->x+1).limit(5).forEach(
                e-> queue.enqueue(String.valueOf(e))

        );
        String dequeue = queue.dequeue();
        queue.enqueue("1");

    }

    @Test
    public void testCircularArrayQueue(){
        CircularArrayQueue queue = new CircularArrayQueue(5);
        Stream.iterate(1,x->x+1).limit(5).forEach(
                e-> queue.enqueue(String.valueOf(e))

        );

        boolean enqueue = queue.enqueue("6");
        String dequeue = queue.dequeue();
        boolean enqueue1 = queue.enqueue("6");

    }
}
