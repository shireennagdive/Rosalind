package Assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DeBruijn {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/rosalind_dbru.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String dna, kmer1;

        TreeSet<String> dnaStringsWithComplement = new TreeSet<>();
        TreeSet<String> complementList = new TreeSet<>();
        while ((dna = bufferedReader.readLine()) != null) {
            dnaStringsWithComplement.add(dna);
        }

        for (String dnaString : dnaStringsWithComplement) {
            String temp = getReverseComplement(dnaString);
            complementList.add(temp);
        }

        dnaStringsWithComplement.addAll(complementList);
        DeBruijn deBruijn = new DeBruijn();
        TreeMap<String, List<String>> deBruijnGraph = deBruijn.createDeBruijnGraph(dnaStringsWithComplement);

        for (Map.Entry<String, List<String>> entry : deBruijnGraph.entrySet()) {
            kmer1 = entry.getKey();
            if (entry.getValue().size() > 0) {
                for (String kmer2 : entry.getValue()) {
                    System.out.println("(" + kmer1 + ", " + kmer2 + ")");
                }
            }
        }

    }

    public static String getReverseComplement(String dna) {
        StringBuilder reverseComplement = new StringBuilder();

        char[] dnaArray = dna.toCharArray();
        int i = dna.length() - 1;
        while (i > -1) {
            if (dnaArray[i] == 'A') {
                reverseComplement.append('T');
            } else if (dnaArray[i] == 'T') {
                reverseComplement.append('A');

            } else if (dnaArray[i] == 'C') {
                reverseComplement.append('G');

            } else if (dnaArray[i] == 'G') {
                reverseComplement.append('C');
            }
            i--;
        }
        return new String(reverseComplement);
    }

    public TreeMap<String, List<String>> createDeBruijnGraph(Set<String> dnaStrings) {
        TreeMap<String, List<String>> graph = new TreeMap<>();
        List<String> temp;
        String kmer1, kmer2;
        for (String s : dnaStrings) {
            kmer1 = s.substring(0, s.length() - 1);
            kmer2 = s.substring(1);
            if (!graph.containsKey(kmer1)) {
                graph.put(kmer1, new ArrayList<>());
            }
            if (!graph.containsKey(kmer2)) {
                graph.put(kmer2, new ArrayList<>());
            }
            temp = graph.get(kmer1);
            temp.add(kmer2);
        }
        return graph;
    }


}