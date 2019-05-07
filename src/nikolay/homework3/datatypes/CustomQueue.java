package nikolay.homework3.datatypes;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CustomQueue<T> {

    private Object[] array = new Object[10];

    private int head = 0;

    private int tail = 0;

    private int size = 0;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }

        array[tail] = item;
        tail = (tail + 1) % array.length;
        size++;
    }

    private void resize(int newSize) {
        Object[] buff = new Object[newSize];
        for (int i = 0; i < size; i++) {
            buff[i] = array[(head + i) % array.length];
        }
        array = buff;
        head = 0;
        tail = size;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T buff = (T) array[head];
        array[head] = null;
        size--;
        head = (head + 1) % array.length;
        if (size == array.length/4 && size > 0) {
            resize(array.length/2);
        }
        return buff;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[head];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
