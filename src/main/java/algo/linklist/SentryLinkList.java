package algo.linklist;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: wangran
 * @Description: 带头链表
 * @Date: 2021/1/14 下午10:49
 */
@Data
public class SentryLinkList {

    public SimpleNode head = new SimpleNode(null,null);

    public SimpleNode addHead(SimpleNode node){
        if(head.next == null){
            head.next = node;
            return head;
        }
        SimpleNode point = head.next;
        head.next = node;
        node.next = point;
        return head;
    }


    public void printAll(){
        if(Objects.isNull(head.next)){
            System.out.println("link list is null");
        }
        SimpleNode point = head.next;
        while (point != null){
            System.out.print(point.data+" ");
            point = point.next;
        }
    }

}
