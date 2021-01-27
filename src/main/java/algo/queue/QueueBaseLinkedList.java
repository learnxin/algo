package algo.queue;

import algo.linklist.SimpleNode;
import lombok.Data;

/**
 * @Author: wangran
 * @Description: 链式队列
 * @Date: 2021/1/28 上午12:31
 */
@Data
public class QueueBaseLinkedList {

    private int n;

    private int capacity;

    private SimpleNode<String> head;

    private SimpleNode<String> tail;

    public QueueBaseLinkedList(int capacity) {
        this.n = 0;
        this.capacity = capacity;
    }

    public boolean enqueue(String num){
        if(capacity == n){
            return false;
        }
        SimpleNode<String> node = new SimpleNode<>(num, null);
        n++;
        if(tail == null){
            tail = head = node;
            return  true;
        }
        tail.next = node;
        tail = node;
        return true;
    }

    public String dequeue(){
        if(head == null){
            return null;
        }
        String data = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        n--;
        return data;
    }

    public void printAll(){
        if(head == null) {
            return;
        }
        SimpleNode point = head;
        while (point != null){
            System.out.print(point.getData()+" ");
            point = point.next;
        }

    }
}
