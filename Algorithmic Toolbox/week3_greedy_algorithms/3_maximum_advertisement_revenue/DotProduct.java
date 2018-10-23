import java.util.*;

public class DotProduct {
    private static long maxDotProduct(int[] a, int[] b) {
        
        int[] aSorted = new int[a.length];
        int[] bSorted = new int[b.length];

        
        for (int i = 0; i<a.length ; i++){
            int maxIndexA = 0;
            int maxIndexB = 0;
            for (int j = 0;j<a.length; j++){
                if (a[j] > a[maxIndexA])
                    maxIndexA = j;
                if (b[j] > b[maxIndexB]){
                    maxIndexB = j;
                }
            }
            aSorted[i] = a[maxIndexA];
            a[maxIndexA] = Integer.MIN_VALUE;
            bSorted[i] = b[maxIndexB];
            b[maxIndexB] = Integer.MIN_VALUE;
        }

        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += (long) aSorted[i] * bSorted[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

