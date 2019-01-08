import java.util.*;
import java.io.*;

public class is_bst_hard {
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
        
        long nextInt() throws IOException {
            return Long.parseLong(next());
        }
    }

    public class IsBST {
        class Node {
            long key;
            int left;
            int right;
            long min;
            long max;

            Node(long key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
                this.min = key;
                this.max = key;
            }
        }

        long nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[(int)nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), (int)in.nextInt(), (int)in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            if (nodes == 0)
                return true;
            
            // find inorder traversal and see if it is in increasing sequence
            boolean result = postOrderRecur(0);
            
            return result;
        }
        
        boolean postOrderRecur(int root)    {
            boolean res1 = true, res2 = true;
            
            // left child
            if (tree[root].left != -1)  {
                res1 = postOrderRecur(tree[root].left);
            }
            
            if (res1 == false)
                return false;                
            
            // right child
            if (tree[root].right != -1) {
                res2 = postOrderRecur(tree[root].right);
            }
            
            if (res2 == false)
                return false;
        
            // process the node
            // check for left node
            if (tree[root].left != -1)
                if (tree[tree[root].left].max >= tree[root].key)
                    return false;
                else
                    tree[root].min = tree[tree[root].left].min;
            
            // check for right node
            if (tree[root].right != -1)
                if (tree[tree[root].right].min < tree[root].key)
                    return false;
                else
                    tree[root].max = tree[tree[root].right].max;
                
            return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
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
