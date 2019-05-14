package nikolay.homework4.datatypes;

import java.util.NoSuchElementException;

public interface LinkedList<T> extends Iterable<T>{
    void addBefore(int index, T item);

    T remove(T item);

    T removeByIndex(int index);

    T get(int index);

    void set(int index, T item);

    int indexOf(T item);

    boolean contains(T item);

    int size();

    boolean isEmpty();

    default T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    void addFirst(T item);

    T removeFirst();

    T getLast();

    void addLast(T item);

    T removeLast();
}
