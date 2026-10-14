package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.SinglyLinkedList;
import interfaces.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SinglyLinkedListIntegerTest {
    private List<Integer> list;

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
        Assertions.assertTrue(list.addAtFirst(10));
        Assertions.assertTrue(list.addAtFirst(20));

        Assertions.assertEquals(2, list.size(), "Size should be 2 after additions");
        Assertions.assertFalse(list.isEmpty(), "List should not be empty");

        // Assuming addAtFirst pushes elements to the front (20 then 10)
        Assertions.assertTrue(list.isExists(20));
        Assertions.assertTrue(list.isExists(10));
    }

    @Test
    void testAddAtLast() {
        Assertions.assertTrue(list.addAtLast(10));
        Assertions.assertTrue(list.addAtLast(20));

        Assertions.assertEquals(2, list.size(), "Size should be 2 after additions");
        Assertions.assertTrue(list.isExists(10));
        Assertions.assertTrue(list.isExists(20));
    }

    @Test
    void testAddAtPosition() {
        list.addAtLast(10);   // Position 0 (assuming 0-indexed)
        list.addAtLast(30);   // Position 1

        // Insert 20 in between
        Assertions.assertTrue(list.addAtPosition(20, 1));

        Assertions.assertEquals(3, list.size(), "Size should be 3");
        Assertions.assertTrue(list.isExists(20));
    }

    @Test
    void testRemoveFromFirst() {
        list.addAtLast(10);
        list.addAtLast(20);

        Integer removed = list.removeFromFirst();
        assertEquals(10, removed, "Should remove the first element");
        Assertions.assertEquals(1, list.size(), "Size should be reduced to 1");
        Assertions.assertFalse(list.isExists(10));
        Assertions.assertTrue(list.isExists(20));
    }

    @Test
    void testRemoveFromLast() {
        list.addAtLast(10);
        list.addAtLast(20);

        Integer removed = list.removeFromLast();
        assertEquals(20, removed, "Should remove the last element");
        Assertions.assertEquals(1, list.size(), "Size should be reduced to 1");
        Assertions.assertTrue(list.isExists(10));
        Assertions.assertFalse(list.isExists(20));
    }

    @Test
    void testRemoveFromPosition() {
        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(30);

        Integer removed = list.removeFromPosition(1); // Removes 20
        assertEquals(20, removed);
        Assertions.assertEquals(2, list.size());
        Assertions.assertFalse(list.isExists(20));
    }

    @Test
    void testIsExists() {
        Assertions.assertFalse(list.isExists(99), "Non-existent item should return false");

        list.addAtLast(99);
        Assertions.assertTrue(list.isExists(99), "Existing item should return true");
    }

    @Test
    void testDeleteElement() {
        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(10); // Duplicate to test single deletion behavior

        // Delete the first occurrence of 10
        Assertions.assertTrue(list.deleteElement(10), "Should successfully delete an existing element");
        Assertions.assertEquals(2, list.size(), "Size should reduce to 2");
        Assertions.assertTrue(list.isExists(10), "Second '10' should still exist");

        // Try deleting a non-existent element
        Assertions.assertFalse(list.deleteElement(99), "Deleting non-existent element should return false");

        // Delete the remaining items
        Assertions.assertTrue(list.deleteElement(20));
        Assertions.assertTrue(list.deleteElement(10));
        Assertions.assertTrue(list.isEmpty(), "List should be empty now");

        // Deleting from empty list
        Assertions.assertFalse(list.deleteElement(10), "Deleting from empty list should return false");
    }

    @Test
    void testDeleteAllElement() {
        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(10);
        list.addAtLast(10);
        list.addAtLast(30);

        // Delete all occurrences of 10 (3 instances)
        Assertions.assertTrue(list.deleteAllElement(10), "Should successfully delete all matching elements");
        Assertions.assertEquals(2, list.size(), "Size should reduce to 2 (20 and 30 left)");
        Assertions.assertFalse(list.isExists(10), "No '10' elements should remain");
        Assertions.assertTrue(list.isExists(20));
        Assertions.assertTrue(list.isExists(30));

        // Try deleting all of something that doesn't exist
        Assertions.assertFalse(list.deleteAllElement(99), "Should return false if element is not found");

        // Delete remaining elements to test clearing everything
        Assertions.assertTrue(list.deleteAllElement(20));
        Assertions.assertTrue(list.deleteAllElement(30));
        Assertions.assertTrue(list.isEmpty(), "List should be completely empty");
    }

    @Test
    void testSearch() {
        // search() is a wrapper around isExists()
        Assertions.assertFalse(list.search(10), "Search should return false for empty list");

        list.addAtLast(10);
        Assertions.assertTrue(list.search(10), "Search should find the existing element");
        Assertions.assertFalse(list.search(99), "Search should return false for non-existent item");
    }

    @Test
    void testEdgeCases() {
        // Removing from empty list
        assertNull(list.removeFromFirst());
        assertNull(list.removeFromLast());
        assertNull(list.removeFromPosition(0));

        // Invalid position checks
        assertFalse(list.addAtPosition(50, -1));
        assertFalse(list.addAtPosition(50, 5));
    }

    @Test
    @DisplayName("Test Reversing Integer Singly Linked List")
    void testReverseIntegerList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addAtLast(1);
        list.addAtLast(2);
        list.addAtLast(3);
        // Initial: 1 -> 2 -> 3

        list.reverseList();

        // Expected after reversal: 3 -> 2 -> 1
        assertEquals("[3, 2, 1, ]", list.forwardTraversal());
    }

}
