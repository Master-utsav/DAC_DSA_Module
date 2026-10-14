package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublyLinkedListIntegerTest {
    private DoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
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
    void testDeleteElement() {
        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(10); // Duplicate to test single deletion behavior

        // Delete the first occurrence of 10
        assertTrue(list.deleteElement(10), "Should successfully delete an existing element");
        assertEquals(2, list.size(), "Size should reduce to 2");
        assertTrue(list.isExists(10), "Second '10' should still exist");

        // Try deleting a non-existent element
        assertFalse(list.deleteElement(99), "Deleting non-existent element should return false");

        // Delete the remaining items
        assertTrue(list.deleteElement(20));
        assertTrue(list.deleteElement(10));
        assertTrue(list.isEmpty(), "List should be empty now");

        // Deleting from empty list
        assertFalse(list.deleteElement(10), "Deleting from empty list should return false");
    }

    @Test
    void testDeleteAllElement() {
        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(10);
        list.addAtLast(10);
        list.addAtLast(30);

        // Delete all occurrences of 10 (3 instances)
        assertEquals("[10, 20, 10, 10, 30, ]", list.forwardTraversal());
        assertTrue(list.deleteAllElement(10), "Should successfully delete all matching elements");
        assertEquals("[20, 30, ]", list.forwardTraversal());
        assertEquals(2, list.size(), "Size should reduce to 2 (20 and 30 left)");
        assertFalse(list.isExists(10), "No '10' elements should remain");
        assertTrue(list.isExists(20));
        assertTrue(list.isExists(30));

        // Try deleting all of something that doesn't exist
        assertFalse(list.deleteAllElement(99), "Should return false if element is not found");

        // Delete remaining elements to test clearing everything
        assertTrue(list.deleteAllElement(20));
        assertTrue(list.deleteAllElement(30));
        assertTrue(list.isEmpty(), "List should be completely empty");
    }

    @Test
    void testSearch() {
        // search() is a wrapper around isExists()
        assertFalse(list.search(10), "Search should return false for empty list");

        list.addAtLast(10);
        assertTrue(list.search(10), "Search should find the existing element");
        assertFalse(list.search(99), "Search should return false for non-existent item");
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