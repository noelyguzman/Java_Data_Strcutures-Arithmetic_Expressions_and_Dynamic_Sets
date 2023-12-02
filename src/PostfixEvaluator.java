import java.util.EmptyStackException;
import java.util.Stack;
import java.text.DecimalFormat;
public class PostfixEvaluator implements Evaluator {
    //You may have additional methods in Evaluator as well, but that is entirely up to you.
    private final Stack<Double> operandStack;
    private final DecimalFormat decimalFormat;

    public PostfixEvaluator() {
        //Initializing and empty stack
        operandStack = new Stack<>();
        //using the Decimal format package from java for evalutions
        decimalFormat = new DecimalFormat("#.##");
    }
    @Override
    public double evaluate(String postfixExpression) {
        //loop through the postfixExpression
        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);
            //Account for the whitespaces:
            if (c == ' ') {
                //skip whitespaces
                continue;
            }
            // If the scanned character is an operand, append it to the returning expression
            Converter.TokenBuilder Mytoken = new Converter.TokenBuilder();
            if (isOperand((String.valueOf(c)))) {
                //Handle operands with decimal points as well (at most 2 decimal points)
                //we do this by creating 'tokens' using the Token Builder

                //append the first character judges as an operand
                Mytoken.append(c);
                while (i + 1 < postfixExpression.length()) {
                    //reference to next index
                    char nextChar = postfixExpression.charAt(i + 1);
                    //check if the next character is either a decimal point or an operand. If true, we append the next character
                    // to the operand token.
                    if (isOperand(String.valueOf(nextChar)) || nextChar == '.') {
                        Mytoken.append(nextChar);
                        i++;  // Move to the next character
                    } else { // when we encounter a whitespace, we are done creating our token
                        break;  // Break if it's not part of the operand
                    }
                    //our token is officially formed
                }
                //When a token is seen, return the double value and push it onto the stack.
                if (isOperand(Mytoken.build())) {
                    double operand = Double.parseDouble(Mytoken.build());
                    operandStack.push(operand);
                }
            }
            //When an operator is seen, pop two elements successively from the stack, evaluate the expression (this
            //will always make sense, since we are only dealing with binary operators) and push the result back onto
            //the stack.
            else if (Operator.isOperator(c)) {
                if (operandStack.size() < 2){
                    throw new EmptyStackException();
                }
                    double operand2 = operandStack.pop();
                    double operand1 = operandStack.pop();
                    //created a method that will do the calculation for simplicity
                    double result = applyOperator(String.valueOf(c), operand1, operand2);
                    operandStack.push(result);
            }
        } //exiting loop of PostfixExpression

        // When there is a single operand left on the stack, pop and return it as the final result. If the input is
        // valid, the stack should have exactly one operand left when the expression has been completely read.
        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        double result = operandStack.pop();
        DecimalFormat df = new DecimalFormat("#.00"); // Format to two decimal places
        return Double.parseDouble(df.format(result)); // Parse the formatted result back to a double
    }
    public boolean isOperand(String s) {
        // First, check if it's a valid numeric string (with at most 2 decimal points)
        return s.matches("^(0*\\d*(\\.\\d{0,2})?)$");
    }
    public double applyOperator(String operator, double operand1, double operand2) {
        Operator op = Operator.of(operator);
        return switch (op) {
            case ADDITION -> operand1 + operand2;
            case SUBTRACTION -> operand1 - operand2;
            case MULTIPLICATION -> operand1 * operand2;
            case DIVISION -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield operand1 / operand2;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }
}
