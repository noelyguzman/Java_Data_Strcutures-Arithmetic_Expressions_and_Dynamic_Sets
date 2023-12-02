/**
 * This interface defines the structure of any converter to be used for conversion of arithmetic
 * expressions between infix, prefix, and postfix types.
 *
 * @author Ritwik Banerjee
 */
public interface Converter {
        /**
         * The fundamental method of any class implementing this interface. It converts the given
         * arithmetic expression to another type, depending on the implementation.
         *
         * @param expression the given arithmetic expression
         */
        String convert(ArithmeticExpression expression);
        /**
         * Given a string and a specific index, returns the next token starting at that index.
         *
         * @param s the given string
         * @param start the given index
         * @return the next token starting at the given index in the given string
         */
        String nextToken(String s, int start);
        /**
         * Determines whether or not a string is a valid operand.
         *
         * @param s the given string
         * @return <code>true</code> if the string is a valid operand, <code>false</code> otherwise
         */
        boolean isOperand(String s);
/**
 * This class handles the parsing of tokens from a string. This is helpful in situations where
 * a single token may take up more than one character in the string.
 */
class TokenBuilder {
    /**
     * The {@link StringBuilder} object used internally. This is used because {@link String}s
     * in Java are immutable, while we may want to build a token as we parse from left to
     * right one character at a time.
     */
    private StringBuilder tokenBuilder = new StringBuilder();
    /**
     * @see StringBuilder#append(char)
     */
    public void append(char c) {
        tokenBuilder.append(c);
    }
    /**
     * @return the final string object that represents a single token
     * @see StringBuilder#toString()
     */
    public String build() {
        return tokenBuilder.toString();
    }
}
}
