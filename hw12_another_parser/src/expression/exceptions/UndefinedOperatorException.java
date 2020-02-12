package expression.exceptions;

public class UndefinedOperatorException extends RuntimeException {
    public UndefinedOperatorException(String string) {
        super(string);
    }
}
