package classes;

import interfaces.List;

import java.util.Objects;

public class CircularSinglyLinkedList<T> implements List<T> {

    public class Node<T> {
         Node<T> next = null;
        T data;

        public Node(T data){
            this.data = data;
            next = null;
        }
    }

    private Node<T> head = new Node<>(null);
    int size = 0;

    public CircularSinglyLinkedList(){
        head.next = head;
    }

    @Override
    public boolean addAtFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head.next;
        head.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtLast(T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = head.next;
        while(currentNode.next != head){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        newNode.next = head;
        size++;
        return true;
    }

    @Override
    public boolean addAtPosition(T data, int position) {
        if(position < 0 || position > size) return false;
        if(position == 0) return addAtFirst(data);
        if(position == size - 1) return addAtLast(data);

        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = head.next;
        Node<T> previousNode = null;
        int it = 0;
        while(currentNode.next != head){
            if(it == position) break;
            previousNode = currentNode;
            currentNode = currentNode.next;
            it++;
        }
        previousNode.next = newNode;
        newNode.next = currentNode;
        size++;
        return true;
    }

    @Override
    public T removeFromFirst() {
        if(head.next == head) return null;
        Node<T> currentNode = head.next;
        head.next = head.next.next;
        if(head.next == null) head.next = head;
        size--;
        return currentNode.data;
    }

    @Override
    public T removeFromLast() {
        if(head.next == head) return null;
        Node<T> currentNode = head.next;
        Node<T> previousNode = null;
        while(currentNode.next != head){
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        previousNode.next = currentNode.next;
        size--;
        return currentNode.data;
    }

    @Override
    public T removeFromPosition(int position) {
        if(position < 0 || position > size) return null;
        if(position == 0) return removeFromFirst();
        if(position == size - 1) return removeFromLast();

        Node<T> currentNode = head.next;
        Node<T> previousNode = null;
        int it = 0;
        while(currentNode.next != head){
            if(it == position) break;
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
        if (head == null) return false;
        Node<T> currentNode = head;
        do {
            if (Objects.equals(currentNode.data, data)) return true;
            currentNode = currentNode.next;
        } while (currentNode.next != head);
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head.next == head || size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
