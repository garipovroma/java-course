package expression.exceptions;

public class BracketNotFoundException extends RuntimeException {
    public BracketNotFoundException(String string) {
        super(string);
    }
}
