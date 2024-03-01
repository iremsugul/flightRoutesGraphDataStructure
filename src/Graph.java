import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private List<String> routes = new ArrayList<>();
    ArrayList<GraphNode> nodeList = new ArrayList<>();
    int[][] adjacencyMatrix;


    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        if (nodeList != null && nodeList.size() > 0) {
            adjacencyMatrix = new int[nodeList.size()+1][nodeList.size()+1];
        } else {
            System.out.println("Error: nodeList is empty or null");
        }
    }

    public void addDirectedEdge(GraphNode source, GraphNode destination){
        adjacencyMatrix[source.index][destination.index] = 1;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("   ");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + " ");
        }
        s.append("\n");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ": ");
            for (int j : adjacencyMatrix[i]) {
                s.append((j) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    public ArrayList<GraphNode> getNeighbors(GraphNode node) {
        ArrayList<GraphNode> neighbors = new ArrayList<>();
        int nodeIndex = node.index;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[nodeIndex][i] == 1) {
                neighbors.add(nodeList.get(i));
            }
        }
        return neighbors;
    }

    public int findDegree(int index){
        int degree =0;
        for(int i =0; i<nodeList.size(); i++){
            if(adjacencyMatrix[index][i]== 1){
                degree++;
            }
        }
        return degree;
    }

    public void printAllRoutes(String source, int hops, String routeSoFar) {
        if (hops == 0) {
            routes.add(routeSoFar.substring(0, routeSoFar.length() - 1));
            return;
        }
        for (GraphNode neighbor : getNeighbors(findNodeWithName(source, nodeList))) {
            if (findDegree(findNodeWithName(source, nodeList).index) >= hops) {
                printAllRoutes(neighbor.name, hops - 1, routeSoFar + neighbor.name +"-");

            } else {
                System.out.println("Source node's degree smaller than expected degree!");
            }

        }
    }

    public void printSortedRoutes() {
        System.out.println("Routes are:");
        Collections.sort(routes);
        for (String route : routes) {
            System.out.println(route);
        }
    }

    public void numberOfRoutes() {
        int count =0;
        Collections.sort(routes);
        for (String route : routes) {
            count++;
        }
        System.out.println("Number of total routes:" + count);
    }

    public GraphNode findNodeWithName(String name, ArrayList<GraphNode> nodeList){
        for(GraphNode node: nodeList){
            if(name.equalsIgnoreCase(node.name)){
                return node;
            }
        }
        return null;
    }

}



