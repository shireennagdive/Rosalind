package CompBioAssign2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructTrie {
    public static void main(String[] args) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/rosalind_ba9a.txt");
        File write_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/output_trieImplementation.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write_file));
        String temp_word;
        List<String> patterns = new ArrayList<>();
        while ((temp_word = bufferedReader.readLine()) != null) {
            patterns.add(temp_word);
        }
        List<String> result = constructTrie(patterns);
        for (String res : result) {
            bufferedWriter.write(res + "\n");
        }
        bufferedWriter.close();
    }

    private static List<String> constructTrie(List<String> patterns) {
        Trie trie = new Trie();
        for (String pattern : patterns) {
            trie.addNode(pattern);
        }
        return trie.outputTrie();
    }
}

class Trie {
    private Node root;
    private ArrayList<Node> nodes;
    private Map<Character, Integer> positionOfNode = new HashMap<>();


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
                nodes.add(currentNode.children[position]);
            }
            currentNode = currentNode.children[position];
        }
        return true;
    }

    public List<String> outputTrie() {
        List<String> result = new ArrayList<>();
        nodes.remove(0);
        for (Node current : nodes) {
            result.add(current.parent.nodeNumber + "->" + current.nodeNumber + ":" + current.value);
        }
        return result;
    }
}

class Node {
    private static int totalNodes = 0;
    public char value;
    public int nodeNumber;
    public Node parent;
    public Node[] children = new Node[4];

    public Node(char value, Node parent) {
        this.value = value;
        this.parent = parent;
        nodeNumber = totalNodes++;
    }
}
