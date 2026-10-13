package interfaces;/*
Q3. Define Queue as ADT (Refer to class notes).
Q4. Implement a Generic Queue class using array that implements the Queue interface defined in Q3.
Throw appropriate exception when required.
(Refer to class notes)

 */

public interface CustomQueueInterface<T> {
    public void enqueue(T t) throws IndexOutOfBoundsException;
    public T dequeue() throws IndexOutOfBoundsException;
    default int capacity(){
        return 0;
    };
    public int size();
    public boolean isEmpty();
    default boolean isFull(){
        return false;
    }
}
