package nikolay.homework4.datatypes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomLinkedList<T> implements LinkedList<T> {

    int size = 0;

    Node first = null;

    Node last = null;

    @Override
    public T remove(T item) {
        Node foundNode = first;

        while (foundNode != null && !item.equals(foundNode.item)) {
            foundNode = foundNode.next;
        }

        if (isEmpty()) {
            return null;
        }

        if (item.equals(first.item)) {
            return removeFirst();
        }

        if (item.equals(last.item)) {
            return removeLast();
        }


        Node nextNode = foundNode.next;
        Node prevNode = foundNode.previous;

        prevNode.next = nextNode;
        nextNode.previous = prevNode;

        foundNode.previous = null;
        foundNode.next = null;

        size--;

        return foundNode.item;
    }

    @Override
    public T get(int index) {
        if(index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index <= size / 2) {
            Node currentNode = first;
            int currentIndex = 0;

            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }

            return currentNode.item;
        } else {
            Node currentNode = last;
            int currentIndex = size - 1;

            while (currentIndex > index) {
                currentNode = currentNode.previous;
                currentIndex--;
            }

            return currentNode.item;
        }
    }

    @Override
    public void set(int index, T item) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index <= size / 2) {
            Node currentNode = first;
            int currentIndex = 0;

            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            currentNode.item = item;
        } else {
            Node currentNode = last;
            int currentIndex = size - 1;

            while (currentIndex > index) {
                currentNode = currentNode.previous;
                currentIndex--;
            }
            currentNode.item = item;
        }
    }

    @Override
    public int indexOf(T item) {
        Node currentNode = first;
        int currentIndex = 0;

        while (!currentNode.item.equals(item) && currentNode != null) {
            currentNode = currentNode.next;
            currentIndex++;
            if (currentNode == null) {
                break;
            }
        }

        return currentNode == null? -1 : currentIndex;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        Node second = first.next;
        T item = first.item;
        first.next = null;
        first = second;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            second.previous = null;
        }
        return item;
    }

    @Override
    public T getLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.item;
    }

    @Override
    public void addFirst(T item) {
        Node oldFirst = first;
        first = new Node(null, item, oldFirst);
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        Node oldLast = last;
        last = new Node(oldLast, item, null);
        if(isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T lastItem = last.item;
        Node previous = last.previous;
        last.previous = null;

        last = previous;

        size--;

        if (!isEmpty()) {
            last.next = null;
        } else {
            first = null;
        }

        return lastItem;
    }

    @Override
    public void addBefore(int index, T item) {
        if (index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(item);
            return;
        }

        if (index == size) {
            addLast(item);
            return;
        }


        Node newNode = new Node(null, item, null);

        Node nodeInIndex = first;
        int nodeIndex = 0;

        while (index < nodeIndex++) {
            nodeInIndex = nodeInIndex.next;
        }


        Node prevNode = nodeInIndex.previous;

        prevNode.next = newNode;
        nodeInIndex.previous = newNode;
        newNode.previous = prevNode;
        newNode.next = nodeInIndex;
        size++;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    @Override
    public T removeByIndex(int index) {
        if (index == 0) {
            return removeFirst();
        }

        if (index == size) {
            return removeLast();
        }

        if (index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException();
        }

        Node nodeInIndex = first;
        int cursor = 0;

        while (index != cursor) {
            nodeInIndex = nodeInIndex.next;
            cursor++;
        }

        Node prev = nodeInIndex.previous;
        Node next = nodeInIndex.next;

        prev.next = next;
        next.previous = prev;

        size--;

        return nodeInIndex.item;
    }

    private class Node {
        private T item;
        private Node next;
        private Node previous;

        Node(Node previous, T data, Node next) {
            this.previous = previous;
            this.item = data;
            this.next = next;
        }
    }

    private class Iterator implements java.util.Iterator<T> {

        Node cursor = first;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = cursor.item;
            cursor = cursor.next;
            return item;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        Node node = first;
        while (node != null) {
            sb.append(node.item + " ");
            node = node.next;
        }
        return sb.toString();
    }
}
