import java.util.Stack;
import java.lang.String;
//I would like to review my implementation with TA at office hours for correctness
//The method works!!!
//Tested input: a+b*c+(d*e+f)*g
//Received output: abc*+de*f+g*+
//Tried also adding decimals and random white spaces and code still compiles and runs with the correct output
//Only one issue: output has weird spacing but the output is still correct. I need to account for this in my evaluator method
public class ToPostfixConverter implements Converter{
    /**
     * The fundamental method of any class implementing this interface. It converts the given
     * arithmetic expression to another type, depending on the implementation.
     *
     * @param expression the given arithmetic expression
     */
    @Override
    //this is the ToPostfixConverter.convert method
    //infix to postfix
    public String convert(ArithmeticExpression expression) {
        //Step 1: create a stack and returning string that contains the postfix expression
        Stack<Character> myStack = new Stack<>();
        StringBuilder conversion = new StringBuilder();
        //Step 2: Iterate through given expression, so we can evaluate accordingly.
        //created length method in ArithmeticExpression class and BalancedWord class, so I can iterate
        for (int i = 0; i < expression.length(); i++) {
            //created charAt method in ArithmeticExpression class and BalancedWord class, so I can extract a character at index i
            char c = expression.charAt(i);
            //Account for the whitespaces:
            if(c == ' '){
                //skip whitespaces
                continue;
            }
            // If the scanned character is an operand, append it to the returning expression
            if (isOperand((String.valueOf(c))) ) {
                //Handle operands with decimal points as well (at most 2 decimal points)
                //we do this by creating 'tokens' using the Token Builder
                TokenBuilder token = new TokenBuilder();
                token.append(c);
                while (i + 1 < expression.length()) {
                    //reference to next index
                    char nextChar = expression.charAt(i + 1);
                    //check if the next character is either a decimal point or an operand. If true, we append the next charcater to the operand token
                    if (isOperand(String.valueOf(nextChar)) || nextChar == '.') {
                        token.append(nextChar);
                        i++;  // Move to the next character
                    } else { // when we encounter a whitespace, we are done creating our token
                        break;  // Break if it's not part of the operand
                    }
                }
                // Add the tokens to the resulting postfix expression
                conversion.append(token.build() + " ");
            }
            //when a left parentheses is read we push onto the stack
            else if (Operator.of(c) == Operator.LEFT_PARENTHESIS) {
                myStack.push(c);
            }
            //when a right parentheses is read we pop until we reach a left parentheses and append those
            //values to the retuning string conversion. Make sure parentheses themselves are not included in
            //conversion
            else if (Operator.of(c) == Operator.RIGHT_PARENTHESIS) {
                while (!myStack.isEmpty() && myStack.peek() != Operator.LEFT_PARENTHESIS.getSymbol()) {
                    conversion.append(myStack.pop() + " ");
                }
                //remove left parentheses from stack
                if (!myStack.isEmpty() && myStack.peek() == Operator.LEFT_PARENTHESIS.getSymbol()) {
                    myStack.pop();
                }
            }
            //if the character read is an operator +-*/, we check 4 conditions:
            //if the rank of the current has a higher precedence than the operator at the top of the stack push the operator to the stack
            //otherwise, if the rank of the current has a lower precedence or the same precedence as the operator at the top of the stack
            // pop the operator at the top of the stack and append it to the resulting expression and push the currently read operator to the stack
            else if (Operator.isOperator(c)) {
                while (!myStack.isEmpty() && Operator.isOperator(myStack.peek())
                        && Operator.of(myStack.peek()).getRank() <= Operator.of(c).getRank()) {
                    conversion.append(myStack.pop() + " ");
                }
                myStack.push(c);
            }
        }
        // Pop any remaining operators from the stack and append them
        while (!myStack.isEmpty()) {
            conversion.append( myStack.pop() + " ");
        }
        // Split the final expression by spaces
        String[] postfixArray = conversion.toString().trim().split("\\s+");

        // Reassemble the expression without extra spaces
        StringBuilder finalExpression = new StringBuilder();
        for (String token : postfixArray) {
            finalExpression.append(token).append(" ");
        }
        return finalExpression.toString().trim(); // Remove trailing spaces
    }
    /**
     * Given a string and a specific index, returns the next token starting at that index.
     *
     * @param s     the given string
     * @param start the given index
     * @return the next token starting at the given index in the given string
     */
    public String nextToken(String s, int start) {
        StringBuilder token = new StringBuilder();

        while (start < s.length() && !Character.isWhitespace(s.charAt(start))) {
            token.append(s.charAt(start));
            start++;
        }
        return token.toString();
    }
    /**
     * Determines whether a string is a valid operand.
     *
     * @param s the given string
     * @return <code>true</code> if the string is a valid operand, <code>false</code> otherwise
     */
    @Override
    public boolean isOperand(String s) {
        return !Operator.isOperator(s) && !s.equals("(") && !s.equals(")");
    }
}
