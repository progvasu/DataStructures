import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        
        // convert array to heap
        // start from half of the array
        for (int i = data.length / 2 ; i >= 0 ; i--)  {
            // top down heapify
            topDownHeapify(i);
        }
    }
    
    private void topDownHeapify(int i)  {
        int min, left, right, temp;
        
        min = i;
        left = 2 * i + 1;
        right = 2 * i + 2;

        if (left < data.length && data[left] < data[min])
            min = left;
        if (right < data.length && data[right] < data[min])
            min = right;

        if (min != i)   {
            // need to swap
            swaps.add(new Swap(i, min));
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
            topDownHeapify(min);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
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
