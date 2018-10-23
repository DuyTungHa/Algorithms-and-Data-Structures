import java.util.Scanner;

public class Fibonacci {
  // private static long calc_fib(int n) {
  //   if (n <= 1)
  //     return n;

  //   return calc_fib(n - 1) + calc_fib(n - 2);
  // }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    System.out.println(fast_calc_fib(n));
  }

  private static long fast_calc_fib(int n){
    if (n <= 1)
      return n;

    int previous = 0;
    int current  = 1;

    for (int i = 0; i < n - 1; i++) {
      int tmp_previous = previous;
      previous = current;
      current = tmp_previous + current;
    }

    return current;
  }
}
