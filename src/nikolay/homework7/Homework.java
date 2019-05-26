package nikolay.homework7;

import org.junit.jupiter.api.Test;

public class Homework {
    @Test
    public void findWay() {
        CustomGraph graph = new CustomGraph(10);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(0, 5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 8);
        graph.addEdge(2, 9);
        graph.addEdge(2, 6);
        graph.addEdge(3, 2);
        graph.addEdge(4, 3);
        graph.addEdge(5, 7);
        graph.addEdge(5, 6);
        graph.addEdge(5, 2);

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 4);
        assert bfs.hasPathTo(8);
        assert bfs.pathTo(8).toString().equals("[3, 2, 8]");
    }
}
