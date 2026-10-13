public interface CustomArrayInterface<T> {
    public boolean add(T t);
    public boolean add(T t, int pos);
    public T get(int idx);
    public void remove(T t);
    public boolean replace(T t, int pos);
    public int size();
    public int capacity();
    public boolean isEmpty();
    public boolean isFull();
    public void resize(int newCapacity);
    // assignment question 1
    /*
    Q1. Implement following function to delete element from specified position in given array.
    The function should return the updated logical size of array.
    int deleteFromPosition(int[] arr, int n, int pos);
     */
    public int deleteFromPosition(int pos);
}
