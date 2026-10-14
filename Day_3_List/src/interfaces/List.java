package interfaces;

public interface List<T> {

    public boolean addAtFirst(T data);
    public boolean addAtLast(T data);
    public boolean addAtPosition(T data, int position);

    public T removeFromFirst();
    public T removeFromLast();
    public T removeFromPosition(int position);

    public boolean isExists(T data);
    public boolean isEmpty();
    public int size();

    default boolean deleteElement(T element){
        return false;
    }
    default boolean deleteAllElement(T element){
        return false;
    }
    default boolean search(T data){
        return isExists(data);
    }
    default List<T> reverseList(){
        return this;
    }

}
