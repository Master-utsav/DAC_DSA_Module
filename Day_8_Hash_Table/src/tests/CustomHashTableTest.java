package tests;

import classes.CustomHashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CustomHashTableTest {

    private CustomHashTable<String, Integer> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new CustomHashTable<>();
    }

    @Nested
    @DisplayName("isEmpty() Tests")
    class IsEmptyTests {

        @Test
        @DisplayName("Should return true for a newly instantiated hash table")
        void isEmpty_NewTable_ReturnsTrue() {
            assertTrue(hashTable.isEmpty());
        }

        @Test
        @DisplayName("Should return false after adding an item")
        void isEmpty_AfterAdd_ReturnsFalse() {
            hashTable.add("key1", 100);
            assertFalse(hashTable.isEmpty());
        }

        @Test
        @DisplayName("Should return true after all items are removed")
        void isEmpty_AfterRemovingAllItems_ReturnsTrue() {
            hashTable.add("key1", 100);
            hashTable.remove("key1");
            assertTrue(hashTable.isEmpty());
        }
    }

    @Nested
    @DisplayName("add() and get() Tests")
    class AddAndGetTests {

        @Test
        @DisplayName("Should store and retrieve a single key-value pair")
        void addAndGet_SinglePair_Success() {
            hashTable.add("apple", 1);
            assertEquals(1, hashTable.get("apple"));
        }

        @Test
        @DisplayName("Should update existing value when adding a duplicate key")
        void add_DuplicateKey_UpdatesValue() {
            hashTable.add("apple", 1);
            hashTable.add("apple", 99);

            assertEquals(99, hashTable.get("apple"));
        }

        @Test
        @DisplayName("Should return null when querying non-existent key")
        void get_NonExistentKey_ReturnsNull() {
            assertNull(hashTable.get("nonExistent"));
        }

        @Test
        @DisplayName("Should handle multiple distinct keys correctly")
        void addAndGet_MultiplePairs_Success() {
            hashTable.add("one", 1);
            hashTable.add("two", 2);
            hashTable.add("three", 3);

            assertEquals(1, hashTable.get("one"));
            assertEquals(2, hashTable.get("two"));
            assertEquals(3, hashTable.get("three"));
        }

        @Test
        @DisplayName("Should handle dynamic resizing gracefully when load factor is exceeded")
        void add_TriggerResize_MaintainsAllData() {
            // Adding enough elements to force capacity expansion / resizing
            for (int i = 0; i < 20; i++) {
                hashTable.add("key" + i, i);
            }

            assertFalse(hashTable.isEmpty());
            for (int i = 0; i < 20; i++) {
                assertEquals(i, hashTable.get("key" + i));
            }
        }
    }

    @Nested
    @DisplayName("containsKey() Tests")
    class ContainsKeyTests {

        @Test
        @DisplayName("Should return true if key exists")
        void containsKey_ExistingKey_ReturnsTrue() {
            hashTable.add("banana", 5);
            assertTrue(hashTable.containsKey("banana"));
        }

        @Test
        @DisplayName("Should return false if key does not exist")
        void containsKey_NonExistentKey_ReturnsFalse() {
            assertFalse(hashTable.containsKey("banana"));
        }

        @Test
        @DisplayName("Should return false after a key has been removed")
        void containsKey_AfterRemoval_ReturnsFalse() {
            hashTable.add("banana", 5);
            hashTable.remove("banana");
            assertFalse(hashTable.containsKey("banana"));
        }
    }

    @Nested
    @DisplayName("remove() Tests")
    class RemoveTests {

        @Test
        @DisplayName("Should remove key and return its associated value")
        void remove_ExistingKey_ReturnsValueAndRemoves() {
            hashTable.add("cherry", 10);
            Integer removedValue = hashTable.remove("cherry");

            assertEquals(10, removedValue);
            assertNull(hashTable.get("cherry"));
            assertFalse(hashTable.containsKey("cherry"));
        }

        @Test
        @DisplayName("Should return null when removing a key that doesn't exist")
        void remove_NonExistentKey_ReturnsNull() {
            assertNull(hashTable.remove("ghostKey"));
        }
    }

    @Nested
    @DisplayName("getKeySet() Tests")
    class GetKeySetTests {

        @Test
        @DisplayName("Should return empty list for empty hash table")
        void getKeySet_EmptyTable_ReturnsEmptyList() {
            ArrayList<String> keySet = hashTable.getKeySet();
            assertNotNull(keySet);
            assertTrue(keySet.isEmpty());
        }

        @Test
        @DisplayName("Should contain all inserted keys")
        void getKeySet_PopulatedTable_ContainsAllKeys() {
            hashTable.add("k1", 10);
            hashTable.add("k2", 20);
            hashTable.add("k3", 30);

            ArrayList<String> keySet = hashTable.getKeySet();

            assertEquals(3, keySet.size());
            assertTrue(keySet.contains("k1"));
            assertTrue(keySet.contains("k2"));
            assertTrue(keySet.contains("k3"));
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return [] for an empty hash table")
        void toString_EmptyTable_ReturnsBracketRepresentation() {
            assertEquals("[]", hashTable.toString());
        }

        @Test
        @DisplayName("Should return non-empty string representation for populated table")
        void toString_PopulatedTable_ReturnsFormattedString() {
            hashTable.add("testKey", 42);
            String result = hashTable.toString();

            assertNotNull(result);
            assertNotEquals("[]", result);
            assertTrue(result.contains("testKey"));
            assertTrue(result.contains("42"));
        }
    }
}