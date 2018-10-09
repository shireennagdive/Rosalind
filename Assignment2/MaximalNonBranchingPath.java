//Referenced from the algorithm mentioned in the Rosalind Probelm

package CompBioAssign2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximalNonBranchingPath {
    static List<List<Integer>> finalPaths = new ArrayList<>();
    static Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    static Map<Integer, Integer> inDegreeMap = new HashMap<>();
    static Map<Integer, Integer> outDegreeMap = new HashMap<>();

    public static void main(String args[]) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/rosalind_ba3m.txt");
        File write_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/output1.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write_file));
        List<String> inputs = new ArrayList<>();
        String temp_pattern;
        StringBuilder result = new StringBuilder();
        while ((temp_pattern = bufferedReader.readLine()) != null) {
            inputs.add(temp_pattern);
        }

        for (String input : inputs) {
            String[] values = input.replaceAll("\\s", "").split("->");
            String[] outNodes = values[1].split(",");
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < outNodes.length; i++) {
                list.add(Integer.valueOf(outNodes[i]));
            }
            adjacencyList.put(Integer.valueOf(values[0]), list);
        }
        in_and_out_degree();
        getAllNonBranchingPaths();

        for (List<Integer> path : finalPaths) {
            for (Integer node : path) {
                result.append(node + " -> ");
            }
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            result.append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        bufferedWriter.write(result.toString());
        bufferedWriter.close();
    }

    public static boolean get_is_one_in_one_out(int vertex) {
        return (inDegreeMap.containsKey(vertex) && inDegreeMap.get(vertex) == 1) && (outDegreeMap.containsKey(vertex) && outDegreeMap.get(vertex) == 1);
    }

    public static void in_and_out_degree() {
        for (Integer key : adjacencyList.keySet()) {
            outDegreeMap.put(key, adjacencyList.get(key).size());

            for (int value : adjacencyList.get(key)) {
                if (!inDegreeMap.containsKey(value)) {
                    inDegreeMap.put(value, 1);
                } else {
                    inDegreeMap.put(value, inDegreeMap.get(value) + 1);
                }
            }
        }
    }

    public static List<Integer> getIsolatedCycle(int vertex) {
        List<Integer> cycle = new ArrayList<>();
        cycle.add(vertex);
        List<Integer> cycle1 = new ArrayList<>();
        while (get_is_one_in_one_out(cycle.get(cycle.size() - 1))) {
            cycle.add(adjacencyList.get(cycle.get(cycle.size() - 1)).get(0));
            if (cycle.get(0).equals(cycle.get(cycle.size() - 1))) {
                return cycle;
            }
        }
        return cycle1;
    }

    public static List<Integer> getOneNonBranchingPath(int startNode, int middleNode) {
        List<Integer> path = new ArrayList<>();
        path.add(startNode);
        path.add(middleNode);
        while (get_is_one_in_one_out(path.get(path.size() - 1))) {
            path.add(adjacencyList.get(path.get(path.size() - 1)).get(0));
        }
        return path;
    }

    public static boolean visited(int vertex) {
        for (List<Integer> path : finalPaths) {
            if (path.contains(vertex)) {
                return true;
            }
        }
        return false;
    }


    public static void getAllNonBranchingPaths() {
        List<Integer> cycle;
        for (Integer key : adjacencyList.keySet()) {
            if (get_is_one_in_one_out(key)) {
                if (!visited(key)) {
                    cycle = getIsolatedCycle(key);
                    if (!cycle.isEmpty()) {
                        finalPaths.add(cycle);
                        System.out.println("aa");
                    }
                }
            } else {
                for (int outerNode : adjacencyList.get(key)) {
                    finalPaths.add(getOneNonBranchingPath(key, outerNode));
                }
            }

        }
    }

}
