package classes;

import interfaces.CustomQueueInterface;

public class CustomQueue<T> implements CustomQueueInterface<T> {

    private final T[] queue;
    private int size;
    private int capacity = 16;
    private int rear = -1;
    private int front = -1;

    @SuppressWarnings("unchecked")
    public CustomQueue(){
        queue = (T[]) new Object[capacity];
    }
    public CustomQueue(int capacity){
        this();
        this.capacity = capacity;
    }

    @Override
    public void enqueue(T t) throws IndexOutOfBoundsException {
        if(isFull()) return;
        queue[++rear] = t;
        size++;
    }

    @Override
    public T dequeue() throws IndexOutOfBoundsException {
        if(isEmpty()) return null;
        T temp = queue[++front];
        size--;
        return temp;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return rear == capacity - 1;
    }

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append("[");
        for(int i = 0; i < size(); i++){
            strB.append(queue[i]).append(",");
        }
        strB.append("]");
        return strB.toString();
    }
}
