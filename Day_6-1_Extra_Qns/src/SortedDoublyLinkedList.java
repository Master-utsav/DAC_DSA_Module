import java.util.Comparator;

public class SortedDoublyLinkedList<T> {
    class Node<T>{
        Node<T> next;
        Node<T> prev;
        T data;

        public Node(T data){
            next = null;
            prev = null;
            this.data = data;
        }
    }

    private Node<T> head = null;
    private int size = 0;
    private Comparator<T> comparator;
    private boolean desc = false;

    private SortedDoublyLinkedList(){};
    public SortedDoublyLinkedList(Comparator<T> comparator){
        this.comparator = comparator;
    }
    public SortedDoublyLinkedList(Comparator<T> comparator, boolean desc){
        this(comparator);
        this.desc = desc;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        // empty
        if(head == null){
            head = newNode;
            size++;
            return;
        }

        //if head data >= data then insert before head
        int cmp = comparator.compare(head.data, data);
        // insert at head;
        if(desc ? cmp < 0 : cmp >= 0){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }

        Node<T> currentNode = head;
        while(currentNode.next != null){
            cmp = comparator.compare(currentNode.next.data, data);
            if(desc ? cmp <= 0 : cmp > 0) break;
            currentNode = currentNode.next;
        }
        newNode.next = currentNode.next;
        newNode.prev = currentNode;
        currentNode.next = newNode;
        if(newNode.next != null)
            newNode.next.prev = newNode;
        size++;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
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

    public int size(){
        return size;
    }
}


// insert
//    public void add(T data){
//        Node<T> newNode = new Node<>(data);
//        // empty
//        if(head == null){
//            head = newNode;
//            tail = newNode;
//            size++;
//            return;
//        }
//
//        int it = 0;
//        Node<T> currentNode = head;
//        while(currentNode != null){
//            it++;
//            int cmp = comparator.compare(currentNode.data , data);
//            if(cmp >= 0 && it == 1){
//                newNode.next = head;
//                head.prev = newNode;
//                head = newNode;
//                size++;
//                break;
//            }
//            else if(cmp >= 0 && it == size){
//                newNode.prev = tail;
//                tail.next = newNode;
//                tail = newNode;
//                size++;
//                break;
//            }
//            else if(cmp >= 0){
//                newNode.next = currentNode;
//                currentNode.prev.next = newNode;
//                newNode.prev = currentNode.prev;
//                currentNode.prev = newNode;
//                size++;
//                break;
//            }
//            else{
//                currentNode = currentNode.next;
//            }
//        }
//    }
