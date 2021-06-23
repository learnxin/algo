package algo.tree.BinarySearchTree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @Author: wangran
 * @Description: test bianry tree
 * @Date: 2021/6/22 下午12:52
 */
public class TestBinaryTree {

    /**
     * binary tree insert
     * @return
     */
    public BinarySearchTree getBinaryTree(){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Random random = new Random();
        Stream.iterate(0, x->x+1).limit(100).forEach(
                e-> binarySearchTree.insert(random.nextInt(100))
        );
        return binarySearchTree;
    }

    @Test
    public void testFindBinaryTree(){
        BinarySearchTree binaryTree = getBinaryTree();
        binaryTree.insert(30);
        Node node = binaryTree.find(30);
    }

    @Test
    public void testDeleteBinaryTree(){
        BinarySearchTree binaryTree = getBinaryTree();
        int data = 30;
        binaryTree.deleteNode(data);
        binaryTree.deleteNode(data);
        binaryTree.deleteNode(data);
    }

    @Test
    public void testOrder(){
        BinarySearchTree binaryTree = getBinaryTree();
        inOrder(binaryTree.getTree());
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(Node node){
        if(node == null){
            return;
        }
        printNode(node);
        preOrder(node.left);
        preOrder(node.right);
    }
    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        printNode(node);
        inOrder(node.right);
    }
    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        printNode(node);

    }

    public void printNode(Node node){
        System.out.print(node.data+" ");
    }


    /**
     * 层次遍历二叉树
     *
     * @param root
     */
    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.print(currentNode.getData() + " ");
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }


    @Test
    public void testLevelOrder(){
        BinarySearchTree binaryTree = getBinaryTree();
        countLevelOrder(binaryTree.getTree());
    }

    /**
     * 层次遍历二叉树
     *
     * @param root
     */
    public void countLevelOrder(Node root){
        if(root == null){
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        LinkedList<Node> currentQueue = new LinkedList<>();
        while (true){
            //打印当前层
            while (!queue.isEmpty()){
                Node poll = queue.poll();
                printNode(poll);
                currentQueue.add(poll);
            }
            //可在此统计总层数
            System.out.println("");
            //push 下一层
            while (!currentQueue.isEmpty()){
                Node poll = currentQueue.poll();
                if(poll.getLeft() != null){
                    queue.add(poll.getLeft());
                }
                if(poll.getRight() != null){
                    queue.add(poll.getRight());
                }
            }
            if(queue.isEmpty()){
                return;
            }
        }
    }
}
