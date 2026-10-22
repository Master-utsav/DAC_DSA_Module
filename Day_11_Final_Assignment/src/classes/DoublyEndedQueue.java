package classes;

public class DoublyEndedQueue<T> {

    private T[] queue;
    int capacity = 5;
    int front = -1;
    int rear = -1;
    int size = 0;

    @SuppressWarnings("unchecked")
    public DoublyEndedQueue(){
        queue = (T[]) new Object[capacity];
    }

    public void addFront(T data){
         if(front == size - 1) resize(capacity * 2);
         T temp = queue[front];
         for(int i = front + 1; i > 0; i--){
             queue[i] = queue[i - 1];
         }
         queue[0] = temp;
    }
    public void addEnd(T data){
        if(front ==  size - 1) resize(capacity * 2);
        queue[++front] = data;
    }

    public T deleteFront(T data){
        if(front ==  rear) return null;
        return queue[++rear];
    }
    public T deleteEnd(T data){
        if(front == rear) return null;
        T temp = queue[front];
        for(int i = front + 1; i > 0; i--){
            queue[i] = queue[i - 1];
        }
        queue[0] = temp;
    }


    public void resize(int newCapacity){
        if(newCapacity <= capacity){
            @SuppressWarnings("unchecked")
            T[] newArrayList = (T[]) new Object[newCapacity];
            int prevSize = size;
            size = 0;
            capacity = newCapacity;
            for(int i = 0; i < prevSize; i++)
                newArrayList[size++] = queue[i];

            queue = newArrayList;
        }
    }


}
