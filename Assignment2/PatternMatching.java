package CompBioAssign2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatternMatching {
    public static void main(String[] args) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/sample.txt");
        File write_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/output.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write_file));
        String genome = bufferedReader.readLine();

        List<String> patterns = new ArrayList<>();
        String temp_pattern;
        while ((temp_pattern = bufferedReader.readLine()) != null) {
            patterns.add(temp_pattern);
        }
        String result = patternMatch(patterns, genome);
        bufferedWriter.write(result);
        bufferedWriter.close();

    }

    public static String patternMatch(List<String> patterns, String genome) {
        StringBuilder result = new StringBuilder();
        String genomeSubstring;
        List<Integer> positions = new ArrayList<>();
        int genomeLength = genome.length();
        for (String pattern : patterns) {
            for (int i = 0; i <= genomeLength - pattern.length(); i++) {
                genomeSubstring = genome.substring(i, i + pattern.length());
                if (genomeSubstring.equals(pattern)) {
                    positions.add(i);
                }
            }
        }
        Collections.sort(positions);
        for(Integer position : positions){
            result.append(position).append(" ");
        }
        return new String(result).substring(0, result.length() - 1);
    }
}
