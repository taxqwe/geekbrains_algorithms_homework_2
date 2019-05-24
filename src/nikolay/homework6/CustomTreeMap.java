package nikolay.homework6;

import java.util.NoSuchElementException;

public class CustomTreeMap<Key extends Comparable<Key>, Value> {
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        int size; // кол-во узлов в дереве, корнем которого является данный узел
        int height;

        public Node(Key key, Value value, int size, int height) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.height = height;
        }
    }

    private Node root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return root == null ? 0 : root.size;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public Value get(Key key) {
        return get(key, root);
    }

    public Value get(Key key, Node node) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (node == null) {
            return null;
        }

        Value result = null;

        switch (key.compareTo(node.key)) {
            case 0:
                result = node.value;
                break;
            case 1:
                result = get(key, node.right);
                break;
            case -1:
                result = get(key, node.left);
                break;
        }

        return result;
    }

    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (node == null) {
            return new Node(key, value, 1, 0 ); // todo height is constant???
        }

        int res = key.compareTo(node.key);
        if (res == 0) {
            node.value = value;
        } else if (res < 0) {
            node.left = put(key, value, node.left);
        } else {
            node.right = put(key, value, node.right);
        }

        node.size = size(node.left) + size(node.right) + 1;

        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left != null && node.right == null) {
            node.height = node.left.height + 1;
        } else if (node.left == null) {
            node.height = node.right.height + 1;
        } else if (node.left.height > node.right.height) {
            node.height = node.left.height + 1;
        } else if (node.left.height < node.right.height) {
            node.height = node.right.height + 1;
        } else {
            node.height = node.left.height + 1;
        }

        return node;
    }

    public Value min() {
        return min(root).value;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public Value max() {
        return max(root).value;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = removeMin(node.left);
        }
        node.size = size(node.left) + size(node.right) + 1;

        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left != null && node.right == null) {
            node.height = node.left.height + 1;
        } else if (node.left == null) {
            node.height = node.right.height + 1;
        } else if (node.left.height > node.right.height) {
            node.height = node.left.height + 1;
        } else if (node.left.height < node.right.height) {
            node.height = node.right.height + 1;
        } else {
            node.height = node.left.height + 1;
        }

        return node;
    }

    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMin(root);
    }

    public void removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMax(root);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            return node.left;
        } else {
            node.right = removeMax(node.right);
        }
        node.size = size(node.left) + size(node.right) + 1;

        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left != null && node.right == null) {
            node.height = node.left.height + 1;
        } else if (node.left == null) {
            node.height = node.right.height + 1;
        } else if (node.left.height > node.right.height) {
            node.height = node.left.height + 1;
        } else if (node.left.height < node.right.height) {
            node.height = node.right.height + 1;
        } else {
            node.height = node.left.height + 1;
        }
        return node;
    }

    public void remove(Key key) {
        root = remove(key, root);
    }

    private Node remove(Key key, Node node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            if (node.left == null) {
                return node.right;
            }
            if(node.right == null) {
                return node.left;
            }

            Node tmp = node;
            node = max(node.left);
            node.left = removeMax(tmp.left);
            node.right = tmp.right;
            tmp = null;
        } else if (cmp < 0) {
            node.left = remove(key, node.left);
        } else {
            node.right = remove(key, node.right);
        }

        node.size = size(node.left) + size(node.right) + 1;

        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left != null && node.right == null) {
            node.height = node.left.height + 1;
        } else if (node.left == null) {
            node.height = node.right.height + 1;
        } else if (node.left.height > node.right.height) {
            node.height = node.left.height + 1;
        } else if (node.left.height < node.right.height) {
            node.height = node.right.height + 1;
        } else {
            node.height = node.left.height + 1;
        }
        return node;
    }

    public boolean isBalanced() {
        if (root == null || root.size == 1) {
            return true;
        } else {

            int leftHeight;
            int rightHeight;

            if (root.left == null) {
                leftHeight = 0;
            } else {
                leftHeight = root.left.height;
            }

            if (root.right == null) {
                rightHeight = 0;
            } else {
                rightHeight = root.right.height;
            }

            return Math.abs(leftHeight - rightHeight) <= 1;
        }
    }

}
