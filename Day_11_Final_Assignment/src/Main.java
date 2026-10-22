import classes.DoublyLinkedList;
import classes.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>(Integer::compareTo);
        l1.add(23);
        l1.add(33);
        l1.add(13);
        l1.add(7);
        l1.add(12);
        l1.add(2);
        System.out.println(l1);
        System.out.println("***********************");

        LinkedList<Integer> l2 = new LinkedList<>(Integer::compareTo);
        l2.add(45);
        l2.add(56);
        l2.add(19);
        l2.add(11);
        l2.add(-12);
        l2.add(90);
        System.out.println(l2);
        System.out.println("***********************");
        l1.mergeTwoSortedLinkedList(l2);
        System.out.println(l1);
        System.out.println("***********************");



        DoublyLinkedList<Integer> dl1 = new DoublyLinkedList<>(Integer::compareTo);
        dl1.PairWiseAdd(1);
        dl1.PairWiseAdd(2);
        dl1.PairWiseAdd(3);
        dl1.PairWiseAdd(4);
        dl1.PairWiseAdd(5);
        System.out.println(dl1);
        System.out.println("***********************");


    }
}