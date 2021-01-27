package algo.queue;

/**
 * @Author: wangran
 * @Description: 动态顺序队列
 * @Date: 2021/1/28 上午1:01
 */
public class DynamicArrayQueue {

    private String[] item;

    private int capacity;

    private int head;

    private int tail;

    public DynamicArrayQueue(int capacity) {
        this.capacity = capacity;
        item =new String[capacity];
        head = tail = 0;
    }

    public boolean enqueue(String str){
        if(tail-head == capacity){
            return false;
        }
        //判断数据迁移条件
        if(tail == capacity && head!=0){
            int i = 0;
            for (; i < tail-head; i++) {
                item[i] = item[head +i];
            }
            head = 0;
            tail = i;
        }

        item[tail] = str;
        tail++;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }
        return item[head++];
    }
}
