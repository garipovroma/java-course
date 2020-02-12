package expression.exceptions;

public class OverflowException extends RuntimeException {
    public OverflowException (final String string) {
        super(string);
    }
}
