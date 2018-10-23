import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        return LongestCommonSubsequence(a,b,a.length,b.length);
    }

    public static int LongestCommonSubsequence(int[] a, int[] b, int n, int m){
        int[][] table = new int[n+1][m+1];
        for (int i = 0; i<= n; i++){
          for (int j =0; j<= m; j++){
            if(i==0)
              table[i][j] = 0;
            else if (j ==0)
              table[i][j] = 0;
            else{
              int matchOrSubstract = a[i-1]==b[j-1]?table[i-1][j-1] +1:table[i-1][j-1];
              table[i][j] = Math.max(Math.max(table[i][j-1], table[i-1][j]),matchOrSubstract);
            }
          }
        }
        return table[n][m];
      }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

