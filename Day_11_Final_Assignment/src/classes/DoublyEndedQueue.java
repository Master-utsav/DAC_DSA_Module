package classes;

public class DoublyEndedQueue<T> {

    private T[] queue;
    private int capacity = 5;
    private int front = -1;
    private int rear = -1;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public DoublyEndedQueue() {
        queue = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void addFront(T data) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }

        queue[front] = data;
        size++;
    }

    public void addEnd(T data) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = data;
        size++;
    }

    public T deleteFront() {
        if (isEmpty()) {
            return null;
        }

        T data = queue[front];
        queue[front] = null; // Prevent memory leak

        if (front == rear) { // Revert to initial state when empty
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        size--;
        return data;
    }

    public T deleteEnd() {
        if (isEmpty()) {
            return null;
        }

        T data = queue[rear];
        queue[rear] = null; // Prevent memory leak

        if (front == rear) { // Revert to initial state when empty
            front = -1;
            rear = -1;
        } else {
            rear = (rear - 1 + capacity) % capacity;
        }

        size--;
        return data;
    }

    public T peekFront() {
        if (isEmpty()) return null;
        return queue[front];
    }

    public T peekEnd() {
        if (isEmpty()) return null;
        return queue[rear];
    }

    @SuppressWarnings("unchecked")
    public void resize(int newCapacity) {
        if (newCapacity <= capacity) return;

        T[] newQueue = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }

        queue = newQueue;
        capacity = newCapacity;
        front = 0;
        rear = size - 1;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}