import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumFast(n);
        System.out.println(s);
    }

    private static long getFibonacciSumFast(long n){
        if (n<=1)
            return n;

        int previous = 0;
        int current = 1;

        for (int i = 0;i<(((n+2)%60)-1);i++){
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current)%10;
        }

        if (current==0)
            return 9;
        else
            return current-1;
    }
}

