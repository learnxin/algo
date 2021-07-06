package algo.tree.binarySearchTree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangran
 * @Description: 二叉查找树 支持重复数据
 * @Date: 2021/6/22 下午12:37
 */
@Data
public class BinarySearchTreeRelease {
    private Node tree;

    public List<Node> find(int data){
        List<Node> results = new ArrayList<>();
        Node p = tree;
        while (p != null){
            //当要查找数据的时候，遇到值相同的节点，我们并不停止查找操作，而是继续在右子树中查找，直到遇到叶子节点，才停止。
            if(p.data == data){
                results.add(p);
                p = p.right;
            }
            else if(p.data > data){
                p = p.left;
            }else {
                p = p.right;
            }
        }
        return results;
    }


    public void insert(int data){
        Node node = new Node(data);
        if(tree == null){
            tree = node;
            return;
        }
        Node p = tree;
        while (p != null){
            //在查找插入位置的过程中，如果碰到一个节点的值，与要插入数据的值相同，我们就将这个要插入的数据放到这个节点的右子树
            if(data > p.data || data == p.data){
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

    public void deleteNode(int data){

        Node p = tree;
        Node prep = null;
        while (p != null){
            if(p.data == data){
                //同find 遍历右子树直到删除所有相同数据
                deleteNode(p,prep);
                p = prep;
            }
            prep = p;
            if(p.data > data){
                p = p.left;
            }else{
                p = p.right;
            }
        }


    }

    public void deleteNode(Node p,Node prep){
        if(p == null) {
            return;
        }

        //删除的节点有两个子节点
        if(p.getRight() != null && p.getLeft() != null){
            //寻找右子树最小节点替换(左子树最大节点也可) 注意，此节点可能存在右子树
            Node minRightNode = p.right,preMinRightNode = p;
            while (minRightNode.left != null){
                preMinRightNode = minRightNode;
                minRightNode = minRightNode.left;
            }
            //替换 p，问题就变化为 删除minRightNode(一个无子节点/仅有一个子节点的问题 这是后面的步骤可以覆盖的问题)
            p.data = minRightNode.data;
            p = minRightNode;
            prep = preMinRightNode;
// 错误的方式，此时minRightNode 可能有右子树
//            preMinRightNode.left = null;
//            minRightNode.left = p.left;
//            minRightNode.right = p.right;

        }
        Node child;
        //删除的节点有一个子节点
        if(p.getRight() != null || p.getLeft() != null){
            child = p.getRight() == null?p.getLeft():p.getRight();
        }
        else {
            //删除的节点没有子节点
            child = null;
        }
        if(prep == null){
            tree = null;
        }else if (prep.right == p){
            prep.right = child;
        }else {
            prep.left = child;
        }
    }
}
