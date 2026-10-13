public class CustomArray<T> implements CustomArrayInterface<T>{
    private T[] arrayList;
    private static int size = 0;
    private int capacity;

    @SuppressWarnings("unchecked")
    public CustomArray(int capacity){
        arrayList = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean add(T t) {
        if(size >= capacity) return false;
        arrayList[size++] = t;
        return true;
    }

    @Override
    public boolean add(T t, int pos) {
        if(size >= capacity) return false;
        if(pos == size) add(t);
        if(pos >= 0 || pos < size) {
            T temp = arrayList[size - 1];
            int it = size - 1;
            while (pos < it) {
                arrayList[it] = arrayList[it - 1];
                it--;
            }
            arrayList[pos] = t;
            add(temp);
        }
        return true;
    }

    @Override
    public T get(int idx) {
        if(idx < size && idx >= 0){
            return arrayList[idx];
        }
        else return null;
    }

    @Override
    public void remove(T t) {
        int prevSize = size;
        for(int i = 0; i < size - 1; i++){
            if(arrayList[i] == t){
                while(i < size - 1){
                    arrayList[i] = arrayList[i+1];
                    i++;
                }
                size--;
                break;
            }
        }
        if(prevSize == size && arrayList[size - 1] == t) {
            size --;
        }
    }

    @Override
    public boolean replace(T t, int pos) {
        if(pos < 0 || pos > size) return false;
        arrayList[pos] = t;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return capacity == size;
    }


    @Override
    public void resize(int newCapacity) {

        if(newCapacity <= capacity){
            return;
        }
        @SuppressWarnings("unchecked")
        T[] newArrayList = (T[]) new Object[newCapacity];
        int prevSize = size;
        size = 0;
        capacity = newCapacity;
        for(int i = 0; i < prevSize; i++)
            newArrayList[size++] = arrayList[i];

        arrayList = newArrayList;
    }

    @Override
    public int deleteFromPosition(int pos) {
        if(size == 0 || pos < 0 || pos > size) return size;
        if(pos != size - 1){
            int it = pos;
            while(it < size-1){
                T val = arrayList[it + 1];
                replace(val, it);
                size--;
                it++;
            }
        }
        if(pos == size - 1) size--;
        return size;

    }

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder("[");
        for(int i = 0; i < size; i++)
            strB.append(arrayList[i]).append(",");
        strB.append("]");
        return strB.toString();
    }

}
