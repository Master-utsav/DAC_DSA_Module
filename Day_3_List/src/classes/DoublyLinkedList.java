package classes;

import interfaces.List;

public class DoublyLinkedList<T> implements List<T> {

    public class Node<T>{
        Node<T> next = null;
        Node<T> prev = null;
        T data;

        public Node(T data){
            this.data = data;
            next = null;
            prev = null;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    int size = 0;

    @Override
    public boolean addAtFirst(T data) {
        Node<T> newNode = new Node<>(data);
        // empty
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtLast(T data) {
        Node<T> newNode = new Node<>(data);
        // empty
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtPosition(T data, int position) {
        Node<T> newNode = new Node<>(data);
        if(position < 0 || position > size) return false;
        // start
        if(position == 0) return addAtFirst(data);
        // end
        if(position == size - 1) return addAtLast(data);
        // else
        Node<T> currentNode = head;
        int it = 0;
        while(currentNode.next != null){
            if(it == position) break;
            currentNode = currentNode.next;
            it++;
        }
        newNode.next = currentNode.next;
        newNode.prev = currentNode.prev;
        currentNode.prev.next = newNode;
        currentNode.next.prev = newNode;
        currentNode.next = null;
        currentNode.prev = null;
        size++;
        return true;
    }

    @Override
    public T removeFromFirst() {
        Node<T> currentNode = head;
        // empty
        if(size == 0 || currentNode == null) return null;
        // single
        if(head == tail){
            head = null;
            tail = null;
            size--;
            return currentNode.data;
        }
        //else
        head = currentNode.next;
        head.prev = null;
        currentNode.next = null;
        size--;
        return currentNode.data;
    }

    @Override
    public T removeFromLast() {
        Node<T> currentNode = tail;
        // empty
        if(size == 0 || currentNode == null) return null;
        // single
        if(head == tail){
            head = null;
            tail = null;
            size--;
            return currentNode.data;
        }
        //else
        tail = currentNode.prev;
        tail.next = null;
        currentNode.prev = null;
        size--;
        return currentNode.data;
    }

    @Override
    public T removeFromPosition(int position) {
        if(position < 0 || position > size) return null;

        if(position == 0) return removeFromFirst();
        if(position == size - 1) return removeFromLast();

        Node<T> currentNode = head;
        int it = 0;
        while(currentNode.next != null){
            if(it == position) break;
            currentNode = currentNode.next;
            it++;
        }
        Node<T> tempNode = currentNode;
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        size--;
        return tempNode.data;

    }

    @Override
    public boolean isExists(T data) {
        Node<T> currentNode = head;
        if(currentNode == null) return false;
        while(currentNode != null){
            if(currentNode.data.equals(data)) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null || size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public String forwardTraversal(){
        StringBuilder strB = new StringBuilder("[");
        Node<T> currentNode = head;
        if(currentNode == null) {
            strB.append("]");
            return strB.toString();
        }
        while(currentNode != null){
            strB.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        strB.append("]");
        return strB.toString();
    }

    public String backwardTraversal(){
        StringBuilder strB = new StringBuilder("[");
        Node<T> currentNode = tail;
        if(currentNode == null) {
            strB.append("]");
            return strB.toString();
        }
        while(currentNode != null){
            strB.append(currentNode.data).append(", ");
            currentNode = currentNode.prev;
        }
        strB.append("]");
        return strB.toString();
    }

}
