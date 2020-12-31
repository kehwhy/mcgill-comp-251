package ca.mcgill.comp251.a3;
import java.util.*;



import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){ 
	  ArrayList<Integer> path = new ArrayList<Integer>();
	  path.add(source);
	  boolean[] isVisited = new boolean[graph.getNbNodes()];
	  path = pathDFSHelper(source, destination, graph, path, isVisited);
	  if (path.size() == 1)
	    return new ArrayList<Integer>();
	  return path;
	}
	
	public static ArrayList<Integer> pathDFSHelper(Integer source, Integer destination, WGraph graph, ArrayList<Integer> path, boolean[] isVisited ) {
	  
	  ArrayList<Edge> edges = graph.getEdges();
	  if (source == destination) {
	    return path;
	  }
	  
	  isVisited[source] = true;
	  
	  for(Edge e: edges) {
	    if (source.equals(e.nodes[0]) && !isVisited[e.nodes[1]] && e.weight > 0) {
	      path.add(e.nodes[1]);
	      path = pathDFSHelper(e.nodes[1], destination, graph, path, isVisited);
	      if (path.get(path.size()-1) == destination)
	        break;
	      path.remove(path.indexOf(e.nodes[1]));
	    }
	  }
	  
	  isVisited[source] = false;
	  return path;
	  
	}



	public static String fordfulkerson(WGraph graph){
		String answer="";
		int maxFlow = 0;
		WGraph workingGraph = new WGraph(graph);
		
		for (Edge e: workingGraph.getEdges())
		  e.weight = 0;
		
		WGraph residualGraph = buildResidualGraph(workingGraph, graph);
		
		ArrayList<Integer> path = pathDFS(workingGraph.getSource(), workingGraph.getDestination(), residualGraph);
		while (path.size() > 0) {
		  int bottleneck = calculateBottleneck(residualGraph, path);
		  
		  for (int i = 1; i < path.size(); i++) {
		    if (workingGraph.getEdge(path.get(i-1), path.get(i)) != null)
		      workingGraph.setEdge(path.get(i-1), path.get(i), workingGraph.getEdge(path.get(i-1), path.get(i)).weight + bottleneck);
		    else {
		      workingGraph.setEdge(path.get(i), path.get(i-1), workingGraph.getEdge(path.get(i), path.get(i-1)).weight - bottleneck);
		    }
		  }
		  
		  residualGraph = buildResidualGraph(workingGraph, graph);
		  path = pathDFS(workingGraph.getSource(), workingGraph.getDestination(), residualGraph);
		}
		
		maxFlow = findMaxFlow(workingGraph);

		answer += maxFlow + "\n" + workingGraph.toString();	
		return answer;
	}
	
    public static int findMaxFlow(WGraph graph) {
      ArrayList<Edge> edges = graph.getEdges();
      int total = 0;
      for (Edge e : edges) {
        if (e.nodes[0] == graph.getSource())
          total += e.weight;
      }
      return total;
    }
	
	public static WGraph buildResidualGraph(WGraph flowGraph, WGraph capacityGraph) {
	  WGraph residualGraph = new WGraph();
	  for (Edge e: flowGraph.getEdges()) {
	    Integer forward = capacityGraph.getEdge(e.nodes[0], e.nodes[1]).weight-e.weight;
	    Integer back = e.weight;
	    residualGraph.addEdge(new Edge(e.nodes[0], e.nodes[1], forward));
	    residualGraph.addEdge(new Edge(e.nodes[1], e.nodes[0], back));
	  }
	  return residualGraph;
	}
	
	public static int calculateBottleneck (WGraph residualGraph, ArrayList<Integer> path) {
	  int minDiff = Integer.MAX_VALUE;
	  for (int i = 1; i < path.size(); i++) {
        int diff = residualGraph.getEdge(path.get(i-1), path.get(i)).weight;
        minDiff = Math.min(minDiff, diff);
	  }
	  return minDiff;
	}
	

	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
	         System.out.println(fordfulkerson(g));
	 }
}

