package expression.exceptions;

public interface ExpressionSource {
    boolean hasNext();
    char next();
    ExpressionException error(final String message);
}
