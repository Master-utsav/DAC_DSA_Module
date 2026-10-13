package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.CustomStackSinglyLinkedList;

public class CustomStackListIntegerTest {
    private CustomStackSinglyLinkedList<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new CustomStackSinglyLinkedList<>();
    }

    @Test
    void testInitialState() {
        assertTrue(stack.isEmpty(), "New stack should be empty");
        assertEquals(0, stack.size(), "Initial size should be 0");
    }

    @Test
    void testPushAndSize() {
        stack.push(10);
        stack.push(20);

        assertFalse(stack.isEmpty(), "Stack should not be empty after push");
        assertEquals(2, stack.size(), "Size should be 2 after two pushes");
    }

    @Test
    void testPopLIFOOrder() {
        stack.push(10);
        stack.push(20);
        stack.push(30); // 30 is at the top (LIFO)

        assertEquals(30, stack.pop(), "First pop should return the top element (30)");
        assertEquals(20, stack.pop(), "Second pop should return 20");
        assertEquals(1, stack.size(), "Size should be 1 remaining");

        assertEquals(10, stack.pop(), "Last pop should return 10");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
        assertEquals(0, stack.size(), "Size should be 0");
    }

    @Test
    void testPeek() throws Exception {
        stack.push(10);
        stack.push(20); // 20 is at the top

        assertEquals(20, stack.peek(), "Peek should return the top element (20)");
        assertEquals(2, stack.size(), "Peek should not change the stack size");

        // Verify peek didn't remove the element
        assertEquals(20, stack.pop(), "Top element should still be present for pop");
    }

    @Test
    void testEmptyStackEdgeCases() {
        assertNull(stack.pop(), "Popping an empty stack should return null");
    }
}
