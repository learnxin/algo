package algo.linklist;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: wangran
 * @Description: 测试
 * @Date: 2021/1/14 下午10:49
 */
@Data
public class LruLinkList {

    private SimpleNode<Integer> head = null;

    private int n;

    public void init(int n ){
        this.n = n;
    }

    public void push(Integer data){
        if(head != null){
            SimpleNode<Integer> pre = head;
            int i =1;
            //处理数据已在头结点的场景
            if(data.equals(pre.getData())){
                return;
            }
            //查询前节点
            while (pre.next!=null){
                if(data.equals(pre.next.getData())){
                    break;
                }
                pre = pre.next;
                i++;
            }
            if(pre.next == null){
                //链表没有此数据
                //如果此时缓存未满，则将此结点直接插入到链表的头部；如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
                if(n == i){
                    //缓存满,删除最后一个节点
                    SimpleNode<Integer> point = head;
                    while (point.next != pre){
                        point = point.next;
                    }
                    point.next = null;
                }
            }else {
                pre.next = pre.next.next;
            }
        }
        addHead(new SimpleNode<>(data,null));

    }

    public SimpleNode<Integer> addHead(SimpleNode<Integer> node){
        if(Objects.isNull(head)){
            head = node;
            return head;
        }
        node.next = head;
        head = node;
        return head;
    }

    public void printAll(){
        if(Objects.isNull(head)){
            System.out.println("link list is null");
        }
        SimpleNode<Integer> point = head;
        while (point != null){
            System.out.print(point.data+" ");
            point = point.next;
        }
    }

    //向尾部新增下一个元素
    public SimpleNode<Integer> add(SimpleNode<Integer> node){
        if(Objects.isNull(head)){
            head = node;
            return head;
        }
        SimpleNode<Integer> pos = head;
        while (Objects.nonNull(pos.next)){
            pos = pos.getNext();
        }
        pos.setNext(node);
        return head;
    }

    //向尾部新增下一个元素
    public SimpleNode<Integer> add(Integer obj){
        return add(new SimpleNode<Integer>(obj,null));
    }

}
