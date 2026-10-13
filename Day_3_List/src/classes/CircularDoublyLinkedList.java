package classes;

import interfaces.List;

import java.util.Objects;

public class CircularDoublyLinkedList<T> implements List<T> {

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

    private Node<T> head = new Node<>(null);
    int size = 0;

    public CircularDoublyLinkedList(){
        head.next = head;
        head.prev = head;
    }

    @Override
    public boolean addAtFirst(T data) {
        Node<T> newNode = new Node<>(data);
        head.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtLast(T data) {
        return false;
    }

    @Override
    public boolean addAtPosition(T data, int position) {
        return false;
    }

    @Override
    public T removeFromFirst() {
        return null;
    }

    @Override
    public T removeFromLast() {
        return null;
    }

    @Override
    public T removeFromPosition(int position) {
        return null;
    }

    @Override
    public boolean isExists(T data) {
        if (head == null) return false;
        Node<T> currentNode = head;
        do {
            if (Objects.equals(currentNode.data, data)) return true;
            currentNode = currentNode.next;
        } while (currentNode != head);
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
        Node<T> currentNode = head;
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
