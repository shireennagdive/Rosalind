package CompBio;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    public static void main(String[] args) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBio/rosalind.txt");
        File write_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBio/output1.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write_file));
        List<String> inputs = new ArrayList<>();
        String temp_pattern;
        StringBuilder result = new StringBuilder();
        while ((temp_pattern = bufferedReader.readLine()) != null) {
            inputs.add(temp_pattern);
        }
        List<Integer> adjacencyList[]; //change

        int max = Integer.MIN_VALUE;

        for (String input : inputs) {
            String[] values = input.replaceAll("\\s", "").split("->");
            int source = Integer.valueOf(values[0]);
            if (source > max) {
                max = source;
            }
            String edgeNodes[] = values[1].split(",");
            for (String edge : edgeNodes) {
                if (Integer.valueOf(edge) > max) {
                    max = Integer.valueOf(edge);
                }
            }
        }

        adjacencyList = new List[max + 1];
        int[] indegree = new int[max + 1];

        for (String input : inputs) {
            String[] values = input.replaceAll("\\s", "").split("->");
            int source = Integer.valueOf(values[0]);
            String edgeNodes[] = values[1].split(",");
            adjacencyList[source] = new ArrayList<>();
            for (String edge : edgeNodes) {
                int target = Integer.valueOf(edge);
                adjacencyList[source].add(target);
                indegree[target]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < max + 1; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.append(node).append(", ");
            if (adjacencyList[node] != null) {
                for (int edgeNode : adjacencyList[node]) {
                    indegree[edgeNode]--;
                    if (indegree[edgeNode]==0) {
                        queue.offer(edgeNode);
                    }
                }
            }
        }

        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        System.out.println(result.toString());
    }

}

//1, 0, 2, 6, 3, 7, 4, 5, 18, 10, 11, 16, 8, 9, 13, 17, 19, 12, 14, 21, 15, 20, 24, 23, 22, 25 correct
//0, 1, 2, 6, 13, 16, 20, 22, 4, 5, 12, 17, 19, 23, 24, 25, 3, 7, 14, 18, 21, 10, 15, 11, 8, 9