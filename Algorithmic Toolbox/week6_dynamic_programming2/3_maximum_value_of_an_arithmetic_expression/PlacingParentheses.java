import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
      int[] numbers = new int[exp.length()/2 +1];
      char[] op = new char[exp.length() - numbers.length]; 
      for(int i = 0;i<exp.length(); i++){
        if(i%2==0)
            numbers[i/2] = Character.getNumericValue(exp.charAt(i));
        else
            op[i/2] = exp.charAt(i);
      }

      int n = numbers.length;
      long[][] M = new long[n][n];
      long[][] m = new long[n][n];
      for(int i = 0; i<n; i++){
        M[i][i] = numbers[i];
        m[i][i] = numbers[i];
      }

      for(int s = 1; s<n; s++){
          for(int i = 0; i< n-s; i++ ){
            int j = i+s;
            minAndMax(i, j, M, m, op);
          }
      }

      return M[0][n-1];
    }

    private static void minAndMax(int i, int j, long[][] M, long[][] m, char[] op){
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;

        for(int k = i; k<j; k++){
            long a = eval(M[i][k], M[k+1][j], op[k]);
            long b = eval(M[i][k], m[k+1][j], op[k]);
            long c = eval(m[i][k],M[k+1][j] , op[k]);
            long d = eval(m[i][k], m[k+1][j], op[k]);
            min = Math.min(Math.min(Math.min(min,a),Math.min(b,c)),d);
            max = Math.max(Math.max(Math.max(max,a),Math.max(b,c)),d);
        }
        
        M[i][j] = max;
        m[i][j] = min;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

