import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int count = 0;
        count += m/10;
        m = m%10;
        count += m/5;
        m = m%5;
        count += m;
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

