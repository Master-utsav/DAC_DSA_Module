package classes;

import interfaces.CustomHashTableInterface;
import java.util.ArrayList;
import java.util.LinkedList;

public class CustomHashTable<K, V> implements CustomHashTableInterface<K, V> {

    private int capacity = 5;
    private LinkedList<Node<K, V>>[] buckets;
    private static final double loadFactor = 0.75;
    int size = 0;

    public CustomHashTable(){
        buckets = new LinkedList[capacity];
    }

    static class Node<K, V>{
        K key;
        V value;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private int getBucketIdx(K key){
        int idx = Math.abs(key.hashCode() % (capacity - 1));
        if(buckets[idx] == null){
            buckets[idx] = new LinkedList<>();
        }
        return idx;
    }

    private int keyIndexInList(K key, int bucketIdx){
        if(buckets[bucketIdx].isEmpty()) return -1; // which means that key is not there
        int i = 0;
        for(Node<K, V> node : buckets[bucketIdx]){
            if(node.key.equals(key)) return i;
            i++;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private void callResize(int newCapacity){
        this.capacity = newCapacity;
        this.size = 0;
        LinkedList<Node<K, V>>[] oldBuckets = buckets;
        this.buckets = (LinkedList<Node<K, V>>[]) new LinkedList[newCapacity];

        for (LinkedList<Node<K, V>> oldBucket : oldBuckets) {
            if(oldBucket != null) {
                for (Node<K, V> node : oldBucket) {
                    addEntry(node.key, node.value);
                }
            }
        }
    }

    private void addEntry(K key, V value){
        int idx = getBucketIdx(key);
        int keyIndex = keyIndexInList(key, idx);

        if(keyIndex == -1){
            buckets[idx].add(new Node<>(key, value));
            size++;
        }
        else{
            buckets[idx].set(keyIndex, new Node<>(key, value));
        }
    }


    @Override
    public void add(K key, V value) {
        double lf = (size / (double) capacity);
        if(lf > loadFactor) callResize(capacity * 2);
        addEntry(key, value);
    }

    @Override
    public V get(K key) {
        int idx = getBucketIdx(key);

        if(buckets[idx].isEmpty()) return null; // which means that key is not there
        for(Node<K, V> node : buckets[idx]){
            if(node.key.equals(key)) return node.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int idx = getBucketIdx(key);
        int keyIndex = keyIndexInList(key, idx);
        return (keyIndex != -1);
    }

    @Override
    public V remove(K key) {
        int idx = getBucketIdx(key);
        int keyIndex = keyIndexInList(key, idx);
        V value = null;

        if (keyIndex == -1) return value;

        value = buckets[idx].get(keyIndex).value;
        buckets[idx].remove(keyIndex);
        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public ArrayList<K> getKeySet() {
        ArrayList<K> list = new ArrayList<>();
        for(LinkedList<Node<K, V>> bucket : buckets){
            if(bucket != null) {
                for (Node<K, V> node : bucket) {
                    list.add(node.key);
                }
            }
        }
        return list;
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        if(size == 0) return "[]";
        int i = 0;
        for(LinkedList<Node<K, V>> bucket : buckets){
            str.append("Bucket Idx : [").append(i).append("] |-> ");
            if(bucket != null){
                for(Node<K, V> node : bucket){
                    str.append("{").append(node.key).append(" : ").append(node.value).append("}, ");
                }
            }
            i++;
            str.append("\n");
        }

        return str.toString();
    }


}
