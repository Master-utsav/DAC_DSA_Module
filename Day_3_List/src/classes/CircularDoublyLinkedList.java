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
        newNode.next = head.next;
        head.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtLast(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.prev = head.prev;
        head.prev.next = newNode;
        newNode.next = head;
        head.prev = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtPosition(T data, int position) {
        if (position < 0 || position > size) return false;
        if (position == 0) return addAtFirst(data);
        if (position == size) return addAtLast(data);

        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = head.next;
        for(int i = 0; i < position; i++, currentNode = currentNode.next);

        newNode.next = currentNode;
        newNode.prev = currentNode.prev;
        currentNode.prev.next = newNode;
        currentNode.prev = newNode;
        size++;
        return true;
    }

    @Override
    public T removeFromFirst() {
        // empty
        if (head.next == head) return null;
        Node<T> target = head.next;

        // Bypassoing the target node
        head.next = target.next;
        target.next.prev = head;
        size--;
        return target.data;
    }

    @Override
    public T removeFromLast() {
        // empty
        if (head.next == head) return null;
        Node<T> target = head.prev;

        // Bypassing the target node
        head.prev = target.prev;
        target.prev.next = head;

        size--;
        return target.data;
    }

    @Override
    public T removeFromPosition(int position) {
        if (position < 0 || position >= size) return null;
        if (position == 0) return removeFromFirst();
        if (position == size - 1) return removeFromLast();

        Node<T> currentNode = head.next;
        for(int i = 0; i < position; i++, currentNode = currentNode.next);

        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        size--;
        return currentNode.data;
    }

    @Override
    public boolean deleteElement(T element){
        if(!isExists(element)) return false;
        Node<T> currentNode = head.next;
        while(currentNode != head){
            if(currentNode.data == element){
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                size--;
                return true;
            }
            else{
                currentNode = currentNode.next;
            }
        }
        return true;
    }

    @Override
    public boolean deleteAllElement(T element){
        if(!isExists(element)) return false;
        Node<T> currentNode = head.next;
        while(currentNode != head){
            if(currentNode.data == element){
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                size--;
            }
            else{
                currentNode = currentNode.next;
            }
        }
        return true;
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

    public String forwardTraversal(){
        StringBuilder strB = new StringBuilder("[");
        Node<T> currentNode = head.next;
        if(currentNode == head) {
            strB.append("]");
            return strB.toString();
        }
        while(currentNode != head){
            strB.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        strB.append("]");
        return strB.toString();
    }

    public String backwardTraversal(){
        StringBuilder strB = new StringBuilder("[");
        Node<T> currentNode = head.prev;
        if(currentNode == head) {
            strB.append("]");
            return strB.toString();
        }
        while(currentNode != head){
            strB.append(currentNode.data).append(", ");
            currentNode = currentNode.prev;
        }
        strB.append("]");
        return strB.toString();
    }
}
