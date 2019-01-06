import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows, int rank) {
            this.numberOfRows = numberOfRows;
            this.rank = 0;
            this.parent = this;
        }
        Table getParent() {
            // find super parent and compress path
            if (this != parent)    {
                parent = parent.getParent();
            }
            return parent;
        }
    }

    int maximumNumberOfRows = -1;

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        // merge two components here
        // use rank heuristic
        if (realDestination.rank > realSource.rank) {
            realSource.parent = realDestination;
            realDestination.numberOfRows += realSource.numberOfRows;
            realSource.numberOfRows = 0;
        }
        else    {
            realDestination.parent = realSource;
            if (realDestination.rank == realSource.rank)    {
                realSource.rank += 1;
            }
            realSource.numberOfRows += realDestination.numberOfRows;
            realDestination.numberOfRows = 0;
        }
        
        // since source goes to destination always
        // do we need union by rank heuristic?
//        realSource.parent = realDestination;
        // no we are ignoring the order
        
        // update maximumNumberOfRows
        if (realDestination.numberOfRows > maximumNumberOfRows)
            maximumNumberOfRows = realDestination.numberOfRows;
        if (realSource.numberOfRows > maximumNumberOfRows)
            maximumNumberOfRows = realSource.numberOfRows;
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows, i);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
