package CompBio;

import java.io.BufferedReader;
import java.io.FileReader;

class SuboptimalLocalAlignment {
    public static int distance(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) count++;
        }
        return count; }

    public static void main(String args[]) {
        String str[] = new String[2];
        str[0] = "";
        str[1] = "";
        String readLine;
        int i = -1;
        try {
            @SuppressWarnings("resource")
            BufferedReader buf = new BufferedReader(new FileReader("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBio/rosalind.txt"));
            while ((readLine = buf.readLine()) != null) {
                if (readLine.charAt(0) != '>') str[i] = str[i] + readLine;
                else if ((readLine.charAt(0) == '>'))
                    i++;
            }
        } catch (Exception e) {}

        String r = "ACGTAGCGTCGCAAGATAGCACCAACGATGAGGAGGT";
        int len_r = r.length();
        for (i = 0; i < 2; i++) {
            int count = 0;
            String curr = str[i];
            for (int j = 0; j < curr.length() - len_r; j++) {
                if (distance(curr.substring(j, j + len_r), r) <= 3) count++;
            }
            System.out.print(count + " ");
        }
    }
}
