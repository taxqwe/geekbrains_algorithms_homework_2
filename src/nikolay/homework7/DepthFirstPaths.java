package nikolay.homework7;

import java.util.LinkedList;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

        public DepthFirstPaths(CustomGraph g, int source) {
            if (source < 0)
                throw new IllegalArgumentException();
            if (source > g.getVertexCount() - 1)
                throw new IndexOutOfBoundsException();
            this.source = source;
            edgeTo = new int[g.getVertexCount()];
            marked = new boolean[g.getVertexCount()];
            dfs(g, source);
        }

        private void dfs(CustomGraph g, int v){
            marked[v] = true;
            for(int w : g.adjList(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(g, w);
                }
            }
        }

        public boolean hasPathTo(int dest) {
            if (dest < 0) {
                throw new IllegalArgumentException();
            }
            if (dest > marked.length) {
                throw new IndexOutOfBoundsException();
            }
            return marked[dest];
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
}
