package CompBio;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestPath {
    public static void main(String[] args) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBio/rosalind.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        List<String> inputs = new ArrayList<>();
        String temp_pattern;

        while ((temp_pattern = bufferedReader.readLine()) != null) {
            inputs.add(temp_pattern);
        }

        String[] dag = new String[inputs.size() - 2];
        int source = Integer.valueOf(inputs.get(0));
        int sink = Integer.valueOf(inputs.get(1));
        inputs.remove(0);
        inputs.remove(0);
        for (int i = 0; i < dag.length; i++) {
            dag[i] = inputs.get(i);
        }
        int[][] graph = createGraphWithCost(dag);
        ArrayList<Integer> longestPath = new ArrayList<>();
        longestPath.add(0);
        getLongestPath(graph, sink, source, 0, new ArrayList<>(), longestPath);
        System.out.println(longestPath.get(0));
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < longestPath.size(); ++i) {
            res.append(longestPath.get(i)).append("->");
        }
        System.out.println(res.deleteCharAt(res.length()-1).deleteCharAt(res.length()-1).toString());
    }

    public static void getLongestPath(int[][] graph, int sink, int curr, int cost, ArrayList<Integer> path, ArrayList<Integer> longestPath) {
        if (curr >= graph.length) {
            return;
        }
        path.add(curr);
        for (int i = 0; i < graph[curr].length; ++i) {
            if (graph[curr][i] != -1) {
                if (i == sink && (graph[curr][i] + cost > longestPath.get(0))) {
                    longestPath.clear();
                    longestPath.add(graph[curr][i] + cost);
                    longestPath.addAll(path);
                    longestPath.add(sink);
                } else if (i == sink) return;
                else getLongestPath(graph, sink, i, (cost + graph[curr][i]), path, longestPath);
            }
        }
        path.remove(path.size() - 1);
    }


    public static int[][] createGraphWithCost(String[] dag) {
        Arrays.sort(dag);
        int max = -1;
        for (int i = 0; i < dag.length; ++i) {
            String[] sourceAndSink = dag[i].split("->");
            int source = Integer.parseInt(sourceAndSink[0]);
            if (source > max) {
                max = source;
            }
        }
        int[][] graph = new int[max + 1][];

        int[] maxDestinations = new int[max + 1];
        for (int i = 0; i < dag.length; ++i) {
            String[] sourceAndSink = dag[i].split("->");
            int source = Integer.parseInt(sourceAndSink[0]);
            String[] destAndWeight = sourceAndSink[1].split(":");
            int dest = Integer.parseInt(destAndWeight[0]);
            if (dest > maxDestinations[source]) {
                maxDestinations[source] = dest;
            }
        }
        for (int i = 0; i < maxDestinations.length; ++i) {
            graph[i] = new int[maxDestinations[i] + 1];
            for (int j = 0; j < graph[i].length; ++j) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i < dag.length; ++i) {
            String[] sourceAndSink = dag[i].split("->");
            int source = Integer.parseInt(sourceAndSink[0]);
            String[] destAndWeight = sourceAndSink[1].split(":");
            int dest = Integer.parseInt(destAndWeight[0]);
            int cost = Integer.parseInt(destAndWeight[1]);
            graph[source][dest] = cost;
        }
        return graph;

    }
}
