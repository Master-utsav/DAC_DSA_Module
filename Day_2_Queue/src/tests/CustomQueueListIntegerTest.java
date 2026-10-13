package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.CustomQueueSinglyLinkedList;

class CustomQueueListIntegerTest {

    private CustomQueueSinglyLinkedList<Integer> queue;

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
        queue.enqueue(10);
        queue.enqueue(20);

        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
        assertEquals(2, queue.size(), "Size should be 2 after two enqueues");
    }

    @Test
    void testDequeueFIFOOrder() {
        queue.enqueue(10); // First in
        queue.enqueue(20);
        queue.enqueue(30); // Last in

        // Queue is FIFO: 10 was added first, so it should be removed first
        assertEquals(10, queue.dequeue(), "First dequeue should return 10");
        assertEquals(20, queue.dequeue(), "Second dequeue should return 20");
        assertEquals(1, queue.size(), "Size should be 1 remaining");

        assertEquals(30, queue.dequeue(), "Last dequeue should return 30");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing all elements");
        assertEquals(0, queue.size(), "Size should be 0");
    }

    @Test
    void testEmptyQueueEdgeCases() {
        assertNull(queue.dequeue(), "Dequeuing an empty queue should return null");
    }
}