package algo.stack;

import algo.linklist.SimpleNode;

/**
 * @Author: wangran
 * @Description: 链式栈
 * @Date: 2021/1/27 上午1:30
 */
public class LinkedStack {

    private final int DEFAULT_LENGTH = 10;

    private SimpleNode<Integer> head = new SimpleNode<Integer>();

    private int length;

    private int count;

    public LinkedStack(int length) {
        this.length = length;
        this.count = 0;
    }

    public boolean push(Integer ele){
        if(count == length){
            return false;
        }
        SimpleNode<Integer> node = new SimpleNode<>(ele,null);
        insertHead(node);
        count++;
        return true;
    }

    public Integer pop() {
        if(count == 0){
            return  null;
        }
        SimpleNode<Integer> simpleNode = deleteHead();
        count--;
        return simpleNode == null?null:simpleNode.getData();
    }

    public void insertHead(SimpleNode<Integer> ele){
        SimpleNode next = head.next;
        ele.next = next;
        head.next = ele;
    }

    public SimpleNode<Integer> deleteHead(){
        if(head.next == null){
            return null;
        }
        SimpleNode next = head.next;
        head.next = head.next.next;
        return next;
    }
}
