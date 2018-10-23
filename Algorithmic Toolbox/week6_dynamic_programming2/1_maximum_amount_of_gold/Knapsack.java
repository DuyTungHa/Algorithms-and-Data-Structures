import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int[][] result = new int[w.length +1][W +1];

        for(int i = 1;i<= w.length;i++){
            for(int j =1;j<=W;j++){
                result[i][j] = result[i-1][j];
                if (j>=w[i-1])
                    result[i][j] = Math.max(result[i-1][j-w[i-1]] + w[i-1], result[i][j]);
            }
        }

        return result[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

