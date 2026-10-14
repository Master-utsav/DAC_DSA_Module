package classes;

import interfaces.List;

import java.util.Objects;

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
        // traverse to last
        Node<T> currentNode = head;
        while(currentNode.next != null)
            currentNode = currentNode.next;

        currentNode.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAtPosition(T data, int position) {
        if(position < 0 || position > size) return false;
        Node<T> newNode = new Node<>(data);
        // first insert
        if(position == 0) {
            addAtFirst(data);
            return true;
        }
        // insert at last
        else if(position == size){
            addAtLast(data);
            return true;
        }
        // traverse
        Node<T> currentNode = head;
        for(int i = 0; i < position - 1; i++, currentNode = currentNode.next);

        newNode.next = currentNode.next;
        currentNode.next = newNode;
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
        while(currentNode.next.next != null)
            currentNode = currentNode.next;

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
        for (int i = 0; i < position - 1; i++, currentNode = currentNode.next);

        Node<T> tempNode = currentNode.next;
        currentNode.next = tempNode.next;
        size--;
        return tempNode.data;
    }

    @Override
    public boolean deleteElement(T element){
        if(!isExists(element)) return false;
        // use the position delete method
        Node<T> currentNode = head;
        int it = 0;
        while(currentNode != null){
            if(currentNode.data == element) {
                removeFromPosition(it);
                return true;
            }
            else{
                it++;
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAllElement(T element){
        if(!isExists(element)) return false;
        Node<T> currentNode = head;
        while(currentNode != null){
            if(currentNode.data == element){
                boolean status = deleteElement(element);
                if(status == false) return true;
            }
            else {
                currentNode = currentNode.next;
            }
        }
        return true;
    }

    @Override
    public boolean isExists(T data) {
        Node<T> currentNode = head;
        if(currentNode == null) return false;

        while(currentNode != null){
            if(Objects.equals(currentNode.data, data)) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean search(T data){
        return isExists(data);
    }

    @Override
    public boolean isEmpty() {
        return head == null || size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    // implement reverseList
    @Override
    public SinglyLinkedList<T> reverseList(){
        if(size <= 1) return this;
        Node<T> currentNode = head;
        Node<T> previousNode = null;
        while(currentNode != null){
            Node<T> nextNode = currentNode;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode.next;
        }
        head = previousNode;
        return this;
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

}
