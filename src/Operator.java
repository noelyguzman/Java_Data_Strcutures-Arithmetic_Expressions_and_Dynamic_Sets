/**
 * This class provides the fixed enumerable types of operators to be used in the conversions and
 * evaluations of simple arithmetic expressions. Each operator is also equipped with a rank, which
 * serves to determine the precedence between two operators in case brackets are not enough to
 * resolve ambiguity of operation order.
 *
 * Since the variable and method names are obvious, documentation has not been provided for each
 * object or method individually.
 *
 * @author Ritwik Banerjee
 */
public enum Operator {
        // lower rank indicates higher priority or precedence
        LEFT_PARENTHESIS('(', 0),
        RIGHT_PARENTHESIS(')', 0),
        MULTIPLICATION( '*' , 1),
        DIVISION('/', 1),
        ADDITION('+', 2),
        SUBTRACTION('-', 2);
        private final char symbol;
        private final int rank;
        Operator(char c, int rank) {
            this.symbol = c;
            this.rank = rank;
        }
        public char getSymbol() { return symbol; }
        public int getRank() { return rank; }
        public static boolean isOperator(char c) {
            return c == ADDITION.symbol || c == SUBTRACTION.symbol ||
                    c == MULTIPLICATION.symbol || c == DIVISION.symbol;

        }
        public static boolean isOperator(String c) { return isOperator(c.charAt(0)); }
        public static Operator of(char c) {
            if (c == LEFT_PARENTHESIS.symbol)
                return LEFT_PARENTHESIS;
            if (c == RIGHT_PARENTHESIS.symbol)
                return RIGHT_PARENTHESIS;
            if (c == MULTIPLICATION.symbol)
                return MULTIPLICATION;
            if (c == DIVISION.symbol)
                return DIVISION;
            if (c == ADDITION.symbol)
                return ADDITION;
            if (c == SUBTRACTION.symbol)
                return SUBTRACTION;
            throw new IllegalArgumentException(String.format("Invalid operator character %c.", c));
        }
        public static Operator of(String c) { return Operator.of(c.charAt(0)); }
    }

