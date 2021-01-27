package algo.queue;

/**
 * @Author: wangran
 * @Description: 动态顺序队列
 * @Date: 2021/1/28 上午1:01
 */
public class CircularArrayQueue {

    private String[] item;

    private int capacity;

    private int head;

    private int tail;

    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        item =new String[capacity];
        head = tail = 0;
    }

    public boolean enqueue(String str){
        if((tail+1)%capacity == head ){
            return false;
        }
        item[tail] = str;
        tail = (tail+1)%capacity;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }
        String s = item[head];
        head = (head+1)%capacity;
        return s;
    }
}
