import java.math.BigInteger;
import java.util.*;

public class FibonacciHuge {
    // private static long getFibonacciHugeNaive(long n, long m) {
    //     if (n <= 1)
    //         return n;

    //     long previous = 0;
    //     long current  = 1;

    //     for (long i = 0; i < n - 1; ++i) {
    //         long tmp_previous = previous;
    //         previous = current;
    //         current = tmp_previous + current;
    //     }

    //     return current % m;
    // }
    
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // long n = scanner.nextLong();
        // long m = scanner.nextLong();
        //System.out.println(getFibonacciHugeNaive(n, m));
        System.out.println(getFibonacciHugeFast(125, 1000));
    }

    private static long getFibonacciHugeFast(long n, long m1){
        if(n<=1)
            return n;

        int count = 0;
        BigInteger previous = new BigInteger("0");
        BigInteger previousMod = new BigInteger("0");
        BigInteger current  = new BigInteger("1");
        BigInteger currentMod = new BigInteger("1");
        BigInteger m = new BigInteger((String) m1);
        

        while (true) {
            BigInteger tmp_previous = previous;
            previous = current;
            current = new BigInteger((String) (tmp_previous + current));
            
            previousMod = currentMod;
            currentMod = current.mod(m);
            count++;
            if (previousMod==0 && currentMod==1)
                break;
        }

        System.out.println(count);

        previous=0;
        current=1;

        for (long i = 0; i< (n%count)-1;i++){
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        System.out.println(current);

        return current%m;
    }
}

