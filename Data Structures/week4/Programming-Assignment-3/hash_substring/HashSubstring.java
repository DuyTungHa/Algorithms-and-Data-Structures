import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();

        int prime = 1000000007;
        int x = 31;

        long[] P = new long[m];
        long[] T = new long[n];
        for(int i =0; i<m; i++){
            P[i] = s.charAt(i);
        }
        for(int i =0; i<n; i++){
            T[i] = t.charAt(i);
        }

        long pHash = polyHash(P, prime, x);
        long[] H = precomputeHashes(T, m, prime, x);

        for(int i =0; i<= n-m; i++){
            if(pHash != H[i])
                continue;
            if(areEqual(Arrays.copyOfRange(T, i, i + m), P)){
                occurrences.add(i);
            }
        } 

        return occurrences;
    }

    private static boolean areEqual(long[] S1, long[] S2){
        if(S1.length != S2.length)
            return false;
        for(int i =0; i<S1.length; i++){
            if(S1[i] != S2[i])
                return false;
        }
        return true;
    }

    private static long polyHash(long[] S, int p, int x){
        long hash = 0;
        for(int i = S.length-1; i>=0;i-- ){
            hash = (hash*x + S[i])%p;
        }
        return hash;
    }

    private static long[] precomputeHashes(long[]T, int Plength, int p, int x){
        long[] H = new long[T.length - Plength +1];
        long[] S = Arrays.copyOfRange(T,T.length - Plength, T.length);
        H[T.length - Plength] = polyHash(S,p,x);
        long y =1;
        for (int i =1; i<= Plength; i++){
            y = (y*x)%p;
        }
        for(int i = T.length -Plength-1; i>=0; i--){
            H[i] = ((x*H[i+1] + T[i]%p - y*T[i+Plength])%p +p)%p;
        }
        return H;
    }



    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

