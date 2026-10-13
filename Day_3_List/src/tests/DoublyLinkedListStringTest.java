package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublyLinkedListStringTest {
    private DoublyLinkedList<String> list;

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
    void testDeleteElement() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");
        list.addAtLast("Apple"); // Duplicate to test single deletion behavior

        // Delete the first occurrence of "Apple"
        assertTrue(list.deleteElement("Apple"), "Should successfully delete an existing element");
        assertEquals(2, list.size(), "Size should reduce to 2");
        assertTrue(list.isExists("Apple"), "Second 'Apple' should still exist");

        // Try deleting a non-existent element
        assertFalse(list.deleteElement("Mango"), "Deleting non-existent element should return false");

        // Delete the remaining items
        assertTrue(list.deleteElement("Banana"));
        assertTrue(list.deleteElement("Apple"));
        assertTrue(list.isEmpty(), "List should be empty now");

        // Deleting from empty list
        assertFalse(list.deleteElement("Apple"), "Deleting from empty list should return false");
    }

    @Test
    void testDeleteAllElement() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");
        list.addAtLast("Apple");
        list.addAtLast("Apple");
        list.addAtLast("Cherry");

        // Delete all occurrences of "Apple" (3 instances)
        assertTrue(list.deleteAllElement("Apple"), "Should successfully delete all matching elements");
        assertEquals(2, list.size(), "Size should reduce to 2 (Banana and Cherry left)");
        assertFalse(list.isExists("Apple"), "No 'Apple' elements should remain");
        assertTrue(list.isExists("Banana"));
        assertTrue(list.isExists("Cherry"));

        // Try deleting all of something that doesn't exist
        assertFalse(list.deleteAllElement("Mango"), "Should return false if element is not found");

        // Delete remaining elements to test clearing everything
        assertTrue(list.deleteAllElement("Banana"));
        assertTrue(list.deleteAllElement("Cherry"));
        assertTrue(list.isEmpty(), "List should be completely empty");
    }

    @Test
    void testSearch() {
        // search() is a wrapper around isExists()
        assertFalse(list.search("Apple"), "Search should return false for empty list");

        list.addAtLast("Apple");
        assertTrue(list.search("Apple"), "Search should find the existing element");
        assertFalse(list.search("Banana"), "Search should return false for non-existent item");
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