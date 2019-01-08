import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    
    private static int prime = 10000007;
    private static int multiplier = 63;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }
    
    private static int hashFunc(String s) {
        long hash = 0;
        for (int i = 0; i < s.length() ; i++)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash;
    }
    
    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();

//        // brute force approach
//        for (int i = 0; i + m <= n; ++i) {
//	    boolean equal = true;
//	    for (int j = 0; j < m; ++j) {
//		if (s.charAt(j) != t.charAt(i + j)) {
//		     equal = false;
// 		    break;
//		}
//	    }
//            if (equal)
//                occurrences.add(i);
//	}
        
        // dont have to precompute hashes
        // will compute hashes and on match will check for substring and pattern match
        
        // computing hash of last |pattern| length of input
        int hash = hashFunc(input.text.substring(0, m));
        int pattern_hash = hashFunc(input.pattern);
        
        int y = 1;
        for (int i = 1 ; i <= m ; i++)
            y = (y * multiplier) % prime;
        
//        System.out.println(hash);
//        System.out.println(pattern_hash);
        
        if (input.text.substring(0, m).equals(input.pattern))
            occurrences.add(0);
        
        for (int i = m ; i < n ; i++) {
            hash = ((multiplier * hash + input.text.charAt(i) - y * input.text.charAt(i - m)) % prime + prime) % prime ;
            
            if (hash == pattern_hash)   {
                if (input.text.substring(i - m + 1, i + 1).equals(input.pattern))
                    occurrences.add(occurrences.size(), i - m + 1);
            }
            
//            System.out.println(hash);
        }
       
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

