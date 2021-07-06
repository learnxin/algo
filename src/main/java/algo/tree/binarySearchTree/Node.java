package algo.tree.binarySearchTree;

import lombok.Data;

@Data
public class Node{
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
}

