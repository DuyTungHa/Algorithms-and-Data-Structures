import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumFast(from, to));
    }

    private static long getFibonacciPartialSumFast(long from, long to){
        long sum = 0;
        long current = 0;
        long next = 1;
        for (long i = 0; i<= (to%60);i++){
            if (i >= (from%60))
                sum = (sum+current)%10;

            long new_current = next;
            next = (next + current)%10;
            current = new_current;
        }
        return sum;
    }
}

