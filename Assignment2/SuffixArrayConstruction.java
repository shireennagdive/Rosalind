package CompBioAssign2;

import java.io.*;
import java.util.TreeMap;

public class SuffixArrayConstruction {
    public static void main(String[] args) throws IOException {
        File read_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/rosalind_ba9g.txt");
        File write_file = new File("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBioAssign2/output.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(read_file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write_file));

        String text = bufferedReader.readLine();
        String result = getSuffixArray(text);
        bufferedWriter.write(result);
        bufferedWriter.close();

    }

    public static String getSuffixArray(String text) {
        TreeMap<String, Integer> suffixArray = new TreeMap<>();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            suffixArray.put(text.substring(i, length), i);
        }
        StringBuilder result = new StringBuilder();
        for (Object value : suffixArray.values()) {
            result.append(value).append(", ");
        }
        return new String(result).substring(0, result.length() - 2);
    }

}
