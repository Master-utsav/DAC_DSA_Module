package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.SinglyLinkedList;
import interfaces.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SinglyLinkedListStringTest {
    private List<String> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
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
    }

    @Test
    void testDeleteElement() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");
        list.addAtLast("Apple"); // Duplicate to test single deletion behavior

        // Delete the first occurrence of "Apple"
        Assertions.assertTrue(list.deleteElement("Apple"), "Should successfully delete an existing element");
        Assertions.assertEquals(2, list.size(), "Size should reduce to 2");
        Assertions.assertTrue(list.isExists("Apple"), "Second 'Apple' should still exist");

        // Try deleting a non-existent element
        Assertions.assertFalse(list.deleteElement("Mango"), "Deleting non-existent element should return false");

        // Delete the remaining items
        Assertions.assertTrue(list.deleteElement("Banana"));
        Assertions.assertTrue(list.deleteElement("Apple"));
        Assertions.assertTrue(list.isEmpty(), "List should be empty now");

        // Deleting from empty list
        Assertions.assertFalse(list.deleteElement("Apple"), "Deleting from empty list should return false");
    }

    @Test
    void testDeleteAllElement() {
        list.addAtLast("Apple");
        list.addAtLast("Banana");
        list.addAtLast("Apple");
        list.addAtLast("Apple");
        list.addAtLast("Cherry");

        // Delete all occurrences of "Apple" (3 instances)
        Assertions.assertTrue(list.deleteAllElement("Apple"), "Should successfully delete all matching elements");
        Assertions.assertEquals(2, list.size(), "Size should reduce to 2 (Banana and Cherry left)");
        Assertions.assertFalse(list.isExists("Apple"), "No 'Apple' elements should remain");
        Assertions.assertTrue(list.isExists("Banana"));
        Assertions.assertTrue(list.isExists("Cherry"));

        // Try deleting all of something that doesn't exist
        Assertions.assertFalse(list.deleteAllElement("Mango"), "Should return false if element is not found");

        // Delete remaining elements to test clearing everything
        Assertions.assertTrue(list.deleteAllElement("Banana"));
        Assertions.assertTrue(list.deleteAllElement("Cherry"));
        Assertions.assertTrue(list.isEmpty(), "List should be completely empty");
    }

    @Test
    void testSearch() {
        // search() is a wrapper around isExists()
        Assertions.assertFalse(list.search("Apple"), "Search should return false for empty list");

        list.addAtLast("Apple");
        Assertions.assertTrue(list.search("Apple"), "Search should find the existing element");
        Assertions.assertFalse(list.search("Banana"), "Search should return false for non-existent item");
    }

    @Test
    @DisplayName("Test Reversing String Singly Linked List")
    void testReverseStringList() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addAtLast("A");
        list.addAtLast("B");
        list.addAtLast("C");
        // Initial: "A" -> "B" -> "C"

        list.reverseList();

        // Expected after reversal: "C" -> "B" -> "A"
        assertEquals("[C, B, A, ]", list.forwardTraversal());
    }
}