import java.util.*;
import java.io.*;

public class tree_orders {
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

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) { 
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            
            inOrderRecur(result, 0);
            
            return result;
        }
        
        void inOrderRecur(ArrayList<Integer> result, int root)    {
            // left child
            if (left[root] != -1)
                inOrderRecur(result, left[root]);
            // process the node
            result.add(result.size(), key[root]);
            // right child
            if (right[root] != -1)
                inOrderRecur(result, right[root]);
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that

            preOrderRecur(result, 0);
            
            return result;
        }
        
        void preOrderRecur(ArrayList<Integer> result, int root)    {
            // process the node
            result.add(result.size(), key[root]);
            // left child
            if (left[root] != -1)
                preOrderRecur(result, left[root]);
            // right child
            if (right[root] != -1)
                preOrderRecur(result, right[root]);
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            
            postOrderRecur(result, 0);
            
            return result;
        }
        
        void postOrderRecur(ArrayList<Integer> result, int root)    {
            // left child
            if (left[root] != -1)
                postOrderRecur(result, left[root]);
            // right child
            if (right[root] != -1)
                postOrderRecur(result, right[root]);
            // process the node
            result.add(result.size(), key[root]);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } 
                catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
