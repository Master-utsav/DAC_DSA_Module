import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2,3,4,5,6,7,8,9);
        SearchClass<Integer> searchClass = new SearchClass<>(list , Integer::compareTo);
        System.out.println(searchClass.linearSearch(10));
        System.out.println(searchClass.linearSearch(-1));
        System.out.println(searchClass.linearSearch(3));
        System.out.println(searchClass.linearSearch(8));
        System.out.println("********************************");
        System.out.println(searchClass.binarySearch(10));
        System.out.println(searchClass.binarySearch(-1));
        System.out.println(searchClass.binarySearch(3));
        System.out.println(searchClass.binarySearch(8));
    }
}