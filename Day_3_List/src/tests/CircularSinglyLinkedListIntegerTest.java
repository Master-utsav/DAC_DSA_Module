package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.CircularSinglyLinkedList;
import interfaces.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class  CircularSinglyLinkedListIntegerTest{
    private List<Integer> list;

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
    void testEdgeCases() {
        // Removing from empty list
        assertNull(list.removeFromFirst());
        assertNull(list.removeFromLast());
        assertNull(list.removeFromPosition(0));

        // Invalid position checks
        assertFalse(list.addAtPosition(50, -1));
        assertFalse(list.addAtPosition(50, 5));
    }
}