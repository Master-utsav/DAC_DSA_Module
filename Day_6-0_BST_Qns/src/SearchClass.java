
import java.util.Comparator;
import java.util.List;

public class SearchClass<T> {

    private Comparator<T> comparator;
    private List<T> list;
    private SearchClass(){}
    public  SearchClass(List<T> list, Comparator<T> comparator){
        this.list = list;
        this.comparator = comparator;
    }

    public boolean linearSearch(T data){
        for(T value : list){
            int cmp = comparator.compare(value, data);
            if(cmp == 0) return  true;
        }
        return false;
    }

    public boolean binarySearch(T data){
        int left = 0;
        int right = list.size() - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int cmp = comparator.compare(list.get(mid), data);
            if(cmp == 0) return true;
            else if(cmp < 0) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }
}
