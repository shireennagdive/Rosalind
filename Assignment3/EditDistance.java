package CompBio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EditDistance {
    public static void main(String[] args) throws IOException {
        String read;
        String s[] = new String[2];
        s[0] = "";
        s[1] = "";
        try {
            @SuppressWarnings("resource")
            BufferedReader br = new BufferedReader(new FileReader("/Users/shireennagdive/Project/LeetCode/src/main/java/CompBio/rosalind.txt"));
            int i = -1;
            while ((read = br.readLine()) != null) {
                if (read.charAt(0) != '>') {
                    s[i] = s[i] + read;
                } else if ((read.charAt(0) == '>'))
                    i++;
            }
            //419
        }catch (Exception e){}
        String str1 = s[0];
        String str2 = s[1];
        int values[][] = new int[str2.length()+1][str1.length()+1];
        for(int i=0;i<=str2.length();i++){ //row-wise
            values[i][0] = i;
        }
        for(int j=0;j<=str1.length();j++){ //column-wise
            values[0][j] = j;
        }

        for(int j=0;j<str2.length();j++){ //
            for(int i=0;i<str1.length();i++){
                if(str1.charAt(i) == str2.charAt(j)){
                    values[j+1][i+1] = values[j][i];
                }else{
                    values[j+1][i+1] = 1+ Math.min(values[j][i], Math.min(values[j+1][i],values[j][i+1]));
                }
            }
        }
        System.out.println(values[str2.length()][str1.length()]);

    }
}
