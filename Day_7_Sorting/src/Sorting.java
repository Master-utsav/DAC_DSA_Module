import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class Sorting<T> {

    private Comparator<T> comparator;
    private Sorting(){}
    public Sorting(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public void sort(List<T> list, SortingTypes sortType, boolean isAsc){
        switch (sortType){
            case SortingTypes.BUBBLE_SORT -> {
                bubbleSort(list, isAsc);
            }
            case SortingTypes.SELECTION_SORT -> {
              selectionSort(list, isAsc);
            }
            case SortingTypes.INSERTION_SORT -> {
               insertionSort(list, isAsc);
            }
            case SortingTypes.QUICK_SORT -> {
               quickSort(list, isAsc);
            }
            case SortingTypes.MERGE_SORT -> {
               mergeSort(list, isAsc);
            }
            case SortingTypes.HEAP_SORT -> {
                System.out.println("no implemented yet");
            }
            default -> {
                System.out.println("No such SortType Found...");
            }
        }
    }

    private void bubbleSort(List<T> list, boolean isAsc){
        System.out.println("Before applying bubble sort = " + list);
        int count = 0;
        int swapCount = 0;
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size() - i - 1; j++){
                count++;
                int cmp = comparator.compare(list.get(j), list.get(j+1));
                if(isAsc == (cmp > 0)){
                    swapCount++;
                    T temp = list.get(j+1);
                    list.set(j+1, list.get(j));
                    list.set(j , temp);
                }
            }
            if(swapCount == 0) break;
        }
        System.out.println("After applying bubble sort " + (isAsc ? "Ascending" : "Descending") + " = " + list);
        System.out.println("Total Iterations = " + count + "  ||  Swap Count = " + swapCount);
    }

    private void selectionSort(List<T> list, boolean isAsc){
        System.out.println("Before applying selection sort = " + list);
        int count = 0;
        int swapCount = 0;

        for(int i = 0; i < list.size(); i++){
            int minIndex = i;
            T minElement = list.get(i);
            for(int j = i+1; j < list.size(); j++){
                count++;
                int cmp = comparator.compare(minElement, list.get(j));
                if(isAsc == cmp > 0){
                    minIndex = j;
                    minElement = list.get(j);
                }
            }
            if(minIndex != i){
                swapCount++;
                T temp = list.get(i);
                list.set(i, minElement);
                list.set(minIndex , temp);
            }
        }
        System.out.println("After applying selection sort " + (isAsc ? "Ascending" : "Descending") + " = " + list);
        System.out.println("Total Iterations = " + count + "  ||  Swap Count = " + swapCount);
    }

    private void insertionSort(List<T> list, boolean isAsc){
        System.out.println("Before applying insertion sort = " + list);
        int sortedCount = 1;
        int count = 0;
        int swapCount = 0;

        while(sortedCount < list.size()){
            int lastIndex = sortedCount - 1;
            T element = list.get(sortedCount);
            while(lastIndex >= 0){
                count++;
                int cmp = comparator.compare(list.get(lastIndex), element);
                if(isAsc == (cmp > 0)){
                    swapCount++;
                    list.set(lastIndex + 1, list.get(lastIndex));
                }
                else break;
                lastIndex--;
            }
            list.set(lastIndex + 1, element);
            sortedCount++;
        }
        System.out.println("After applying insertion sort " + (isAsc ? "Ascending" : "Descending") + " = " + list);
        System.out.println("Total Iterations = " + count + "  ||  Swap Count = " + swapCount);
    }


    private void quickSort(List<T> list, boolean isAsc){
        System.out.println("Before applying Quick sort = " + list);
        quickSortAlgo(list,0, list.size() - 1, isAsc);
        System.out.println("After applying Quick sort " + (isAsc ? "Ascending" : "Descending") + " = " + list);
//        System.out.println("Total Iterations = " + count + "  ||  Swap Count = " + swapCount);
    }
    private void quickSortAlgo(List<T> list, int start, int end, boolean isAsc){
        if((end - start + 1) <= 1) return;

        int pivot = (int) (Math.random() * (end));
        while(pivot < start){
            pivot = (int) (Math.random() * (end));
        }

        int partitionIndex = partition(list, pivot, start, end, isAsc);
        quickSortAlgo(list, start, partitionIndex - 1, isAsc);
        quickSortAlgo(list, partitionIndex + 1, end, isAsc);

    }
    private int partition(List<T> list, int pivotIndex, int start, int end, boolean isAsc){
        T pivotElement = list.get(pivotIndex);
        int left = start;
        int right = end;

        while(left <= right){
            while(left <= right && (isAsc == (comparator.compare(list.get(left), pivotElement) <= 0))){
                left++;
            }
            while(left <= right && (isAsc == (comparator.compare(list.get(right), pivotElement) > 0))){
                right--;
            }
            if(left < right && (isAsc == comparator.compare(list.get(left), list.get(right)) >= 0)){
                if(comparator.compare(list.get(left), pivotElement) == 0) pivotIndex = right;
                if(comparator.compare(list.get(right), pivotElement) == 0) pivotIndex = left;
                T temp = list.get(right);
                list.set(right, list.get(left));
                list.set(left, temp);
            }
        }
        T temp = isAsc ? list.get(right) : list.get(left);
        list.set(isAsc ? right : left, pivotElement);
        list.set(pivotIndex, temp);
        return isAsc ? right : left;
    }

    private void mergeSort(List<T> list, boolean isAsc){
        System.out.println("Before applying merge sort = " + list);
        mergeSortAlgo(list, 0, list.size() - 1, isAsc);
        System.out.println("After applying merge sort " + (isAsc ? "Ascending" : "Descending") + " = " + list);
    }
    private void mergeSortAlgo(List<T> list, int start, int end, boolean isAsc){
        if((end - start + 1) <= 1) return;

        int mid = start + (end - start) / 2;
        mergeSortAlgo(list, start, mid, isAsc);
        mergeSortAlgo(list, mid + 1, end, isAsc);
        merge(list, start, mid, end, isAsc);
    }
    private void merge(List<T> list, int start, int mid, int end, boolean isAsc){
        int firstSubArrayStart = start;
        int firstSubArrayEnd = mid;
        int secondSubArrayStart = mid + 1;
        int secondSubArrayEnd = end;

        List<T> newArray = new ArrayList<>();
        int iPointer = firstSubArrayStart;
        int jPointer = secondSubArrayStart;

        while(iPointer <= firstSubArrayEnd && jPointer <= secondSubArrayEnd){
            while(iPointer <= firstSubArrayEnd && (isAsc == comparator.compare(list.get(iPointer), list.get(jPointer)) <= 0)){
                newArray.add(list.get(iPointer));
                iPointer++;
            }
            while(jPointer <= secondSubArrayEnd && (isAsc == comparator.compare(list.get(iPointer), list.get(jPointer)) > 0)){
                newArray.add(list.get(jPointer));
                jPointer++;
            }
        }

        while(iPointer <= firstSubArrayEnd){
            newArray.add(list.get(iPointer));
            iPointer++;
        }
        while(jPointer <= secondSubArrayEnd){
            newArray.add(list.get(jPointer));
            jPointer++;
        }

        int tempIndex = 0;
        for(int i = start; i <= end; i++){
            if(tempIndex > newArray.size()) break;
            list.set(i, newArray.get(tempIndex));
            tempIndex++;
        }

    }



}
