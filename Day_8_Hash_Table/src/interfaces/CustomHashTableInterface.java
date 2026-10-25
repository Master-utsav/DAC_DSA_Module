package interfaces;

import java.util.ArrayList;

public interface CustomHashTableInterface<K, V> {
    void add(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    V remove(K key);
    boolean isEmpty();
    ArrayList<K> getKeySet();
}
