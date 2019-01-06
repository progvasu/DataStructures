import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

public class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int position = 0;
        
        for (position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // opening brackets - simply push
                opening_brackets_stack.push(new Bracket(next, position + 1));
            }

            if (next == ')' || next == ']' || next == '}') {
                // for closing bracket need to find the position where the error occurs
                // if it occurs
                if (!opening_brackets_stack.isEmpty() && opening_brackets_stack.peek().Match(next))
                    // if true - simply pop
                    opening_brackets_stack.pop();
                else
                    // find the position where error occurs
                    break;
            }
        }

        if (position == text.length())
            if (opening_brackets_stack.isEmpty())
                System.out.println("Success");
            else
                System.out.println(opening_brackets_stack.pop().position);
        else
            System.out.println(position + 1);
    }
}
