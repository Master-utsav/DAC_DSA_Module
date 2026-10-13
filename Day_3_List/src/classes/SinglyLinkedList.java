package classes;

import interfaces.List;

public class SinglyLinkedList<T> implements List<T> {

    public class Node<T> {
        Node<T> next = null;
        T data;

        public Node(T data){
            this.data = data;
            next = null;
        }
    }

    private Node<T> head = null;
    int size = 0;

    @Override
    public boolean addAtFirst(T data) {
        Node<T> newNode = new Node<>(data);
        // empty
        if(head == null) {
            head = newNode;
            size++;
            return true;
        }
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtLast(T data) {
        Node<T> newNode = new Node<>(data);
        // empty
        if(head == null) {
            head = newNode;
            size++;
            return true;
        }
        Node<T> currentNode = head;
        while(currentNode.next != null){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtPosition(T data, int position) {
        if(position < 0 || position >= size) return false;
        Node<T> newNode = new Node<>(data);
        if(position == 0) {
            addAtFirst(data);
            return true;
        }
        else if(position == size - 1){
            addAtLast(data);
            return true;
        }
        // traverse
        Node<T> currentNode = head;
        Node<T> previousNode = null;
        int it = 0;
        while(currentNode != null){
            if(it == position) break;
            previousNode = currentNode;
            currentNode = currentNode.next;
            it++;
        }
        if(currentNode == null) return false;
        newNode.next = currentNode;
        previousNode.next = newNode;
        size++;
        return true;
    }

    @Override
    public T removeFromFirst() {
        Node<T> currentNode = head;
        // empty
        if(currentNode == null) return null;
        // single Node
        if(currentNode.next == null){
            head = null;
            size--;
            return currentNode.data;
        }
        // else
        head = currentNode.next;
        size--;
        return currentNode.data;
    }

    @Override
    public T removeFromLast() {
        Node<T> currentNode = head;
        // empty
        if(head == null) return null;
        // single Node
        if(currentNode.next == null){
            head = null;
            size--;
            return currentNode.data;
        }
        // else
        while(currentNode.next.next != null){
            currentNode = currentNode.next;
        }
        Node<T> tempNode = currentNode.next;
        currentNode.next = null;
        size--;
        return tempNode.data;
    }

    @Override
    public T removeFromPosition(int position) {
        if(position < 0 || position >= size) return null;
        if(position == 0) return removeFromFirst();
        if(position == size - 1) return removeFromLast();

        Node<T> currentNode = head;
        Node<T> previousNode = null;
        int it = 0;
        while(currentNode.next != null){
            if(it == position)break;
            previousNode = currentNode;
            currentNode = currentNode.next;
            it++;
        }

        previousNode.next = currentNode.next;
        size--;
        return currentNode.data;
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

}
