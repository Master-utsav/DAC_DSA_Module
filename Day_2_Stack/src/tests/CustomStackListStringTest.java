package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.CustomStackSinglyLinkedList;

public class CustomStackListStringTest {
    private CustomStackSinglyLinkedList<String> stack;

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
        stack.push("Apple");
        stack.push("Banana");

        assertFalse(stack.isEmpty(), "Stack should not be empty after push");
        assertEquals(2, stack.size(), "Size should be 2 after two pushes");
    }

    @Test
    void testPopLIFOOrder() {
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry"); // "Cherry" is at the top

        assertEquals("Cherry", stack.pop(), "First pop should return 'Cherry'");
        assertEquals("Banana", stack.pop(), "Second pop should return 'Banana'");
        assertEquals(1, stack.size(), "Size should be 1 remaining");

        assertEquals("Apple", stack.pop(), "Last pop should return 'Apple'");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
        assertEquals(0, stack.size(), "Size should be 0");
    }

    @Test
    void testPeek() throws Exception {
        stack.push("Apple");
        stack.push("Banana"); // "Banana" is at the top

        assertEquals("Banana", stack.peek(), "Peek should return 'Banana'");
        assertEquals(2, stack.size(), "Peek should not change the stack size");

        // Verify peek didn't remove the element
        assertEquals("Banana", stack.pop(), "Top element should still be present for pop");
    }

    @Test
    void testEmptyStackEdgeCases() {
        assertNull(stack.pop(), "Popping an empty stack should return null");
    }
}
