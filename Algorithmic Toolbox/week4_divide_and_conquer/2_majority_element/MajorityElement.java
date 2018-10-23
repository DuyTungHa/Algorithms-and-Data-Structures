import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return a[left];
        }

        int mid = (right-left+1)/2;
        int element1 = getMajorityElement(a, left, mid + left-1);
        int element2 = getMajorityElement(a, mid +left  , right);
        if (element1 == element2)
            return element1;
        else{
            int count1 = getFrequency(a, element1, left, right);
            int count2 = getFrequency(a, element2, left, right);
            if (count1 > mid)
                return element1;
            else if (count2 > mid)
                return element2;
        }
        return -1;
    }

    private static int getFrequency(int[] a, int n, int left, int right){
        int count = 0;
        for (int i = left;i<= right;i++)
        {
            if (a[i] == n)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length-1) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

