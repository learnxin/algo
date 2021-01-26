package algo.linklist;

import algo.stack.LinkedStack;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @Author: wangran
 * @Description: 测试
 * @Date: 2021/1/16 下午7:53
 */
public class TestLinkList {

    public static SimpleLinkList initLinkList(){
        SimpleLinkList linkList = new SimpleLinkList();
        Stream.iterate(1, x->x+1).limit(5).forEach(
                e->{
                    SimpleNode simpleNode = new SimpleNode(e, null);
                    linkList.addHead(simpleNode);
                }
        );
        return linkList;
    }

    @Test
    public void testHeadPrint() {

        initLinkList().printAll();
    }

    /**
     * 单链表反转 o(n)
     */
    @Test
    public void  turnLinkList(){
        SimpleLinkList linkList = initLinkList();
        linkList.printAll();
        SimpleNode head = null;
        SimpleNode point = linkList.getHead();
        while (null != point){
            SimpleNode next = point.getNext();
            point.setNext(head);
            head = point;
            point = next;
        }
        linkList.setHead(head);
        linkList.printAll();

    }

    /**
     * 判断是否有环 o(n)
     */
    @Test
    public void testRing(){
        //造环
        SimpleLinkList linkList = initLinkList();
        SimpleNode point = linkList.getHead();
        while (point.next != null){
            point = point.next;
        }
        point.setNext(linkList.getHead());
        //设置快慢指针，快指针走两步，慢指针走完前快慢指针相遇则有环
        SimpleNode slowP = linkList.getHead();
        SimpleNode fastP = linkList.getHead();
        while (slowP != null && fastP != null && fastP.next != null){
            slowP = slowP.next;
            fastP = fastP.next.next;
            if(slowP == fastP){
                System.out.print("link list is ringing");
                break;
            }
        }
    }

    /**
     * 两个有序的链表合并 o(n)
     */
    @Test
    public void mergeSortLinkList(){
        //造链表
        SimpleLinkList oneLinkList = new SimpleLinkList();
        SimpleLinkList twoLinkList = new SimpleLinkList();
        Stream.iterate(0, x->x+2).limit(5).forEach(
                e->{
                    SimpleNode simpleNode = new SimpleNode(e, null);
                    SimpleNode simpleNodeT = new SimpleNode(e+1, null);
                    twoLinkList.add(simpleNode);
                    oneLinkList.add(simpleNodeT);
                }
        );
//        twoLinkList.add(new SimpleNode(Integer.MAX_VALUE, null));
//        oneLinkList.add(new SimpleNode(Integer.MAX_VALUE, null));
        //merge
        SimpleLinkList mergeLink = new SimpleLinkList();
        SimpleNode onePoint = oneLinkList.getHead();
        SimpleNode twoPint = twoLinkList.getHead();
        while (onePoint != null && twoPint != null){
            int one = (int)onePoint.getData();
            int two = (int)twoPint.getData();
            if(one <= two){
                mergeLink.add(one);
                onePoint = onePoint.next;
            }else {
                mergeLink.add(two);
                twoPint = twoPint.next;
            }
        }
        if(onePoint == null){
            mergeLink.add(twoPint);
        }else{
            mergeLink.add(onePoint);
        }
        mergeLink.printAll();
    }

    /**
     * 删除链表倒数第 n 个结点 o(n)
     */
    @Test
    public void deleteNoPoint(){
        SimpleLinkList linkList = initLinkList();
        linkList.printAll();
        deleteNoPoint(linkList,6);
        linkList.printAll();
    }

    private void deleteNoPoint(SimpleLinkList linkList,int n){
        System.out.println();
        int i = 0 ;
        SimpleNode point = linkList.getHead();
        while (point != null){
            point = point.next;
            i++;
        }
        if(i < n){
            System.out.println("out of index");
            return;
        }
        if(n == i){
          linkList.setHead(linkList.head.next);
          return;
        }
        SimpleNode pre = point = linkList.getHead();
        //获取领先head的快节点
        for (int j = 0; j < n; j++) {
            pre = pre.next;
        }
        while (pre.next != null){
            point = point.next;
            pre = pre.next;
        }
        point.setNext(point.next.next);
    }

    /**
     * 求中间结点 o(n)
     * @param list
     * @return
     */
    public static SimpleNode findMiddleNode(SimpleNode list) {
        if (list == null) return null;

        SimpleNode fast = list;
        SimpleNode slow = list;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    @Test
    public void initSentryLinkList(){
        SentryLinkList linkList = new SentryLinkList();
        Stream.iterate(0,x->x+1).limit(5).forEach(
                e->{
                    SimpleNode simpleNode = new SimpleNode(e, null);
                    linkList.addHead(simpleNode);
                }
        );
        linkList.printAll();
    }

    /**
     * 回文链表判断
     */
    @Test
    public void palindromeTest(){
        SimpleLinkList linkList = initLinkList();

        Stream.iterate(5,x->x-1).limit(4).forEach(
                e->{
                    SimpleNode<Integer> simpleNode = new SimpleNode(Integer.valueOf(e), null);
                    linkList.addHead(simpleNode);
                }
        );
        linkList.printAll();
        SimpleNode<Integer> point = linkList.getHead();
        int i =0;
        while (point != null){
            point = point.next;
            i++;
        }
        //找到中间节点(一个或两个)
        int m = i%2;
        SimpleNode<Integer> left = linkList.getHead();
        SimpleNode<Integer> right;
        int d ;
        if(m == 1){
            d = (i+1)/2 -2 ;
        }else {
            d = i/2-1;
        }
        for (int j = 0; j < d; j++) {
            left = left.next;
        }
        if(m == 1){
            right = left.next.next;
        }else {
            right = left.next;
        }
        //链表反转至left;
        SimpleNode<Integer> head = null;
        SimpleNode<Integer> current = linkList.getHead();
        SimpleNode next1 = left.next;
        while (current != next1){
            SimpleNode<Integer> next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        while (left != null && right != null) {
            Integer dataL = left.getData();
            Integer dataR= right.getData();
            if(!dataL.equals(dataR)){
                System.out.println("false");
                return;
            }
            left = left.next;
            right = right.next;
        }
        System.out.println("true");
    }

    /**
     * Lru缓存测试
     */
    @Test
    public void LruCacheLinkedListTest(){
        LruLinkList lruLinkList = new LruLinkList();
        lruLinkList.init(5);
        Stream.iterate(0,x->x+1).limit(4).forEach(
                lruLinkList::push
        );
        lruLinkList.printAll();
        lruLinkList.push(0);
        lruLinkList.push(4);

        lruLinkList.push(5);

    }

    /**
     * 栈测试
     */
    @Test
    public void LinkedStackTest(){
        LinkedStack lruLinkList = new LinkedStack(4);

        Stream.iterate(0,x->x+1).limit(4).forEach(
                lruLinkList::push
        );

        Integer pop = lruLinkList.pop();
        boolean push = lruLinkList.push(1);
        boolean push1 = lruLinkList.push(2);
    }
}
