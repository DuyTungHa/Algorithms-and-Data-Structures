import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] A) {
       if (A.length < 3)
            return 0;
        int sum = Arrays.stream(A).sum();
        return ((sum%3)==0 && subsetSum(A, A.length-1, sum/3, sum/3, sum/3))?1:0;
    }

    public static boolean subsetSum(int[] S,int n, int a, int b, int c){
        if(a==0 && b==0 && c==0){
            return true;
        }
        
        boolean A = false;
        if(a - S[n] >=0){
            A = subsetSum(S, n-1, a-S[n], b, c);
        }

        boolean B = false;
        if (!A && (b - S[n] >=0)){
            B = subsetSum(S, n-1, a, b-S[n], c);
        }

        boolean C = false;
        if ((!A&&!B)&&(c-S[n]>=0)){
            C = subsetSum(S, n-1, a, b, c-S[n]);
        }

        return A||B||C;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        System.out.println(partition3(A));
    }
}

