package nikolay.homework8;

import java.time.temporal.ValueRange;

public class CustomChainingHashMap<K, V> {
    private int M = 7;
    private int size = 0;
    private Object[] st = new Object[M];
    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int h = hash(key);
        Node currentNode = (Node) st[h];
        while (currentNode != null) {
            if (key.equals(currentNode.key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int h = hash(key);
        Node currentNode = (Node) st[h];
        while (currentNode != null) {
            if (key.equals(currentNode.key)) {
                currentNode.value = value;
                return;
            }
            currentNode = currentNode.next;
        }
        st[h] = new Node(key, value, (Node) st[h]);
        size++;
    }
}
