// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String... args) {

        /* Test Cases for ToPostfix class: */
        /*
            ArithmeticExpression Myexp = new ArithmeticExpression("a+b*c+(d*e+f)*g");
            ArithmeticExpression Myexp = new ArithmeticExpression("a.a + b.b * c.cc + ( d * e + f ) * g ");
            ArithmeticExpression Myexp = new ArithmeticExpression("a.aa+b.bb*c.cc+(d.dd*e.ee+f.ff)*g.gg");
            ArithmeticExpression Myexp = new ArithmeticExpression("a.aa + b.bb   * c.cc + ( d.dd  *  e.ee+ f.ff ) * g.gg");
            */
/*
        ToPostfixConverter toPostfixConverter = new ToPostfixConverter();
        //stop line 6 for debugging
        String myEval = toPostfixConverter.convert(Myexp);
        for( int i =0; i < myEval.length(); i++)
            System.out.print(myEval.charAt(i));
        }
    }
All scenarios work; only issue is the awkward spacing of the output

*/

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

//        Test Cases for BST
//        All the test cases worked
    DynamicIntegerSet set = new DynamicIntegerSet();
    PrintableNode node = new DynamicIntegerSet.Node(3);
        // Add elements to the set
        set.add(50);
        set.add(30);
        set.add(70);
        set.add(20);
        set.add(40);
        set.add(60);
        set.add(80);

        // Check if elements exist in the set
        System.out.println("Contains 40: " + set.contains(40)); // Should be true
        System.out.println("Contains 90: " + set.contains(90)); // Should be false

        // Remove an element from the set
        set.remove(30);

        // Check if the removed element no longer exists
        System.out.println("Contains 30: " + set.contains(30)); // Should be false

        // Print the binary search tree structure
        DynamicIntegerSet.printTree(set.root());
    }
}
