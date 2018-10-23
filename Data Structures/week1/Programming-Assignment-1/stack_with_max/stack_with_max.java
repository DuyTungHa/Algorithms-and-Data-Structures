import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

class stack_with_max {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        int n = Integer.parseInt(reader.readLine());
        Stack<Integer> mainStack = new Stack<Integer>();
        Stack<Integer> supportStack = new Stack<Integer>();
        String[] commands = new String[n];
        int maxTemp = 0;

        for(int i =0; i<n; i++){
            commands[i] = reader.readLine();
        }

        for(int i =0; i<n; i++){
            if(commands[i].equals("pop")){
                mainStack.pop();
                supportStack.pop();
            } else if(commands[i].equals("max")){
                System.out.println(supportStack.peek());
            } else{
                int element = (int) commands[i].charAt(5);
                System.out.println(element);
                mainStack.push(element);
                if(element>maxTemp)
                    maxTemp = element;
                supportStack.push(maxTemp);
            }
        }
    }

}