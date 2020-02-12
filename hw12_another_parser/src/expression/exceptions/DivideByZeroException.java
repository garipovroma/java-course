package expression.exceptions;

public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException (final String string) {
        super(string);
    }
}
