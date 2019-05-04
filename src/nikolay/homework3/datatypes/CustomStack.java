package nikolay.homework3.datatypes;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CustomStack<T> {

    private int cursor = 0;

    private Object[] array = new Object[10];

    public void push(T item) {
        if (cursor == array.length) {
            resize(array.length * 2);
        }

        array[cursor++] = item;
    }

    public T pop() {
        if (--cursor < 0) {
            throw new IndexOutOfBoundsException();
        }

        T buff = (T) array[cursor];
        array[cursor] = null;

        if (cursor == array.length / 4 && cursor > 0) {
            resize(array.length / 2);
        }

        return buff;
    }

    public T peek() {
        if (cursor == 0) {
            throw new NoSuchElementException();
        }

        return (T) array[cursor];
    }

    public boolean isEmpty() {
        return cursor == 0;
    }

    public int size() {
        return cursor;
    }

    private void resize(int newSize) {
        array = Arrays.copyOf(array, newSize);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
