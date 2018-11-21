package CompBio;

public class GapPenalities {
    public static String aminos = "ACDEFGHIKLMNPQRSTVWY";
    static int[][] blosum62 = {{4, 0, -2, -1, -2, 0, -2, -1, -1, -1, -1, -2, -1, -1, -1, 1, 0, 0, -3, -2}, {0, 9, -3, -4, -2, -3, -3, -1, -3, -1, -1, -3, -3, -3, -3, -1, -1, -1, -2, -2}, {-2, -3, 6, 2, -3, -1, -1, -3, -1, -4, -3, 1, -1, 0, -2, 0, -1, -3, -4, -3}, {-1, -4, 2, 5, -3, -2, 0, -3, 1, -3, -2, 0, -1, 2, 0, 0, -1, -2, -3, -2}, {-2, -2, -3, -3, 6, -3, -1, 0, -3, 0, 0, -3, -4, -3, -3, -2, -2, -1, 1, 3}, {0, -3, -1, -2, -3, 6, -2, -4, -2, -4, -3, 0, -2, -2, -2, 0, -2, -3, -2, -3}, {-2, -3, -1, 0, -1, -2, 8, -3, -1, -3, -2, 1, -2, 0, 0, -1, -2, -3, -2, 2}, {-1, -1, -3, -3, 0, -4, -3, 4, -3, 2, 1, -3, -3, -3, -3, -2, -1, 3, -3, -1}, {-1, -3, -1, 1, -3, -2, -1, -3, 5, -2, -1, 0, -1, 1, 2, 0, -1, -2, -3, -2}, {-1, -1, -4, -3, 0, -4, -3, 2, -2, 4, 2, -3, -3, -2, -2, -2, -1, 1, -2, -1}, {-1, -1, -3, -2, 0, -3, -2, 1, -1, 2, 5, -2, -2, 0, -1, -1, -1, 1, -1, -1}, {-2, -3, 1, 0, -3, 0, 1, -3, 0, -3, -2, 6, -2, 0, 0, 1, 0, -3, -4, -2}, {-1, -3, -1, -1, -4, -2, -2, -3, -1, -3, -2, -2, 7, -1, -2, -1, -1, -2, -4, -3}, {-1, -3, 0, 2, -3, -2, 0, -3, 1, -2, 0, 0, -1, 5, 1, 0, -1, -2, -2, -1}, {-1, -3, -2, 0, -3, -2, 0, -3, 2, -2, -1, 0, -2, 1, 5, -1, -1, -3, -3, -2}, {1, -1, 0, 0, -2, 0, -1, -2, 0, -2, -1, 1, -1, 0, -1, 4, 1, -2, -3, -2}, {0, -1, -1, -1, -2, -2, -2, -1, -1, -1, -1, 0, -1, -1, -1, 1, 5, 0, -2, -2}, {0, -1, -3, -2, -1, -3, -3, 3, -2, 1, 1, -3, -2, -2, -3, -2, 0, 4, -3, -1}, {-3, -2, -4, -3, 1, -2, -2, -3, -3, -2, -1, -4, -4, -2, -3, -3, -2, -3, 11, 2}, {-2, -2, -3, -2, 3, -3, 2, -1, -2, -1, -1, -2, -3, -1, -2, -2, -2, -1, 2, 7}};
    public static int gapOpeningPenality = 11;
    public static int gapExtensionPenality = 1;

