import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2,3,4,5,6,7,8,9);
        SearchClass<Integer> searchClass = new SearchClass<>(list , Integer::compareTo);

        System.out.println(searchClass.linearSearch(10));
        System.out.println(searchClass.linearSearch(3));
        System.out.println("********************************");
        Collections.sort(list);
        System.out.println(searchClass.binarySearch(10));
        System.out.println(searchClass.binarySearch(3));

        System.out.println("********************************");
        System.out.println("********************************");

        List<String> list2 = Arrays.asList("Ak", "divya", "kajal", "palak", "tanvi", "utsav", "yashi");
        SearchClass<String> searchClass2 = new SearchClass<>(list2 , String::compareTo);

        System.out.println(searchClass2.linearSearch("utsavi"));
        System.out.println(searchClass2.linearSearch("samrudhhi"));
        System.out.println("********************************");
        Collections.sort(list2);
        System.out.println(searchClass2.binarySearch("divya"));
        System.out.println(searchClass2.binarySearch("utsav"));
    }
}