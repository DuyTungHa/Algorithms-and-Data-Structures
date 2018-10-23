import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] a = new int[n +1];
        for (int i = 1; i < a.length; i++){
            a[i] = a[i-1] + 1;
            if (i%2 == 0 && a[i] > a[i/2] + 1){
                a[i] = a[i/2] + 1;
            }
            if (i%3 == 0 && a[i] > a[i/3] + 1){
                a[i] = a[i/3] + 1;
            }
        }

        while (n >= 1) {
            sequence.add(n);
            int count1 = a[n-1];
            int count2 = n%2==0?a[n/2]:Integer.MAX_VALUE;
            int count3 = n%3==0?a[n/3]:Integer.MAX_VALUE;

            if (count3 <= Math.min(count2, count1))
                n = n/3;
            else if (count2 <= Math.min(count1, count3))
                n= n/2;
            else
                n--;
            
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

