package classes;

import java.util.Comparator;

public class DoublyLinkedList<T> {
    class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data){
            this.data = data;
            next = null;
            prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private Comparator<T> comparator;
    int size = 0;
    private boolean isAsc = true;
    private DoublyLinkedList(){};
    public  DoublyLinkedList(Comparator<T> comparator){
        this.comparator = comparator;
    }
    public DoublyLinkedList(Comparator<T> comparator, boolean isAsc){
        this(comparator);
        this.isAsc = isAsc;
    }

    public void PairWiseAdd(T data){
        Node<T> newNode = new Node<>(data);

        if(head == null || tail == null){
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;

        // size == 2 case
        if(size == 2){
            System.out.println("size == 2 | head.data -> " + head.data + " tail.data -> " + tail.data);
            Node<T> temp = tail.prev;
            tail.next = temp;
            temp.prev = tail;
            head = tail;
            tail = temp;
            System.out.println("size == 2 | head.data -> " + head.data + " tail.data -> " + tail.data);
        }
        // else
        else if(size > 2 && size % 2 == 0){
            System.out.println("size > 2 | head.data -> " + head.data + " tail.data -> " + tail.data);
            Node<T> temp = tail.prev;
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
            System.out.println("size > 2 | head.data -> " + head.data + " tail.data -> " + tail.data);
        }

    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        if(head == null || tail == null) return "[]";

        Node<T> currentNode = head;
        str.append("[");
        while(currentNode != null){
            str.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        str.append("]");

        return str.toString();
    }
}
