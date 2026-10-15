package tests;

import classes.BinarySearchTree;
import interfaces.BinarySearchTreeInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeIntegerTest {

    private BinarySearchTreeInterface<Integer> bst;
    private Comparator<Integer> comparator;

    @BeforeEach
    public void setUp() {
        // Assuming your implementation class is named BinarySearchTree
        bst = new BinarySearchTree<>();
        comparator = Integer::compareTo;
    }

    @Test
    public void testInsertAndContains() {
        assertFalse(bst.contains(50, comparator));

        bst.insert(50, comparator);
        bst.insert(30, comparator);
        bst.insert(70, comparator);

        assertTrue(bst.contains(50, comparator));
        assertTrue(bst.contains(30, comparator));
        assertTrue(bst.contains(70, comparator));
        assertFalse(bst.contains(99, comparator));
    }

    @Test
    public void testMinMaxAndHeight() {
        // Tree: 50, 30, 70, 20, 40, 80
        bst.insert(50, comparator);
        bst.insert(30, comparator);
        bst.insert(70, comparator);
        bst.insert(20, comparator);
        bst.insert(40, comparator);
        bst.insert(80, comparator);

        assertEquals(20, bst.minElement());
        assertEquals(80, bst.maxElement());
        // Height depends on convention (root=0 or root=1), adjust expectation if needed
        assertEquals(2, bst.height());
    }

    @Test
    public void testTraversals() {
        // Tree:
        //        50
        //       /  \
        //      30   70
        //     /  \    \
        //    20  40    80
        bst.insert(50, comparator);
        bst.insert(30, comparator);
        bst.insert(70, comparator);
        bst.insert(20, comparator);
        bst.insert(40, comparator);
        bst.insert(80, comparator);

        // Adjust string format expectations based on your exact trailing spacing/commas
        assertEquals("[20, 30, 40, 50, 70, 80, ]", bst.inOrderTraversal());
        assertEquals("[50, 30, 20, 40, 70, 80, ]", bst.preOrderTraversal());
        assertEquals("[20, 40, 30, 80, 70, 50, ]", bst.postOrderTraversal());
        assertEquals("[50, 30, 70, 20, 40, 80, ]", bst.levelOrderTraversal());
        assertEquals("50, \n30, 70, \n20, 40, 80, \n", bst.levelOrderTraversalPerLevel());
    }

    @Test
    public void testInOrderPreAndSuccessorEdges() {
        // Tree:
        //        50
        //       /  \
        //      30   70
        //     /  \    \
        //    20  40    80
        bst.insert(50, comparator);
        bst.insert(30, comparator);
        bst.insert(70, comparator);
        bst.insert(20, comparator);
        bst.insert(40, comparator);
        bst.insert(80, comparator);

        // Standard cases (using .data to extract value from TreeNode)
        assertEquals(40, bst.inOrderPredecessor(50, comparator), "Inorder pre 1 test fails");
        assertEquals(70, bst.inOrderSuccessor(50, comparator), "Inorder succ 1 test fails");
        assertEquals(50, bst.inOrderPredecessor(70, comparator), "Inorder pre 2 test fails");
        assertEquals(30, bst.inOrderSuccessor(20, comparator), "Inorder succ 2 test fails");

        // Edge Cases:
        // 1. Min element (20) has no in-order predecessor -> should return null
        assertNull(bst.inOrderPredecessor(20, comparator), "Min element predecessor should be null");

        // 2. Max element (80) has no in-order successor -> should return null
        assertNull(bst.inOrderSuccessor(80, comparator), "Max element successor should be null");

        // 3. Node 30's predecessor is 20, successor is 40
        assertEquals(20, bst.inOrderPredecessor(30, comparator));
        assertEquals(40, bst.inOrderSuccessor(30, comparator));

        // 4. Node 40's successor is 50
        assertEquals(50, bst.inOrderSuccessor(40, comparator));
    }

    @Test
    public void testRemove() {
        bst.insert(50, comparator);
        bst.insert(30, comparator);
        bst.insert(70, comparator);
        bst.insert(20, comparator);

        assertTrue(bst.contains(20, comparator));
        assertTrue(bst.remove(20, comparator)); // Remove leaf node
        assertFalse(bst.contains(20, comparator));

        // Remove node with child or root handling
        assertTrue(bst.remove(30, comparator));
        assertFalse(bst.contains(30, comparator));
    }

    @Test
    public void testIntegerCountingMethods() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Comparator<Integer> comparator = Integer::compareTo;

        // Tree Structure:
        //        50
        //       /  \
        //      30   70
        //        \    \
        //        30    70  (added duplicates to test value counting)
        //       /
        //      40

        bst.insert(50, comparator);
        bst.insert(30, comparator);
        bst.insert(70, comparator);
        bst.insert(30, comparator); // Duplicate 30
        bst.insert(40, comparator);
        bst.insert(70, comparator); // Duplicate 70
        // 1. Test total node count
        assertEquals(6, bst.countNode(), "Total node count failed");

        // 2. Test leaf node count (Nodes with no children: 30, 40, 70)
        assertEquals(2, bst.countLeafNodes(), "Leaf node count failed");

        // 3. Test counting nodes with specific values
        assertEquals(2, bst.countNodesWithValues(30), "Count for value 30 failed");
        assertEquals(2, bst.countNodesWithValues(70), "Count for value 70 failed");
        assertEquals(1, bst.countNodesWithValues(50), "Count for value 50 failed");
        assertEquals(0, bst.countNodesWithValues(99), "Count for non-existent value failed");
    }
}