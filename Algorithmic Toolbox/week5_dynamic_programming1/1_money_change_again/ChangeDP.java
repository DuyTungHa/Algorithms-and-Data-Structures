import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] a = new int[m+1];
        for(int i = 1;i<a.length; i++){
            a[i] = a[i-1] + 1;
            if (i - 3 >= 0 && a[i] > a[i-3] + 1){
                a[i] = a[i-3] + 1;
            }
            if (i-4 >=0 && a[i] > a[i-4] +1){
                a[i] = a[i-4] + 1;
            }
        }
        return a[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

