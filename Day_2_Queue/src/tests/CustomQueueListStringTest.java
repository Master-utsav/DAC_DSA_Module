package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.CustomQueueSinglyLinkedList;

class CustomQueueListStringTest {

    private CustomQueueSinglyLinkedList<String> queue;

    @BeforeEach
    void setUp() {
        queue = new CustomQueueSinglyLinkedList<>();
    }


    @Test
    void testInitialState() {
        assertTrue(queue.isEmpty(), "New queue should be empty");
        assertEquals(0, queue.size(), "Initial size should be 0");
    }

    @Test
    void testEnqueueAndSize() {
        queue.enqueue("Apple");
        queue.enqueue("Banana");

        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
        assertEquals(2, queue.size(), "Size should be 2 after two enqueues");
    }

    @Test
    void testDequeueFIFOOrder() {
        queue.enqueue("Apple"); // First in
        queue.enqueue("Banana");
        queue.enqueue("Cherry"); // Last in

        // Queue is FIFO: "Apple" was added first, so it should be removed first
        assertEquals("Apple", queue.dequeue(), "First dequeue should return 'Apple'");
        assertEquals("Banana", queue.dequeue(), "Second dequeue should return 'Banana'");
        assertEquals(1, queue.size(), "Size should be 1 remaining");

        assertEquals("Cherry", queue.dequeue(), "Last dequeue should return 'Cherry'");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing all elements");
        assertEquals(0, queue.size(), "Size should be 0");
    }

    @Test
    void testEmptyQueueEdgeCases() {
        assertNull(queue.dequeue(), "Dequeuing an empty queue should return null");
    }
}