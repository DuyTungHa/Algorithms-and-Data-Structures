import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;

        double[] valuesPerWeight = new double[values.length];
        for (int i = 0; i<valuesPerWeight.length; i++){
            valuesPerWeight[i] = (double) values[i]/weights[i];
        }

        while(capacity != 0){
            int indexOfMax = 0;
            for (int i = 0;i<valuesPerWeight.length;i++){
                if (valuesPerWeight[i] > valuesPerWeight[indexOfMax])
                    indexOfMax = i;
            }

            int amount = Math.min(capacity,weights[indexOfMax]);
            value += amount*valuesPerWeight[indexOfMax];
            capacity -= amount;
            valuesPerWeight[indexOfMax] = 0;
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