    public static void main(String[] args) {

        String v = "VEDEPHCFHGKTHKKFDPQEGIEDTVMMDHWTRMFWDKPAPAWMDWTWPCMAMNLAGEMTEAWIRFPYKFIHFVCVINKFSDDHI";
        String w = "VEDEHIFSCMSMSCFHGKTVNKKLMVMMDHDPAWMDWTWPCMAMNLAGETQTWVNTEAWIRFPYKFIHFVMVINKFSDDPI";

        String[] result = getAlignmentAffineGapPenalties(v, w);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    public static String[] getAlignmentAffineGapPenalties(String str1, String str2) {
        int[][][] matrix = new int[3][str1.length() + 1][str2.length() + 1];
        int[][][] optimal = new int[3][str1.length() + 1][str2.length() + 1];
        int tempValue;
        for (int i = 1; i <= str1.length(); ++i) {
            tempValue = gapOpeningPenality + (i - 1);
            matrix[0][i][0] = -1 * tempValue;
            matrix[1][i][0] = -1 * tempValue;
            matrix[2][i][0] = -10 * gapOpeningPenality;
            optimal[0][i][0] = optimal[1][i][0] = optimal[2][i][0] = 0;
        }
        for (int j = 1; j <= str2.length(); ++j) {
            tempValue = gapOpeningPenality + (j - 1);
            matrix[0][0][j] = -10 * gapOpeningPenality;
            matrix[1][0][j] = -1 * tempValue;
            matrix[2][0][j] = -1 * tempValue;
            optimal[0][0][j] = optimal[1][0][j] = optimal[2][0][j] = 1;
        }
        for (int row = 1; row <= str1.length(); ++row) {
            for (int col = 1; col <= str2.length(); ++col) {
                if ((matrix[0][row - 1][col] - gapExtensionPenality) > (matrix[1][row - 1][col] - gapOpeningPenality)) {
                    matrix[0][row][col] = matrix[0][row - 1][col] - gapExtensionPenality;
                    optimal[0][row][col] = 0;
                } else {
                    matrix[0][row][col] = matrix[1][row - 1][col] - gapOpeningPenality;
                    optimal[0][row][col] = 1;
                }
                if ((matrix[2][row][col - 1] - gapExtensionPenality) > (matrix[1][row][col - 1] - gapOpeningPenality)) {
                    matrix[2][row][col] = matrix[2][row][col - 1] - gapExtensionPenality;
                    optimal[2][row][col] = 0;
                } else {
                    matrix[2][row][col] = matrix[1][row][col - 1] - gapOpeningPenality;
                    optimal[2][row][col] = 1;
                }
                matrix[1][row][col] = matrix[0][row][col];
                optimal[1][row][col] = 0;
                if ((matrix[1][row - 1][col - 1] + blosum62[aminos.indexOf(str1.charAt(row - 1))][aminos.indexOf(str2.charAt(col - 1))])
                        > matrix[1][row][col]) {
                    matrix[1][row][col] = matrix[1][row - 1][col - 1] + blosum62[aminos.indexOf(str1.charAt(row - 1))][aminos.indexOf(str2.charAt(col - 1))];
                    optimal[1][row][col] = 1;
                }
                if (matrix[2][row][col] > matrix[1][row][col]) {
                    matrix[1][row][col] = matrix[2][row][col];
                    optimal[1][row][col] = 2;
                }
            }
        }
        int i = str1.length(), j = str2.length();
        int bestValue = 0;
        int tempBest = matrix[0][i][j];
        if (matrix[1][i][j] > tempBest) {
            tempBest = matrix[1][i][j];
            bestValue = 1;
        }
        if (matrix[2][i][j] > tempBest) {
            tempBest = matrix[2][i][j];
            bestValue = 2;
        }
        String[] result = new String[3];
        result[0] = "" + tempBest;
        result[1] = "";
        result[2] = "";
        while (i > 0 && j > 0) {
            if (bestValue == 0) {
                if (optimal[0][i][j] == 1) bestValue = 1;
                result[1] = str1.charAt(i - 1) + result[1];
                i--;
                result[2] = '-' + result[2];
            } else if (bestValue == 1) {
                if (optimal[1][i][j] == 0) bestValue = 0;
                else if (optimal[1][i][j] == 2) bestValue = 2;
                else {
                    result[1] = str1.charAt(i - 1) + result[1];
                    i--;
                    result[2] = str2.charAt(j - 1) + result[2];
                    j--;
                }
            } else {
                if (optimal[2][i][j] == 1) bestValue = 1;
                result[1] = '-' + result[1];
                result[2] = str2.charAt(j - 1) + result[2];
                j--;
            }
        }
        if (i > 0) {
            result[1] = str1.substring(0, i) + result[1];
            StringBuilder temp = new StringBuilder();
            for (int x = 0; x < i; ++x) temp.append('-');
            result[2] = temp + result[2];
        }
        if (j > 0) {
            result[2] = str2.substring(0, j) + result[2];
            StringBuilder temp = new StringBuilder();
            for (int x = 0; x < j; ++x) temp.append('-');
            result[1] = temp + result[1];
        }
        return result;
    }
}
