package classes;

import interfaces.CustomQueueInterface;
import list.List;
import list.SinglyLinkedList;

public class CustomQueueSinglyLinkedList<T> implements CustomQueueInterface<T> {
    List<T> list = new SinglyLinkedList<>();

    @Override
    public void enqueue(T t) throws IndexOutOfBoundsException {
        list.addAtFirst(t);
    }

    @Override
    public T dequeue() throws IndexOutOfBoundsException {
        return list.removeFromLast();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
