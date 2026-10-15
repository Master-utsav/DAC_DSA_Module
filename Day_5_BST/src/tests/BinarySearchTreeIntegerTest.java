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
    public void testInOrderPreAndSuccesssor(){
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

        assertEquals(40, bst.inOrderPredecessor(50, comparator).data, "Inorder pre 1 test fails");
        assertEquals(70, bst.inOrderSuccessor(50, comparator).data, "Inorder succ 1 test fails");
        assertEquals(50, bst.inOrderPredecessor(70, comparator).data, "Inorder pre 2 test fails");
        assertEquals(30, bst.inOrderSuccessor(20, comparator).data, "Inorder succ 2 test fails");

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
}