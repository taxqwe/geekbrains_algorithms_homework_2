package nikolay;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class CustomArrayList<T> implements Iterable<T> {

    private int cursor = 0;

    private Object[] array = new Object[1];

    public CustomArrayList() {

    }

    public CustomArrayList(Object[] preInitializedArray) {
        array = Arrays.copyOf(preInitializedArray, preInitializedArray.length);
        cursor = array.length;
    }

    public void add(T item) {
        if (cursor == array.length) {
            resize(array.length * 2);
        }
        array[cursor] = item;
        cursor++;
    }

    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == -1) {
            return false;
        }

        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        cursor--;
        if (cursor == array.length / 4 && cursor != 0) {
            resize(array.length / 2);
        }
        return true;
    }


    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    public void set(int index, T item) {
        checkIndex(index);
        array[index] = item;
    }

    private void resize(int newSize) {
        array = Arrays.copyOf(array, newSize);
    }

    public int indexOf(T item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public int size() {
        return cursor;
    }

    private void checkIndex(int index) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object item : array) {
            if (item != null) {
                sb.append(((T) item).toString()).append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                return (T) array[cursor++];
            }
        };
    }

    /**
     * @param i index of first element for flipping
     * @param j index of second element for flipping
     */
    private void flip(int i, int j) {
        if (i >= array.length || j >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        if (i == j) {
            return;
        }

        Object foo = array[i];
        array[i] = array[j];
        array[j] = foo;
    }

    private boolean lessThan(T first, T second, Comparator comp) {
        return comp.compare(first, second) < 0;
    }

    public void selectionSorting(Comparator comparator) {
        for (int i = 0; i < cursor - 1; i++) {
            int min = i;
            for (int j = i + 1; j < cursor; j++) {
                if (lessThan((T) array[j], (T) array[min], comparator)) {
                    min = j;
                }
            }
            flip(i, min);
        }

    }

    public void instertionSorting(Comparator comparator) {
        for (int i = 0; i < cursor; i++) {
            for (int j = i; j > 0; j--) {
                if (lessThan((T) array[j], (T) array[j - 1], comparator)) {
                    flip(j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
