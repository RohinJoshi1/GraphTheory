package GraphAlgos;

import org.w3c.dom.Node;

import java.util.*;

public class MinShortestPathUsingTopSort{
	//top sort it and find min weights
	//Creating a class for adding edges
	static class Edge{
		int to,from,wt;
		public Edge(int to,int from,int wt){
			to = to;
			from = from;
			wt = wt;

		}

	}
	static int dfs(int i,int at,int[] ordering,Map<Integer,List<Edge>>graph,boolean[] visited){
		visited[at] = true;
		List<Edge> edges = graph.get(at);
		if(edges!=null){
			for(Edge edge:edges){
				if(!visited[edge.to]){
					i= dfs(i,edge.to,ordering,graph,visited);
				}
			}
			ordering[i]=at;
		}
		return i-1;
	}
	static int[] topsort(Map<Integer,List<Edge>>graph,int numnodes){
		int[] ordering = new int[numnodes];
		boolean[] visited = new boolean[numnodes];
		int i = numnodes-1;
		for(int at =0;i<numnodes;i++){
			if(!visited[at]){
				i= dfs(i,at,ordering,graph,visited);
			}
		}
		return ordering;
	}
	public static Integer[] DAGSP(Map<Integer, List<Edge>> graph,int start,int numnodes){
		int[] toposort = topsort(graph,numnodes);
		Integer[] dist = new Integer[numnodes];
		dist[start] = 0;
		for(int i=0;i<numnodes;i++){
			int NodeIndex = toposort[i];
			if(dist[NodeIndex]!=null){
				List<Edge> adjacentEdges = graph.get(NodeIndex);
				if(adjacentEdges!=null){
					for(Edge edge:adjacentEdges){
						int newdist = dist[NodeIndex] + edge.wt;
						if(dist[edge.to]==null){
							dist[edge.to]=newdist;
						}
						else{
							dist[edge.to]=Math.min(dist[edge.to],newdist);
						}
					}
				}
			}
		}
		return dist;
	}
	
}