package nikolay.homework7;

import java.util.LinkedList;

public class CustomGraph {
    private int vertexCount;
    private int edgeCount;
    private LinkedList<Integer>[] adjLists;

    public CustomGraph(int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException();
        }

        this.vertexCount = vertexCount;
        adjLists = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void addEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 > vertexCount - 1 || v2 > vertexCount - 1) {
            throw new IllegalArgumentException("Vertex number can not be negative.");
        }
        adjLists[v1].add(v2);
        adjLists[v2].add(v1);
        edgeCount++;
    }

    public LinkedList<Integer> adjList(int v) {
        if (vertexCount < 0 || v > vertexCount - 1) {
            throw new IllegalArgumentException("Vertex number can not be negative.");
        }
        return adjLists[v];
    }
}
