import java.util.*;
import java.io.*;

public class tree_height {
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

    class Node  {
        int index;
        // height
        int height;
        // array list of children
        List<Node> children = new ArrayList<>();
        
        Node(int index) {
            this.index = index;
            height = 1;
        }
    }
    
    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // create an array of nodes given
            Node[] nodes = new Node[n];
            for (int i = 0 ; i < n ; i++)
                nodes[i] = new Node(i);
            
            // root node
            Node root = null;
            
            // fill in their children
            for (int child_index = 0 ; child_index < n ; child_index++) {
                // parent_index - parent[i]
                if (parent[child_index] == -1)
                    root = nodes[child_index];
                else
                    // add as child of parent
                    nodes[parent[child_index]].children.add(nodes[child_index]);
            }
            
            // now finding the height - iteratively
            // using a queue
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
                    
            Node temp;
            int max_height = 0;
            
            while(!queue.isEmpty())    {
                // get a node from queue
                temp = queue.remove();
                
                if (max_height < temp.height)
                    max_height = temp.height;
                    
                // add all children to queue + increasing their height by one
                for (Node child : temp.children)    {
                    child.height = temp.height + 1;
                    queue.add(child);
                }
            }
            
            // print all height
//            for (int i = 0 ; i < n ; i++)
//                System.out.println(nodes[i].index + " : " + nodes[i].height);
            
            return max_height;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
