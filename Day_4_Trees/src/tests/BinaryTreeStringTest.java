package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.BinaryTree;
import classes.TreeNode;
import org.junit.jupiter.api.Test;

public class BinaryTreeStringTest {

    @Test
    public void testStringBinaryTreeManualSetup() {
        // Manually constructing a string binary tree:
        //       "root"
        //       /    \
        //  "apple"  "banana"
        //              \
        //            "cherry"

        BinaryTree<String> tree = new BinaryTree<>();

        TreeNode<String> root = new TreeNode<>("root");
        TreeNode<String> leftChild = new TreeNode<>("apple");
        TreeNode<String> rightChild = new TreeNode<>("banana");
        TreeNode<String> rightRightChild = new TreeNode<>("cherry");

        root.left = leftChild;
        root.right = rightChild;
        rightChild.right = rightRightChild;

        tree.setRoot(root);

        // Test Traversals
        assertEquals("[root, apple, banana, cherry, ]", tree.preOrderTraversal(), "Pre-order traversal failed");
        assertEquals("[apple, root, banana, cherry, ]", tree.inOrderTraversal(), "In-order traversal failed");
        assertEquals("[apple, cherry, banana, root, ]", tree.postOrderTraversal(), "Post-order traversal failed");

        // Test Node Counts
        assertEquals(4, tree.countNode(), "Total node count failed");
        assertEquals(2, tree.countLeafNodes(), "Leaf node count failed");

        // Test Specific Value Counting
        assertEquals(1, tree.countNodesWithValues("root"), "Count for value 'root' failed");
        assertEquals(1, tree.countNodesWithValues("apple"), "Count for value 'apple' failed");
        assertEquals(0, tree.countNodesWithValues("grape"), "Count for non-existent value failed");
    }
}