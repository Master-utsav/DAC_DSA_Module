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

        Node<T> newNode = new Node<>(data);
        Node<T> previousNode = head;
        for (int i = 0; i < position; i++, previousNode = previousNode.next);

        Node<T> target = previousNode.next;
        previousNode.next = newNode;
        newNode.next = target;
        size++;
        return true;
    }

    @Override
    public T removeFromFirst() {
        if(head.next == head) return null;
        Node<T> currentNode = head.next;
        head.next = head.next.next;
        size--;
        return currentNode.data;
    }

    @Override
    public T removeFromLast() {
        if (head.next == head) return null;

        Node<T> currentNode = head;
        while (currentNode.next.next != head) {
            currentNode = currentNode.next;
        }

        Node<T> target = currentNode.next;
        currentNode.next = target.next;
        size--;
        return target.data;
    }

    @Override
    public T removeFromPosition(int position) {
        if (position < 0 || position >= size) return null;

        Node<T> prev = head;
        for (int i = 0; i < position; i++, prev = prev.next);

        Node<T> target = prev.next;
        prev.next = target.next;
        size--;
        return target.data;
    }


    @Override
    public boolean isExists(T data) {
        if (head.next == head) return false;
        Node<T> currentNode = head.next;
        while(currentNode != head){
            if(Objects.equals(currentNode.data, data)) return true;
            currentNode = currentNode.next;
        }
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
