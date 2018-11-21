package CompBio;

import java.io.BufferedReader;
import java.io.FileReader;

public class CountingOptimalAlignments {
    private static final int MOD = 134217727;

    public static void main(String[] args) {
        String read;
        String s[] = new String[2];
        s[0] = "";
        s[1] = "";
        int i, j;
        try {
            @SuppressWarnings("resource")
            BufferedReader br = new BufferedReader(new FileReader("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBio/rosalind.txt"));
            i = -1;
        } catch (Exception e) {}
        String str1 = s[0];
        String str2 = s[1];
        int[][] alignmentMatrix = new int[str1.length()][str2.length()];
        int[][] numberAlignmentsMatrix = new int[str1.length()][str2.length()];
        for (i = 0; i < str1.length(); i++) {
            for (j = 0; j < str2.length(); j++) {
                alignmentMatrix[i][j] = -1;
                numberAlignmentsMatrix[i][j] = -1;
            }
        }
        int optimalScore = getOptimalScore(str1, str2, str1.length() - 1, str2.length() - 1, alignmentMatrix);
        int total = getTotalAlignments(str1, str2, str1.length() - 1, str2.length() - 1, numberAlignmentsMatrix,
                alignmentMatrix, optimalScore);
        System.out.println(total);
    }

    public static int getTotalAlignments(String str1, String str2, int i, int j, int[][] numberAlignmentsMatrix,
                                                int[][] alignmentMatrix, int optimalScore) {
        if (i < 0 || j < 0) return 1;
        else if (numberAlignmentsMatrix[i][j] != -1) return numberAlignmentsMatrix[i][j];
        else {
            int total = 0;
            if (optimalScore - ((str1.charAt(i) == str2.charAt(j) ? 0 : 1)) == getAlignmentScore(alignmentMatrix, i - 1, j - 1)) {
                total+= getTotalAlignments(str1, str2, i - 1, j - 1,
                        numberAlignmentsMatrix, alignmentMatrix, optimalScore - (str1.charAt(i) == str2.charAt(j) ? 0 : 1)) % MOD;
            }

            if (optimalScore - 1 == getAlignmentScore(alignmentMatrix, i - 1, j)) {
                total+= getTotalAlignments(str1, str2, i - 1, j, numberAlignmentsMatrix,
                        alignmentMatrix, optimalScore - 1) % MOD;
            }

            if (optimalScore - 1 == getAlignmentScore(alignmentMatrix, i, j - 1)) {
                total+= getTotalAlignments(str1, str2, i, j - 1, numberAlignmentsMatrix,
                        alignmentMatrix, optimalScore - 1) % MOD;
            }
            numberAlignmentsMatrix[i][j] = total;
            return total;
        }
    }

    private static int getAlignmentScore(int[][] alignmentMatrix, int i, int j) {
        if (i < 0 && j < 0) return 0;
         else if (i < 0) return (j + 1);
         else if (j < 0) return (i + 1);
         else return alignmentMatrix[i][j];
    }

    public static int getOptimalScore(String str1, String str2, int i, int j, int[][] alignmentMatrix) {
        if (i < 0 && j < 0) return 0;
        else if (i < 0) return (j + 1);
        else if (j < 0) return (i + 1);
        else if (alignmentMatrix[i][j] != -1) return alignmentMatrix[i][j];
        else {
            alignmentMatrix[i][j] = Math.min((((str1.charAt(i) == str2.charAt(j) ? 0 : 1)) +
                            getOptimalScore(str1, str2, i - 1, j - 1, alignmentMatrix)),
                   1 + Math.min(getOptimalScore(str1, str2, i - 1, j, alignmentMatrix),
                            getOptimalScore(str1, str2, i, j - 1, alignmentMatrix)));
            return alignmentMatrix[i][j];
        }
    }
}