package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import classes.CircularDoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CircularDoublyLinkedListIntegerTest {
    private CircularDoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CircularDoublyLinkedList<>();
    }

    @Test
    void testInitialState() {
        assertTrue(list.isEmpty(), "New list should be empty");
        assertEquals(0, list.size(), "Initial size should be 0");
        assertEquals("[]", list.forwardTraversal());
        assertEquals("[]", list.backwardTraversal());
    }

    @Test
    void testAddAtFirst() {
        assertTrue(list.addAtFirst(10));
        assertTrue(list.addAtFirst(20));

        assertEquals(2, list.size(), "Size should be 2 after additions");
        assertFalse(list.isEmpty(), "List should not be empty");

        assertTrue(list.isExists(20));
        assertTrue(list.isExists(10));
        assertEquals("[20, 10, ]", list.forwardTraversal());
        assertEquals("[10, 20, ]", list.backwardTraversal());
    }

    @Test
    void testAddAtLast() {
        assertTrue(list.addAtLast(10));
        assertTrue(list.addAtLast(20));

        assertEquals(2, list.size(), "Size should be 2 after additions");
        assertTrue(list.isExists(10));
        assertTrue(list.isExists(20));
        assertEquals("[10, 20, ]", list.forwardTraversal());
    }

    @Test
    void testAddAtPosition() {
        list.addAtLast(10);   // Position 0
        list.addAtLast(30);   // Position 1

        // Insert 20 in between at position 1
        assertTrue(list.addAtPosition(20, 1));

        assertEquals(3, list.size(), "Size should be 3");
        assertTrue(list.isExists(20));
    }

    @Test
    void testRemoveFromFirst() {
        list.addAtLast(10);
        list.addAtLast(20);

        Integer removed = list.removeFromFirst();
        assertEquals(10, removed, "Should remove the first element");
        assertEquals(1, list.size(), "Size should be reduced to 1");
        assertFalse(list.isExists(10));
        assertTrue(list.isExists(20));
    }

    @Test
    void testRemoveFromLast() {
        list.addAtLast(10);
        list.addAtLast(20);

        Integer removed = list.removeFromLast();
        assertEquals(20, removed, "Should remove the last element");
        assertEquals(1, list.size(), "Size should be reduced to 1");
        assertTrue(list.isExists(10));
        assertFalse(list.isExists(20));
    }

    @Test
    void testRemoveFromPosition() {
        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(30);

        Integer removed = list.removeFromPosition(1); // Removes 20
        assertEquals(20, removed);
        assertEquals(2, list.size());
        assertFalse(list.isExists(20));
    }

    @Test
    void testIsExists() {
        assertFalse(list.isExists(99), "Non-existent item should return false");

        list.addAtLast(99);
        assertTrue(list.isExists(99), "Existing item should return true");
    }

    @Test
    void testEdgeCases() {
        assertNull(list.removeFromFirst());
        assertNull(list.removeFromLast());
        assertNull(list.removeFromPosition(0));

        assertFalse(list.addAtPosition(50, -1));
        assertFalse(list.addAtPosition(50, 10));
    }
}
