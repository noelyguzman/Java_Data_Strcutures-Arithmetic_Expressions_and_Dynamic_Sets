import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class defines an arithmetic expression as a wrapper around balanced words. It also
 * provides the <code>main</code> method to run the application to evaluate such expressions.
 *
 * @author Ritwik Banerjee
 */
public class ArithmeticExpression {
    /**
     * Provide the full path to the input file.
     */
    private static final String INPUT_PATH = "C:\\Users\\nguzm\\IdeaProjects\\CSE214Assignment2\\src\\Test Cases";
    /**
     * The balanced word around which we are wrapping.
     */
    private BalancedWord expression;
    /**
     * The constructor for this class, simply sets the balanced word based on the given expression.
     *
     * @param expression the given expression
     * @throws IllegalArgumentException if the given expression is not a valid balanced word
     */
    public ArithmeticExpression(String expression) throws IllegalArgumentException {
        this.expression = new BalancedWord(expression);
    }
    /**
     * @return the string representation of the balanced word
     */
    public String getExpression() {
        return expression.getWord();
    }
    /**
     * The main method to run the application. It reads from the file specified by
     * {@link ArithmeticExpression#INPUT_PATH}, prints out the equivalent postfix expressions,
     * and then prints out the final evaluation as a <code>double</code>.
     */
    public static void main(String... args) {
        File input = new File(INPUT_PATH);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.printf("Input: %s%n", line);
                try {
                    ArithmeticExpression a = new ArithmeticExpression(line.trim());
                    Converter converter = new ToPostfixConverter();
                    String postfix = converter.convert(a);
                    System.out.printf("\tPostfix: %s%n", postfix);

                    Evaluator evaluator =  new PostfixEvaluator();
                    double result = evaluator.evaluate(postfix);
                    System.out.printf("\tValue: %f%n", result);
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /* Test Cases for ToPostfix class: */

//            ArithmeticExpression Myexp = new ArithmeticExpression("a+b*c+(d*e+f)*g");
//            ArithmeticExpression Myexp = new ArithmeticExpression("a.a + b.b * c.cc + ( d * e + f ) * g ");
//            ArithmeticExpression Myexp = new ArithmeticExpression("a.aa+b.bb*c.cc+(d.dd*e.ee+f.ff)*g.gg");
//            ArithmeticExpression Myexp = new ArithmeticExpression("a.aa + b.bb   * c.cc + ( d.dd  *  e.ee+ f.ff ) * g.gg");


//        ToPostfixConverter toPostfixConverter = new ToPostfixConverter();
//        //stop line 6 for debugging
//        String myEval = toPostfixConverter.convert(Myexp);
//        for( int i =0; i < myEval.length(); i++)
//            System.out.print(myEval.charAt(i));
//        }
//All scenarios work; only issue is the awkward spacing of the output


/*
        //Test Cases for PostfixEvaluator Class
        PostfixEvaluator evaluator = new PostfixEvaluator();



// Test Case 1:
        double result1 = evaluator.evaluate("6.0 5.0 +");
        System.out.println("Test Case 1: " + result1); // Expected Output: 11.0

// Test Case 2:
        double result2 = evaluator.evaluate("3 5 * 2 3 / +");
        System.out.println("Test Case 2: " + result2); // Expected Output: 15.6
//Test Case 3:
        double result = evaluator.evaluate("3 4 * 2 +");
        System.out.println("Result: " + result);
All the test cases for PostfixEvaluator Class Work !!
*/

        public int length () {
            return expression.length();
        }

        public char charAt ( int i){
            return expression.charAt(i);
        }

}


