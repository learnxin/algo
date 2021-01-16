package algo.linklist;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: wangran
 * @Description: 测试
 * @Date: 2021/1/14 下午10:49
 */
@Data
public class SimpleLinkList {

    public SimpleNode head = null;

    public SimpleNode addHead(SimpleNode node){
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
        SimpleNode point = head;
        while (point != null){
            System.out.print(point.data+" ");
            point = point.next;
        }
    }

    //向尾部新增下一个元素
    public SimpleNode add(SimpleNode node){
        if(Objects.isNull(head)){
            head = node;
            return head;
        }
        SimpleNode pos = head;
        while (Objects.nonNull(pos.next)){
            pos = pos.getNext();
        }
        pos.setNext(node);
        return head;
    }

    //向尾部新增下一个元素
    public SimpleNode add(Object obj){
        return add(new SimpleNode(obj,null));
    }

    //修改指定位置元素
    public void updateBySign(Object ele,int sign){
        SimpleNode pos = head;
        for (int i = 0; i <sign ; i++) {
            pos = pos.getNext();
            if (Objects.isNull(pos)) break;
        }
        pos.setData(ele);
    }
    //在指定位置插入元素，实际上需要遍历上一个元素
    public void addBySign(Object ele,int sign){
        SimpleNode pos = head;
        SimpleNode pre = null;
        for (int i = 0; i <sign ; i++) {
            pre = pos;
            pos = pos.getNext();
            if (Objects.isNull(pos)) return;
        }
        pos.setData(ele);
        SimpleNode simpleNode = new SimpleNode(ele, pos);
        pre.setNext(simpleNode);
    }

    //删除指定元素
    public void deleteObject(Object ele){
        SimpleNode pos = head;
        SimpleNode pre = null;
        do{
            pre = pos;
            pos = pos.getNext();
        }while (Objects.equals(ele,pos.getData()));

        pre.setData(pos.getNext());

    }
    //删除特定位置的元素，实际需要遍历得到上一个元素
    public void deleteBySign(Object ele,int sign){
        SimpleNode pos = head;
        SimpleNode pre = null;
        for (int i = 0; i <sign ; i++) {
            pre = pos;
            pos = pos.getNext();
            if (Objects.isNull(pos)) return;
        }
        pre.setData(pos.getNext());
    }


}
