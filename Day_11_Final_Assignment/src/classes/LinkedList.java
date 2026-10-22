package classes;

import java.util.Comparator;

public class LinkedList<T> {

    class Node<T>{
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
            next = null;
        }
    }

    private Node<T> head;
    private Comparator<T> comparator;
    private boolean isAsc = true;
    private LinkedList(){};
    public  LinkedList(Comparator<T> comparator){
        this.comparator = comparator;
    }
    public LinkedList(Comparator<T> comparator, boolean isAsc){
        this(comparator);
        this.isAsc = isAsc;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node<T> currentNode = head;

        // insert at head
        int cmp = comparator.compare(currentNode.data, data);
        if(isAsc == cmp > 0){
            newNode.next = head;
            head = newNode;
            return;
        }
        // traverse and find a position
        while(currentNode.next != null){
            cmp = comparator.compare(currentNode.next.data, data);
            if(isAsc == cmp > 0) break;
            currentNode = currentNode.next;
        }
        // insert somewhere in middle
        if(currentNode.next != null){
            newNode.next = currentNode.next;
        }
        currentNode.next = newNode;

    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        if(head == null) return "[]";

        Node<T> currentNode = head;
        str.append("[");
        while(currentNode != null){
            str.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        str.append("]");

        return str.toString();
    }

    public void mergeTwoSortedLinkedList(LinkedList<T> l2){
        Node<T> currentNode = l2.head;
        while(currentNode != null){
            this.add(currentNode.data);
            currentNode = currentNode.next;
        }
    }


}
