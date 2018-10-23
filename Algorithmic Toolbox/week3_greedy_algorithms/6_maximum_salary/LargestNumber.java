import java.util.*;

public class LargestNumber {
    private static String largestNumber(int[] numbers) {
        String result = "";
        
        for (int i = 0; i<numbers.length; i++){
            int maxDigitIndex = 0;
            for (int j = 0; j<numbers.length; j++){
                while(numbers[maxDigitIndex]==0)
                    maxDigitIndex++;
                if(numbers[j] !=0 && isGreaterOrEqual(numbers[j], numbers[maxDigitIndex]))
                    maxDigitIndex = j;
            }
            result += numbers[maxDigitIndex];
            numbers[maxDigitIndex] = 0;
        }

        return result;
    }

    private static boolean isGreaterOrEqual(int a, int b){
        String ab = "";
        String ba = "";
        ab += a;
        ab +=b;
        ba += b; 
        ba += a;
        if (Integer.parseInt(ab) >= Integer.parseInt(ba))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(largestNumber(a));
    }
}

