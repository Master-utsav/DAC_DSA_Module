package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.BinaryTree;
import classes.TreeNode;
import org.junit.jupiter.api.Test;

public class BinaryTreeIntegerTest {

    @Test
    public void testIntegerBinaryTreeManualSetup() {
        // Manually constructing a simple binary tree:
        //        10
        //       /  \
        //      5    15
        //     /
        //    2

        BinaryTree<Integer> tree = new BinaryTree<>();


        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> leftChild = new TreeNode<>(5);
        TreeNode<Integer> rightChild = new TreeNode<>(15);
        TreeNode<Integer> leftLeftChild = new TreeNode<>(2);

        root.left = leftChild;
        root.right = rightChild;
        leftChild.left = leftLeftChild;

        tree.setRoot(root); // Assuming your tree has a setRoot or similar initialization method

        // Test Traversals (Adjust expected format to match your implementation)
        assertEquals("[10, 5, 2, 15, ]", tree.preOrderTraversal(), "Pre-order traversal failed");
        assertEquals("[2, 5, 10, 15, ]", tree.inOrderTraversal(), "In-order traversal failed");
        assertEquals("[2, 5, 15, 10, ]", tree.postOrderTraversal(), "Post-order traversal failed");

        // Test TreeNode Counts
        assertEquals(String.valueOf(4), tree.countNode(), "Total TreeNode count failed");
        assertEquals(String.valueOf(2), tree.countLeafNodes(), "Leaf TreeNode count failed"); // Using interface spelling 'countLeadTreeNodes'

        // Test Specific Value Counting
        assertEquals(String.valueOf(1), tree.countNodesWithValues(10), "Count for value 10 failed");
        assertEquals(String.valueOf(1), tree.countNodesWithValues(5), "Count for value 5 failed");
        assertEquals(String.valueOf(0), tree.countNodesWithValues(99), "Count for non-existent value failed");
    }
}