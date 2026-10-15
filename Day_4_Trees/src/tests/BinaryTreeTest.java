package tests;

import classes.BinaryTree;
import classes.TreeNode;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {

    @Nested
    class BinaryTreeTestBasics{
        @Test
        public void testStringBinaryTreeStrings() {
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
            assertEquals("[root, apple, banana, cherry, ]", tree.levelOrderTraversal(), "levelOrder traversal failed");
            assertEquals("root, \napple, banana, \ncherry, \n", tree.levelOrderTraversalPerLevel(), "level order per level failed");

            // Test Node Counts
            assertEquals(4, tree.countNode(), "Total node count failed");
            assertEquals(2, tree.countLeafNodes(), "Leaf node count failed");

            // Test Specific Value Counting
            assertEquals(1, tree.countNodesWithValues("root"), "Count for value 'root' failed");
            assertEquals(1, tree.countNodesWithValues("apple"), "Count for value 'apple' failed");
            assertEquals(0, tree.countNodesWithValues("grape"), "Count for non-existent value failed");
        }


        @Test
        public void testIntegerBinaryTreeIntegers() {
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
            assertEquals("[10, 5, 15, 2, ]", tree.levelOrderTraversal(), "levelOrder traversal failed");
            assertEquals("10, \n5, 15, \n2, \n", tree.levelOrderTraversalPerLevel(), "level order per level failed");

            // Test TreeNode Counts
            assertEquals(4, tree.countNode(), "Total TreeNode count failed");
            assertEquals(2, tree.countLeafNodes(), "Leaf TreeNode count failed"); // Using interface spelling 'countLeadTreeNodes'

            // Test Specific Value Counting
            assertEquals(1, tree.countNodesWithValues(10), "Count for value 10 failed");
            assertEquals(1, tree.countNodesWithValues(5), "Count for value 5 failed");
            assertEquals(0, tree.countNodesWithValues(99), "Count for non-existent value failed");
        }

    }
}
