package nikolay.homework7;

import java.util.LinkedList;

public class BreadthFirstSearch {
    private boolean[] marked;
    protected int[] edgeTo;
    private int[] distTo;
    private int source;
    private int inf = Integer.MAX_VALUE;

    public BreadthFirstSearch(CustomGraph g, int source) {
        if (source < 0) {
            throw new IllegalArgumentException();
        }
        if (source > g.getVertexCount() - 1) {
            throw new IndexOutOfBoundsException();
        }
        this.source = source;
        edgeTo = new int[g.getVertexCount()];
        distTo = new int[g.getVertexCount()];
        marked = new boolean[g.getVertexCount()];
        for (int i = 0; i < g.getVertexCount(); i++) {
            distTo[i] = inf;
        }
        bfs(g, source);
    }

    private void bfs(CustomGraph g, int source) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked[source] = true;
        distTo[source] = 0;

        while (!queue.isEmpty()) {
            int currentVertex = queue.removeFirst();
            for (int w: g.adjList(currentVertex)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = currentVertex;
                    distTo[w] = distTo[currentVertex] + 1;
                    queue.addLast(w);
                }
            }
        }
    }

    public boolean hasPathTo(int dist) {
        if (dist < 0) {
            throw new IllegalArgumentException();
        }
        if (dist > marked.length) {
            throw new IndexOutOfBoundsException();
        }
        return marked[dist];
    }

    public LinkedList<Integer> pathTo(int dist) {
        if (!hasPathTo(dist)) {
            return null;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        int currentVertex = dist;
        while (currentVertex != source) {
            stack.push(currentVertex);
            currentVertex = edgeTo[currentVertex];
        }
        return stack;
    }

    public int distTo(int dist) {
        if (dist < 0) {
            throw new IllegalArgumentException();
        }
        if (dist > marked.length - 1) {
            throw new IndexOutOfBoundsException();
        }

        return distTo[dist];
    }
}
