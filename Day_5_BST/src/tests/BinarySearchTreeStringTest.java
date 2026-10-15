package tests;

import classes.BinarySearchTree;
import interfaces.BinarySearchTreeInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeStringTest {

    private BinarySearchTreeInterface<String> bst;
    private Comparator<String> comparator;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
        comparator = String::compareTo;
    }

    @Test
    public void testInsertAndContains() {
        assertFalse(bst.contains("M", comparator));

        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);

        assertTrue(bst.contains("M", comparator));
        assertTrue(bst.contains("G", comparator));
        assertTrue(bst.contains("T", comparator));
        assertFalse(bst.contains("Z", comparator));
    }

    @Test
    public void testMinMaxAndHeight() {
        // Alphabetical BST: M, G, T, B, I, W
        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);
        bst.insert("B", comparator);
        bst.insert("I", comparator);
        bst.insert("W", comparator);

        assertEquals("B", bst.minElement());
        assertEquals("W", bst.maxElement());
        assertEquals(2, bst.height());
    }

    @Test
    public void testTraversals() {
        // Tree:
        //        M
        //       / \
        //      G   T
        //     / \   \
        //    B   I   W
        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);
        bst.insert("B", comparator);
        bst.insert("I", comparator);
        bst.insert("W", comparator);

        assertEquals("[B, G, I, M, T, W, ]", bst.inOrderTraversal());
        assertEquals("[M, G, B, I, T, W, ]", bst.preOrderTraversal());
        assertEquals("[B, I, G, W, T, M, ]", bst.postOrderTraversal());
        assertEquals("[M, G, T, B, I, W, ]", bst.levelOrderTraversal());
        assertEquals("M, \nG, T, \nB, I, W, \n", bst.levelOrderTraversalPerLevel());
    }

    @Test
    public void testStringInOrderPreAndSuccessorEdges() {
        // Alphabetical BST Tree:
        //        M
        //       / \
        //      G   T
        //     / \   \
        //    B   I   W
        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);
        bst.insert("B", comparator);
        bst.insert("I", comparator);
        bst.insert("W", comparator);

        // Standard cases
        assertEquals("I", bst.inOrderPredecessor("M", comparator), "String pre 1 failed");
        assertEquals("T", bst.inOrderSuccessor("M", comparator), "String succ 1 failed");
        assertEquals("G", bst.inOrderPredecessor("I", comparator), "String pre 2 failed");
        assertEquals("M", bst.inOrderSuccessor("I", comparator), "String succ 2 failed");

        // Edge Cases:
        // 1. Min element ("B") has no predecessor
        assertNull(bst.inOrderPredecessor("B", comparator), "Min string predecessor should be null");

        // 2. Max element ("W") has no successor
        assertNull(bst.inOrderSuccessor("W", comparator), "Max string successor should be null");

        // 3. Node "T" predecessor is "M", successor is "W"
        assertEquals("M", bst.inOrderPredecessor("T", comparator));
        assertEquals("W", bst.inOrderSuccessor("T", comparator));
    }

    @Test
    public void testRemove() {
        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);

        assertTrue(bst.contains("G", comparator));
        assertTrue(bst.remove("G", comparator));
        assertFalse(bst.contains("G", comparator));
        assertTrue(bst.contains("M", comparator));
    }

    @Test
    public void testStringCountingMethods() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        Comparator<String> comparator = String::compareTo;

        // Tree Structure:
        //        "M"
        //       /   \
        //    "G"    "T"
        //     \        \
        //     "G"       "T"  (duplicates)

        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);
        bst.insert("G", comparator); // Duplicate "G"
        bst.insert("T", comparator); // Duplicate "T"

        // 1. Test total node count
        assertEquals(5, bst.countNode(), "Total string node count failed");

        // 2. Test leaf node count (Nodes with no children: left duplicate "G", right duplicate "T")
        assertEquals(2, bst.countLeafNodes(), "Leaf string node count failed");

        // 3. Test counting nodes with specific values
        assertEquals(2, bst.countNodesWithValues("G"), "Count for value 'G' failed");
        assertEquals(2, bst.countNodesWithValues("T"), "Count for value 'T' failed");
        assertEquals(1, bst.countNodesWithValues("M"), "Count for value 'M' failed");
        assertEquals(0, bst.countNodesWithValues("Z"), "Count for non-existent value failed");
    }
}