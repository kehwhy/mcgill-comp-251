package ca.mcgill.comp251.a3;
import java.util.*;

public class BellmanFord{

    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    class BellmanFordException extends Exception{
        public BellmanFordException(String str){
            super(str);
        }
    }

    class NegativeWeightException extends BellmanFordException{
        public NegativeWeightException(String str){
            super(str);
        }
    }

    class PathDoesNotExistException extends BellmanFordException{
        public PathDoesNotExistException(String str){
            super(str);
        }
    }

    private boolean[] checked = null;
    private Stack<Integer> stack = new Stack<Integer>();
    int currentPath[] = new int[this.stack.size()];


    BellmanFord(WGraph g, int source) throws NegativeWeightException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         */
        int numberOfVertices = g.getNbNodes();
        ArrayList<Edge> edges = g.getEdges();
        
        this.distances = new int[numberOfVertices];
        this.predecessors = new int[numberOfVertices];
        this.source = source;

        for (int i = 0; i < numberOfVertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        

        distances[source] = 0;

        for (int i = 1; i < numberOfVertices; i++) {
            for (Edge e : edges) {
              relaxEdge(e);
            }
        }
        for (Edge e : edges) {
          checkForNegativeWeightCycles(e);
        }
    }
    
    public void checkForNegativeWeightCycles(Edge e) throws NegativeWeightException {
      if (distances[e.nodes[0]] != Integer.MAX_VALUE 
          && distances[e.nodes[0]] + e.weight < distances[e.nodes[1]]) {
          throw new NegativeWeightException("The graph contains a negative weight cycle.");
      }
    }
    
    public void relaxEdge(Edge e) {
      if (distances[e.nodes[0]] != Integer.MAX_VALUE 
          && distances[e.nodes[0]] + e.weight < distances[e.nodes[1]]) {
        distances[e.nodes[1]] = distances[e.nodes[0]] + e.weight;
        predecessors[e.nodes[1]] = e.nodes[0];
      }
    }

    public int[] shortestPath(int destination) throws PathDoesNotExistException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Error is thrown
         */

        if (distances[destination] == Integer.MAX_VALUE) {
            throw new PathDoesNotExistException("A path has not been found.");
        }

        int node = destination;
        Stack<Integer> stack = new Stack<Integer>();
        
        stack.push(node);
        while(node != this.source) {
            node = predecessors[node];
            stack.push(node);
        }

        int shortestPath[] = new int[stack.size()];
        for (int i = 0; i < shortestPath.length; i++) {
            shortestPath[i] = stack.pop();
        }

        return shortestPath;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}

