import java.io.*;
import java.util.*;

class Node
{
	public int symbol = -1;
	public List<Node> outgoing = new ArrayList<Node>();
}

public class TrieMatching implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return -1;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		Node root = trieConstruction(patterns);
		trieMatching(text, root, result);
		return result;
	}

	void trieMatching(String text, Node root, List<Integer> result){
		int index = 0;
		while(text.length() >0){
			prefixTrieMatching(text, root, result, index);
			text = text.substring(1);
			index++;
		}
	}

	void prefixTrieMatching(String text, Node v, List<Integer> result, int index){
		int counter = 0;
		int symbol = letterToIndex(text.charAt(counter));
		while (true){
			if (v.outgoing.isEmpty()){
				result.add(index);
				return;
			} else {
				boolean found = false;
				for (Node e : v.outgoing) {
					if(e.symbol == symbol){
						found = true;
						symbol = ++counter < text.length() ? letterToIndex(text.charAt(counter)) : -1;
						v = e;
						break;
					}
				}
				if (!found)
					return;
			}
		}
	}

	Node trieConstruction(List<String> patterns){
		Node root = new Node();
		for (String pattern : patterns) {
			Node currentNode = root;
			for (int i = 0; i<pattern.length(); i++){
				int currentSymbol = letterToIndex(pattern.charAt(i));

				boolean found = false;
				for(int j = 0; j <currentNode.outgoing.size(); j++){
					if (currentNode.outgoing.get(j).symbol == currentSymbol){
						currentNode = currentNode.outgoing.get(j);
						found = true;
						break;
					}
				}
				if(!found){
					Node newNode = new Node();
					newNode.symbol = currentSymbol;
					currentNode.outgoing.add(newNode);
					currentNode = newNode;
				}
			}
		}
		return root;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
