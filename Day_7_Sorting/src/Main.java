import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Sorting<Integer> sorting = new Sorting<>(Integer::compareTo);

        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.BUBBLE_SORT, true);
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.BUBBLE_SORT, false);
        System.out.println("-------------------------------------------------------------------");
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.SELECTION_SORT, true);
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.SELECTION_SORT, false);
        System.out.println("-------------------------------------------------------------------");
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.INSERTION_SORT, true);
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.INSERTION_SORT, false);
        System.out.println("-------------------------------------------------------------------");
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.QUICK_SORT, true);
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.QUICK_SORT, false);
        System.out.println("-------------------------------------------------------------------");
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.MERGE_SORT, true);
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.MERGE_SORT, false);

        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.HEAP_SORT, true);
        sorting.sort(Arrays.asList(11,2,34,5,6,7,81,15), SortingTypes.HEAP_SORT, false);
    }
}