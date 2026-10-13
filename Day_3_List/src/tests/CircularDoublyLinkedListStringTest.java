package tests;

import classes.CircularDoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CircularDoublyLinkedListStringTest {
    private CircularDoublyLinkedList<String> list;

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
        assertTrue(list.addAtFirst("Apple"));
        assertTrue(list.addAtFirst("Banana"));

        assertEquals(2, list.size(), "Size should be 2 after additions");
        assertFalse(list.isEmpty(), "List should not be empty");

        assertTrue(list.isExists("Banana"));
        assertTrue(list.isExists("Apple"));
        assertEquals("[Banana, Apple, ]", list.forwardTraversal());
        assertEquals("[Apple, Banana, ]", list.backwardTraversal());
    }

    @Test
    void testAddAtLast() {
        assertTrue(list.addAtLast("Apple"));
        assertTrue(list.addAtLast("Banana"));

        assertEquals(2, list.size(), "Size should be 2 after additions");
        assertTrue(list.isExists("Apple"));
        assertTrue(list.isExists("Banana"));
        assertEquals("[Apple, Banana, ]", list.forwardTraversal());
    }

    @Test
    void testAddAtPosition() {
        list.addAtLast("Apple");   // Position 0
        list.addAtLast("Cherry");  // Position 1

        // Insert "Banana" in between at position 1
        assertTrue(list.addAtPosition("Banana", 1));

        assertEquals(3, list.size(), "Size should be 3");
        assertTrue(list.isExists("Banana"));
    }

    @Test
    void testRemoveFromFirst() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");

        String removed = list.removeFromFirst();
        assertEquals("Apple", removed, "Should remove the first element");
        assertEquals(1, list.size(), "Size should be reduced to 1");
        assertFalse(list.isExists("Apple"));
        assertTrue(list.isExists("Banana"));
    }

    @Test
    void testRemoveFromLast() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");

        String removed = list.removeFromLast();
        assertEquals("Banana", removed, "Should remove the last element");
        assertEquals(1, list.size(), "Size should be reduced to 1");
        assertTrue(list.isExists("Apple"));
        assertFalse(list.isExists("Banana"));
    }

    @Test
    void testRemoveFromPosition() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");
        list.addAtLast("Cherry");

        String removed = list.removeFromPosition(1); // Removes "Banana"
        assertEquals("Banana", removed);
        assertEquals(2, list.size());
        assertFalse(list.isExists("Banana"));
    }

    @Test
    void testIsExists() {
        assertFalse(list.isExists("Mango"), "Non-existent item should return false");

        list.addAtLast("Mango");
        assertTrue(list.isExists("Mango"), "Existing item should return true");
    }

    @Test
    void testEdgeCases() {
        assertNull(list.removeFromFirst());
        assertNull(list.removeFromLast());
        assertNull(list.removeFromPosition(0));

        assertFalse(list.addAtPosition("Item", -1));
        assertFalse(list.addAtPosition("Item", 5));
    }
}
