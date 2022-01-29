package GraphAlgos;
//Implementing the lazy version of Djikstra's algo, The eager would use D-ary Heaps
import java.util.*;
public class LazyDjikstras {
    private static double Epsilon = 1e-6;
    public static class Edge{
        double cost;
        int to,from;
    //Edge to represent a directed edge
        public Edge(double cost, int to, int from) {
            this.cost = cost;
            this.to = to;
            this.from = from;
        }

    }
    //Node class to keep a track of nodes in the algo
    public static class Node{
        int id;
        double value;

        public Node(int id, double value) {
            this.id = id;
            this.value = value;
        }
    }
    private int n;
    private  double[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;
    private Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if(Math.abs(o1.value-o2.value)<Epsilon)return 0;
            return (o1.value-o2.value)>0? 1:-1;
        }
    };
    public LazyDjikstras(int n){
        this.n = n;
        createEmptyGraph();
    }
    public LazyDjikstras(int n,Comparator<Node> comparator){
       this(n);
       if(comparator==null)throw new IllegalArgumentException("Comparator can't be null");
       this.comparator = comparator;
    }
    private void createEmptyGraph(){
        graph = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
    }
    public void addEdge(int from,int to,int cost){
        graph.get(from).add(new Edge(from,to,cost));
    }
    public double djikstra(int start,int end){
        dist = new double[n];
        Arrays.fill(dist,Double.POSITIVE_INFINITY);
        dist[start]=0;
        PriorityQueue<Node> pq = new PriorityQueue<>(2*n,comparator);
        pq.offer(new Node(start,0));
        boolean[] visited = new boolean[n];
        prev = new Integer[n];
        while(!pq.isEmpty()){
            Node node = pq.poll();
            visited[node.id]=true;
            if(dist[node.id]<node.value) continue;
            List<Edge> edges = graph.get(node.id);
            for(int i=0;i<edges.size();i++){
                Edge edge = edges.get(i);
                if(visited[edge.to]) continue;
                double NewDist= dist[edge.from]+ edge.cost;
                if(NewDist<dist[edge.to]){
                    prev[edge.to] = edge.from;
                    dist[edge.to] = NewDist;
                    pq.offer(new Node(edge.to,dist[edge.to]));

                }

            }

            if(node.id == end){
                return dist[end];
            }
        }
        return Double.POSITIVE_INFINITY;


    }
}
