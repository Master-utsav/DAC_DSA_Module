import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        SortedDoublyLinkedList<Integer> intSortedListInteger = new SortedDoublyLinkedList<>(Integer::compareTo);
        SortedDoublyLinkedList<Integer> intSortedListInteger = new SortedDoublyLinkedList<>(Integer::compareTo, true);

        intSortedListInteger.add(9);
        intSortedListInteger.add(7);
        intSortedListInteger.add(6);
        intSortedListInteger.add(2);
        intSortedListInteger.add(4);
        intSortedListInteger.add(5);

        System.out.println(intSortedListInteger);

//        SortedDoublyLinkedList<String> intSortedListString = new SortedDoublyLinkedList<>(String::compareTo);
        SortedDoublyLinkedList<String> intSortedListString = new SortedDoublyLinkedList<>(String::compareTo, true);
        intSortedListString.add("Utsav");
        intSortedListString.add("Kunal");
        intSortedListString.add("Urmila");
        intSortedListString.add("Abhay");
        intSortedListString.add("Ramesh");

        System.out.println(intSortedListString);
        System.out.println("*********************");

//    Example 1:
//    Input: ["2","1","+","3","*"]
//    Output: 9
        List<String> exp1 = Arrays.asList("2","1","+","3","*");
        int ans = PostFixArithmetics.calculate(exp1);
        if(ans == 0){
            System.out.println("Error");
        }
        else{
            System.out.println("value is : " + ans);
        }


//    Example 2:
//    Input: = ["4","13","5","/","+"]
//    Output: 6
        List<String> exp2 = Arrays.asList("4","13","5","/","+");
        int ans2 = PostFixArithmetics.calculate(exp2);
        if(ans2 == 0){
            System.out.println("Error");
        }
        else{
            System.out.println("value is : " + ans2);
        }

//    Example 3:
//    Input:= ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//    Output: 22
        List<String> exp3 = Arrays.asList("10","6","9","3","+","-11","*","/","*","17","+","5","+");
        int ans3 = PostFixArithmetics.calculate(exp3);
        if(ans3 == 0){
            System.out.println("Error");
        }
        else{
            System.out.println("value is : " + ans3);
        }


    }
}