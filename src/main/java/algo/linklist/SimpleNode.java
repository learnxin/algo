package algo.linklist;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: wangran
 * @Description: 简单链表实现
 * @Date: 2021/1/14 下午10:51
 */
@Data
@Accessors(chain = true)
public class SimpleNode {

    public Object data;

    public SimpleNode next;

    public SimpleNode(Object data, SimpleNode next) {
        this.data = data;
        this.next = next;

    }

    public SimpleNode(Object data) {
        this.data = data;
        this.next = null;

    }
}
