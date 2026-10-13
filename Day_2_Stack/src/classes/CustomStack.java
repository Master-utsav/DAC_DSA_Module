package classes;

import interfaces.CustomStackInterface;

public class CustomStack<T> implements CustomStackInterface<T> {

    private T[] stack;
    int top = -1;
    int capacity = 16;
    int size = 0;

    @SuppressWarnings("unchecked")
    public CustomStack(){
        stack = (T[]) new Object[capacity];
    }

    public CustomStack(int capacity){
        this();
        this.capacity = capacity;
    }

    @Override
    public void push(T t) throws IndexOutOfBoundsException{
        if(top == capacity - 1) {
            resize(capacity * 2);
        }
        stack[++top] = t;
        size++;
    }

    @Override
    public T pop() throws IndexOutOfBoundsException{
        T temp = null;
        try {
            temp = peek();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        top--;
        size--;
        return temp;
    }

    @Override
    public T peek() throws IllegalAccessException {
        return stack[top];
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
            newArrayList[size++] = stack[i];

        stack = newArrayList;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == capacity - 1;
    }

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append("[");
        for(int i = size - 1; i >= 0; i--){
            strB.append(stack[i]).append(",");
        }
        strB.append("]");
        return strB.toString();
    }

}
