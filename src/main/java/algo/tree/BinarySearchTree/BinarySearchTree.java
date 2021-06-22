package algo.tree.BinarySearchTree;

import lombok.Data;

/**
 * @Author: wangran
 * @Description: 二叉查找树
 * @Date: 2021/6/22 下午12:37
 */
@Data
public class BinarySearchTree {
    private Node tree;




    public void insert(int data){
        Node node = new Node(data);
        if(tree == null){
            tree = node;
            return;
        }
        Node p = tree;
        while (p != null){
            if(data > p.data){
                if(p.right == null){
                    p.right = node;
                    return;
                }
                p = p.right;
            }else {
                if(p.left == null){
                    p.left = node;
                    return;
                }
                p = p.left;
            }
        }
    }
}
