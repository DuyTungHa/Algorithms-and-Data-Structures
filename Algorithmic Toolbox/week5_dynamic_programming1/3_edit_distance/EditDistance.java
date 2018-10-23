import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    String[] first = s.split("");
    String[] second = t.split("");

    return FindDistanceFast(first, second, first.length, second.length);
  }

  public static int FindDistanceFast(String[] a, String[] b, int n, int m){
    int[][] table = new int[n+1][m+1];
    for (int i = 0; i<= n; i++){
      for (int j =0; j<= m; j++){
        if(i==0)
          table[i][j] = j;
        else if (j ==0)
          table[i][j] = i;
        else{
          int matchOrSubstract = a[i-1].equals(b[j-1])?table[i-1][j-1]:table[i-1][j-1] +1;
          table[i][j] = Math.min(Math.min(table[i][j-1] + 1, table[i-1][j] + 1),matchOrSubstract);
        }
      }
    }
    return table[n][m];
  }
  
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
