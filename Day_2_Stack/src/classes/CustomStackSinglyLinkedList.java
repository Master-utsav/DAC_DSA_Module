package classes;

import interfaces.CustomStackInterface;
import list.List;
import list.SinglyLinkedList;

public class CustomStackSinglyLinkedList <T> implements CustomStackInterface<T> {
    List<T> list = new SinglyLinkedList<>();

    @Override
    public void push(T t) throws IndexOutOfBoundsException {
        list.addAtFirst(t);
    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        return list.removeFromFirst();
    }

    @Override
    public T peek() throws IllegalAccessException {
        T t = list.removeFromFirst();
        list.addAtFirst(t);
        return t;
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
