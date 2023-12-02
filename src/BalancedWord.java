import java.util.Stack;
public class BalancedWord {
    private final String word;

    public BalancedWord(String word){
        if(isBalanced(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a balanced word.", word));
    }
    private static boolean isBalanced(String word){
        Stack<Character> stack = new Stack<>();
        for (char c : word.toCharArray()) {
            if (c == '(' ) {
                stack.push(c);
            }
            else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                else if (stack.peek() == '(') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
        }
    public String getWord() {
        return word;
    }

    public int length() {
        return getWord().length();
    }

    public char charAt(int i) {
        return getWord().charAt(i);
    }
    //we only to make sure the parentheses are balanced, not check if it is a valid expression.
    }

