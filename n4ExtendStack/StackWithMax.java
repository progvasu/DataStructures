import java.util.*;
import java.io.*;

public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();

        // taking an auxiliary stack that will store the max element till that point
//        Stack<Integer> aux_stack = new Stack<>();
                
        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if (null != operation) 
                switch (operation) {
                case "push":
                    int value = scanner.nextInt();
                    if (stack.isEmpty())
                        stack.push(value);
                    else if (stack.peek() < value)
                        stack.push(value);
                    else
                        stack.push(stack.peek());
                    break;
                case "pop":
                    // pop from aux stack if the value equals the top of aux stack
                    stack.pop();
                    break;
                case "max":
                    if (!stack.isEmpty())
                        System.out.println(stack.peek());
                    break;
            }
            // dont actually need an aux stack
//            String operation = scanner.next();
//            if (null != operation) switch (operation) {
//                case "push":
//                    int value = scanner.nextInt();
//                    stack.push(value);
//                    // if this value if bigger than top aux_stack we push this new value
//                    if (aux_stack.isEmpty() || value >= aux_stack.peek())
//                        aux_stack.push(value);
//                    break;
//                case "pop":
//                    // pop from aux stack if the value equals the top of aux stack
//                    if (aux_stack.peek() <= stack.peek())
//                        aux_stack.pop();
//                    stack.pop();
//                    break;
//                case "max":
//                    if (!aux_stack.isEmpty())
//                        System.out.println(aux_stack.peek());
//                    break;
//            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
