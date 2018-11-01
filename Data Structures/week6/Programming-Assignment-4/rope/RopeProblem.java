import java.io.*;
import java.util.*;

class RopeProblem {
	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	class Rope {
		String result;

		class Node{
			int offset, len, size;
			char s;
			Node left, right, parent;
		}

		void process( int i, int j, int k ) {
                        
		}

		void printr(Node r){
			for(int i =0; i<r.len; i++){
				result += r.s;
			}
		}

		void report(Node r){
			if (r == null)
				return;
			report(r.left);
			printr(r);
			report(r.right);
		}

		String result() {
			return result;
		}

		Rope( String s ) {
			this.result = s;
		}
	}

	

	public static void main( String[] args ) throws IOException {
		new RopeProblem().run();
	}
	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		Rope rope = new Rope(in.next());
		for (int q = in.nextInt(); q > 0; q--) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			rope.process(i, j, k);
		}
		out.println(rope.result());
		out.close();
	}
}
