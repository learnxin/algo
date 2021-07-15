package algo.tree.binarySearchTree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
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

    /**
     * 测试支持重复数据的tree
     * @return
     */
    public BinarySearchTreeRelease getBinaryTreeRelease(){
        BinarySearchTreeRelease binarySearchTree = new BinarySearchTreeRelease();
        Random random = new Random();
        Stream.iterate(0, x->x+1).limit(100).forEach(
                e-> binarySearchTree.insert(random.nextInt(50))
        );
        return binarySearchTree;
    }

    /**
     * 测试支持重复数据的tree
     * @return
     */
    @Test
    public void testFindBinaryTreeRelease(){
        BinarySearchTreeRelease binaryTree = getBinaryTreeRelease();
        binaryTree.insert(30);
        List<Node> nodes = binaryTree.find(30);
    }

    /**
     * 测试支持重复数据的tree
     * @return
     */
    @Test
    public void testDeleteBinaryTreeRelease(){
        BinarySearchTreeRelease binaryTree = getBinaryTreeRelease();
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
     * 类似广度优先搜索
     * 添加求总层数的逻辑
     * @param root
     */
    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        //记录当前层已出队数量
        int front = 0;
        //记录当前层数量
        int behind = queue.size();
        //记录层数
        int floor = 0;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            front++;
            System.out.print(currentNode.getData() + " ");
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
            if(front == behind){
                //进入下一层
                floor++;
                front = 0;
                behind = queue.size();
                System.out.println("");
            }
        }
        System.out.println("floor = " + floor);
    }

    /**
     * 求二叉树高度/层数，等于左右子树高度较大者+1
     * 此处采用分治/深度优先递归
     */
    public static int getTreeFloor(Node root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        //获取右子树高度
        int rFloor = getTreeFloor(root.right);
        //获取左子树高度
        int lFloor = getTreeFloor(root.left);
        return Math.max(rFloor,lFloor)+1;
    }
    @Test
    public void testLevelOrder(){
        BinarySearchTreeRelease binaryTree = getBinaryTreeRelease();
        countLevelOrder(binaryTree.getTree());
        levelOrder(binaryTree.getTree());
        int treeFloor = getTreeFloor(binaryTree.getTree());
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
        outer:
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
                if(currentQueue.isEmpty()&&queue.isEmpty()) {
                    break outer;
                }
            }

        }
    }
}
