import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // need to populate these two
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        
        // create thread pool
        Thread[] thread_pool = new Thread[numWorkers];
        // initialize
        for (int i = 0 ; i < numWorkers ; i++)
            thread_pool[i] = new Thread(i, 0);
        
        Thread cand;
        
        for (int i = 0; i < jobs.length; i++) {
            // pick the top thread
            cand = extract_min(thread_pool);
            
            assignedWorker[i] = cand.index;
            startTime[i] = cand.finish_time;
            
            // change finish time of this thread
            // and heapify on next interation will take care of the heap property
            cand.finish_time += jobs[i]; 
            
            topDownHeapify(thread_pool, 0);
        }
    }
    
    private Thread extract_min(Thread[] thread_pool)    {
        Thread ret = thread_pool[0];
        return ret;
    }
    
    
    private void topDownHeapify(Thread[] data, int i)  {
        int min, left, right;
        Thread temp;
        
        min = i;
        left = 2 * i + 1;
        right = 2 * i + 2;

        if (left < data.length && data[left].finish_time <= data[min].finish_time)  {
            if (data[left].finish_time < data[min].finish_time)
                min = left;
            else if (data[left].finish_time == data[min].finish_time)   {
                if (data[left].index < data[min].index)
                    min = left;
            }
        }
            
        if (right < data.length && data[right].finish_time <= data[min].finish_time) {
            if (data[right].finish_time < data[min].finish_time)
                min = right;
            else if (data[right].finish_time == data[min].finish_time)   {
                if (data[right].index < data[min].index)
                    min = right;
            }
        }
            
        if (min != i)   {
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
            topDownHeapify(data, min);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }
    
    public class Thread  {
        int index;
        long finish_time;
        
        Thread(int index, int time) {
            this.index = index;
            this.finish_time = time;
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
