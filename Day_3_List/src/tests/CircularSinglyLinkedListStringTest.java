package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.CircularSinglyLinkedList;
import interfaces.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularSinglyLinkedListStringTest {
    private List<String> list;

    @BeforeEach
    void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test
    void testInitialState() {
        Assertions.assertTrue(list.isEmpty(), "New list should be empty");
        Assertions.assertEquals(0, list.size(), "Initial size should be 0");
    }

    @Test
    void testAddAtFirst() {
        Assertions.assertTrue(list.addAtFirst("Apple"));
        Assertions.assertTrue(list.addAtFirst("Banana"));

        Assertions.assertEquals(2, list.size(), "Size should be 2 after additions");
        Assertions.assertFalse(list.isEmpty(), "interfaces.List should not be empty");

        // Assuming addAtFirst pushes elements to the front ("Banana" then "Apple")
        Assertions.assertTrue(list.isExists("Banana"));
        Assertions.assertTrue(list.isExists("Apple"));
    }

    @Test
    void testAddAtLast() {
        Assertions.assertTrue(list.addAtLast("Apple"));
        Assertions.assertTrue(list.addAtLast("Banana"));

        Assertions.assertEquals(2, list.size(), "Size should be 2 after additions");
        Assertions.assertTrue(list.isExists("Apple"));
        Assertions.assertTrue(list.isExists("Banana"));
    }

    @Test
    void testAddAtPosition() {
        list.addAtLast("Apple");   // Position 0 (assuming 0-indexed)
        list.addAtLast("Cherry");  // Position 1

        // Insert "Banana" in between
        Assertions.assertTrue(list.addAtPosition("Banana", 1));

        Assertions.assertEquals(3, list.size(), "Size should be 3");
        Assertions.assertTrue(list.isExists("Banana"));
    }

    @Test
    void testRemoveFromFirst() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");

        String removed = list.removeFromFirst();
        assertEquals("Apple", removed, "Should remove the first element");
        Assertions.assertEquals(1, list.size(), "Size should be reduced to 1");
        Assertions.assertFalse(list.isExists("Apple"));
        Assertions.assertTrue(list.isExists("Banana"));
    }

    @Test
    void testRemoveFromLast() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");

        String removed = list.removeFromLast();
        assertEquals("Banana", removed, "Should remove the last element");
        Assertions.assertEquals(1, list.size(), "Size should be reduced to 1");
        Assertions.assertTrue(list.isExists("Apple"));
        Assertions.assertFalse(list.isExists("Banana"));
    }

    @Test
    void testRemoveFromPosition() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");
        list.addAtLast("Cherry");

        String removed = list.removeFromPosition(1); // Removes "Banana"
        assertEquals("Banana", removed);
        Assertions.assertEquals(2, list.size());
        Assertions.assertFalse(list.isExists("Banana"));
    }

    @Test
    void testIsExists() {
        Assertions.assertFalse(list.isExists("Mango"), "Non-existent item should return false");

        list.addAtLast("Mango");
        Assertions.assertTrue(list.isExists("Mango"), "Existing item should return true");
    }

    @Test
    void testEdgeCases() {
        // Removing from empty list
        assertNull(list.removeFromFirst());
        assertNull(list.removeFromLast());
        assertNull(list.removeFromPosition(0));

        // Invalid position checks (if your implementation throws exceptions or returns false/null)
        // Adjust these assertions based on how your error handling is written:
        // assertThrows(IndexOutOfBoundsException.class, () -> list.addAtPosition("Item", 10));
    }
}