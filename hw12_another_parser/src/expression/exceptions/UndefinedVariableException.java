package expression.exceptions;

public class UndefinedVariableException extends RuntimeException {
    public UndefinedVariableException(String string) {
        super(string);
    }
}
