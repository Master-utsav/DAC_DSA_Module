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
    public void testRemove() {
        bst.insert("M", comparator);
        bst.insert("G", comparator);
        bst.insert("T", comparator);

        assertTrue(bst.contains("G", comparator));
        assertTrue(bst.remove("G", comparator));
        assertFalse(bst.contains("G", comparator));
        assertTrue(bst.contains("M", comparator));
    }
}