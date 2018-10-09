//Referenced Fleury's algorithm from https://en.wikipedia.org/wiki/Eulerian_path
package CompBioAssign2;

import java.io.*;
import java.util.*;

public class EulerianPath {
    static ArrayList<Integer> sources = new ArrayList<>();
    static ArrayList<Integer> destinations = new ArrayList<>();
    static ArrayList<Integer> oddNumberedNodes = new ArrayList<>();
    static Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    static List<Integer> finalPath = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File input_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/rosalind_ba3g.txt");
        File output_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/output_eulerianPath.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(input_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output_file));

        List<String> edges = new ArrayList<>();
        String temp_edge;
        while ((temp_edge = bufferedReader.readLine()) != null) {
            edges.add(temp_edge);
        }
        int i = 0, destInt;

        String temp_edge_array[];
        String temp_dest[];
        Set<Integer> nodesInGraph = new HashSet<>();
        while (i < edges.size()) {
            temp_edge_array = edges.get(i).split(" -> ");
            temp_dest = temp_edge_array[1].split("\\,");
            int source = Integer.valueOf(temp_edge_array[0]);
            for (String ignored : temp_dest) {
                sources.add(source);
            }
            nodesInGraph.add(source);
            for (String dest : temp_dest) {
                destInt = Integer.valueOf(dest);
                destinations.add(destInt);
                nodesInGraph.add(Integer.valueOf(dest));
                if (!adjacencyList.containsKey(source)) {
                    List<Integer> temp_destinations = new ArrayList<>();
                    temp_destinations.add(destInt);
                    adjacencyList.put(source, temp_destinations);
                } else {
                    List<Integer> temp_destinations = adjacencyList.get(source);
                    temp_destinations.add(destInt);
                    adjacencyList.put(source, temp_destinations);
                }

            }
            i++;
        }
        for (int node : nodesInGraph) {
            if (isTotalDegreeOdd(node)) {
                oddNumberedNodes.add(node);
            }
        }
        int firstNode = -1;
        if (oddNumberedNodes != null && oddNumberedNodes.size() > 0) {
            firstNode = oddNumberedNodes.get(0);
        }
        if (oddNumberedNodes != null) {
            for (int oddNode : oddNumberedNodes) {
                if (sources.contains(oddNode)) {
                    firstNode = oddNode;
                }
            }
        }

        if (firstNode == -1) {
            firstNode = sources.get(0);
        }
        eulerianPath(firstNode);
        StringBuilder result = new StringBuilder();
        result.append(firstNode);

        for (i = finalPath.size() - 1; i >= 0; i--) {
            result.append("->").append(finalPath.get(i));
        }
        System.out.println(result.toString());
        bufferedWriter.write(result.toString());
        bufferedWriter.close();
    }

    public static boolean isTotalDegreeOdd(int node) {
        int countSource = 0, countDestination = 0;
        for (int source : sources) {
            if (source == node) {
                countSource++;
            }
        }
        for (int destination : destinations) {
            if (destination == node) {
                countDestination++;
            }
        }
        return (countSource + countDestination) % 2 != 0;
    }

    public static void eulerianPath(int firstNode) {
        Stack<Integer> nodes = new Stack<>();
        nodes.push(firstNode);
        int adjacentNode = adjacencyList.get(firstNode).get(0);
        for (int i = 0; i < adjacencyList.get(firstNode).size(); i++) {
            if (adjacencyList.get(firstNode).get(i) == adjacentNode) {
                adjacencyList.get(firstNode).remove(i);
            }
        }
        int presentNode = adjacentNode;
        while (0 == 0) {
            if (nodes.empty() && (adjacencyList.get(presentNode).size() == 0)) {
                break;
            }
            if (adjacencyList.containsKey(presentNode) && adjacencyList.get(presentNode).size() != 0) {
                nodes.push(presentNode);
                adjacentNode = adjacencyList.get(presentNode).get(0);
                for (int i = 0; i < adjacencyList.get(presentNode).size(); i++) {
                    if (adjacencyList.get(presentNode).get(i) == adjacentNode) {
                        adjacencyList.get(presentNode).remove(i);
                    }
                }
                presentNode = adjacentNode;
            } else {
                finalPath.add(presentNode);
                presentNode = nodes.pop();
            }
        }
    }
}
