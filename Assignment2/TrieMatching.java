import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieMatching {
    public static void main(String[] args) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/rosalind_trieMatching");
        File write_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/output_trieMatching.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write_file));

        String text = bufferedReader.readLine();

        List<String> patterns = new ArrayList<>();
        String temp_pattern;
        while ((temp_pattern = bufferedReader.readLine()) != null) {
            patterns.add(temp_pattern);
        }

        String result = getMatchedPositions(text, patterns);
        bufferedWriter.write(result);
        bufferedWriter.close();
    }

    public static String getMatchedPositions(String text, List<String> patterns) {
        Trie trie = constructTrie(patterns);
        List<Integer> positions = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int j, i = 0, childPosition;
        while (i < text.length()) {
            Node currentNode = trie.root;
            boolean nodeAdded = false;
            j = i;
            while (j < text.length()) {
                childPosition = trie.positionOfNode.get(text.charAt(j));
                if (currentNode.children[childPosition] == null) {
                    break;
                } else if (currentNode.totalChildren == 0) {
                    positions.add(i);
                    nodeAdded = true;
                    break;
                } else {
                    currentNode = currentNode.children[childPosition];
                }
                ++j;
            }
            if (currentNode != trie.root && !nodeAdded && currentNode.totalChildren == 0) {
                positions.add(i);
            }
            ++i;
        }
        for (Integer pos : positions) {
            result.append(pos.toString()).append(" ");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private static Trie constructTrie(List<String> patterns) {
        Trie trie = new Trie();
        for (String pattern : patterns) {
            trie.addNode(pattern);
        }
        return trie;
    }
}

class Trie {
    public Node root;
    private ArrayList<Node> nodes;
    public Map<Character, Integer> positionOfNode = new HashMap<>();


    public Trie() {
        root = new Node('\1', null);
        nodes = new ArrayList<>();
        nodes.add(root);
        positionOfNode.put('A', 0);
        positionOfNode.put('C', 1);
        positionOfNode.put('G', 2);
        positionOfNode.put('T', 3);
    }

    public boolean addNode(String word) {
        Node currentNode = root;
        char[] wordAsArray = word.toCharArray();
        int position;
        for (char w : wordAsArray) {
            position = positionOfNode.get(w);
            if (currentNode.children[position] == null) {
                currentNode.children[position] = new Node(w, currentNode);
                ++currentNode.totalChildren;
                nodes.add(currentNode.children[position]);
            }
            currentNode = currentNode.children[position];
        }
        return true;
    }
}

class Node {
    private static int totalNodes = 0;
    public char value;
    public int nodeNumber;
    public Node parent;
    public Node[] children = new Node[4];
    public int totalChildren;

    public Node(char value, Node parent) {
        this.value = value;
        this.parent = parent;
        nodeNumber = totalNodes++;
        totalChildren = 0;
    }
}
