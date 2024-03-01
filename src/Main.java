import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        ArrayList<GraphNode> cities = new ArrayList<>();
        Graph flightNetwork;

        try {
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();

            int hops = scanner.nextInt();
            scanner.nextLine();

            String sourceCity = scanner.nextLine();

            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            int index =0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String city1 = parts[0];
                String city2 = parts[1];

                GraphNode node1 = findGraphNode(city1, cities, null);
                GraphNode node2 = findGraphNode(city2, cities, null);

                if (node1 == null) {
                    node1 = new GraphNode(city1, index);
                    cities.add(node1);
                    index++;
                }

                if (node2 == null) {
                    node2 = new GraphNode(city2, index);
                    cities.add(node2);
                    index++;
                }
            }

            flightNetwork = new Graph(cities);
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String city1 = parts[0];
                String city2 = parts[1];

                GraphNode node1 = findGraphNode(city1, cities, null);
                GraphNode node2 = findGraphNode(city2, cities, null);

                flightNetwork.addDirectedEdge(node1, node2);
            }
            flightNetwork.printAllRoutes(sourceCity, hops, sourceCity + "-");
            flightNetwork.numberOfRoutes();
            flightNetwork.printSortedRoutes();
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static GraphNode findGraphNode(String cityName, ArrayList<GraphNode> cities, GraphNode defaultNode) {
        for (GraphNode node : cities) {
            if (node.name.equalsIgnoreCase(cityName)) {
                return node;
            }
        }
        return defaultNode;
    }

}
