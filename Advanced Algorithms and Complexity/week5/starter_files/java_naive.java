import java.util.*;

public class java_naive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();

        Map<Integer, Integer> M = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int value = scanner.nextInt();
            M.put(id, value);
        }

        for (int i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int value = scanner.nextInt();
            M.put(id, M.get(id) - value);
        }

        int queries_n = scanner.nextInt();
        for (int i = 0; i < queries_n; ++i) {
            int q = scanner.nextInt();
            if (M.get(q) >= t)
                System.out.print("1 ");
            else
                System.out.print("0 ");
        }
    }
}

