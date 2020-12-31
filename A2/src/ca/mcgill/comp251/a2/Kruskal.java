package ca.mcgill.comp251.a2;
import java.util.*;

public class Kruskal{

  public static WGraph kruskal(WGraph g){

    DisjointSets set = new DisjointSets(g.getNbNodes());
    WGraph newGraph = new WGraph();
    ArrayList<Edge> sortedEdges = g.listOfEdgesSorted();

    for(Edge edge : sortedEdges) {
      if (IsSafe(set, edge)) {
        newGraph.addEdge(edge);
        set.union(edge.nodes[0], edge.nodes[1]);
      }
    } 

    return newGraph;
  }

  public static Boolean IsSafe(DisjointSets p, Edge e){
    return p.find(e.nodes[0]) != p.find(e.nodes[1]);    
  }

  public static void main(String[] args){

    String file = args[0];
    WGraph g = new WGraph(file);
    WGraph t = kruskal(g);
    System.out.println(t);

  } 
}
