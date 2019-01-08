import java.util.*;
import java.io.*;

public class is_bst {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            // Implement correct algorithm here
            // will not work
//            for (int i = 0 ; i < tree.length ; i++) {
//                if (tree[i].left != -1 && tree[i].key < tree[tree[i].left].key)
//                    return false;
//                if (tree[i].right != -1 && tree[i].key > tree[tree[i].right].key)
//                    return false;
//            }
            
            //
            if (nodes == 0)
                return true;
            // find inorder traversal and see if it is in increasing sequence
            ArrayList<Integer> result = new ArrayList<Integer>();
            inOrderRecur(result, 0);
            
            for (int i = 1 ; i < result.size() ; i++)   {
                if (result.get(i - 1) > result.get(i))
                    return false;
            }
            
            return true;
        }
        
        void inOrderRecur(ArrayList<Integer> result, int root)    {
            // left child
            if (tree[root].left != -1)
                inOrderRecur(result, tree[root].left);
            // process the node
            result.add(result.size(), tree[root].key);
            // right child
            if (tree[root].right != -1)
                inOrderRecur(result, tree[root].right);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
