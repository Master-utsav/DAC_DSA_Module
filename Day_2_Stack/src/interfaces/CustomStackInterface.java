package interfaces;/*
Implement a Stack class using resizable array that implements the Stack interface defined in class.
Throw appropriate exception when required.
 */

public interface CustomStackInterface<T> {
    public void push(T t) throws IndexOutOfBoundsException;
    public T pop() throws IndexOutOfBoundsException;
    public T peek() throws IllegalAccessException;
    default int capacity(){return 0;};
    public int size();
    default void resize(int capacity){return;}
    public boolean isEmpty();
    default boolean isFull(){return false;};
}
