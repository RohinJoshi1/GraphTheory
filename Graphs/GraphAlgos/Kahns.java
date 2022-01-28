package GraphAlgos;
import java.util.*;

    public class Kahns {

        // Given a an acyclic graph `g` represented as a adjacency list, return a
        // topological ordering on the nodes of the graph.
        public static int[] kahns(List<List<Integer>> g) {
            int n = g.size();

            // Calculate the in-degree of each node.
            int[] inDegree = new int[n];
            for (List<Integer> edges : g) {
                for (int to : edges) {
                    inDegree[to]++;
                }
            }

            // q always contains the set nodes with no incoming edges.
            Queue<Integer> q = new ArrayDeque<>();

            // Find all start nodes.
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }

            int index = 0;
            int[] order = new int[n];
            while (!q.isEmpty()) {
                int at = q.poll();
                order[index++] = at;
                for (int to : g.get(at)) {
                    inDegree[to]--;
                    if (inDegree[to] == 0) {
                        q.offer(to);
                    }
                }
            }
            if (index != n) {
                throw new IllegalArgumentException("Graph is not acyclic! Detected a cycle.");
            }
            return order;
        }

        // Example usage:
        public static void main(String[] args) {

            // test1();
            // test2();
            // cycleTest();
        }

       
}
