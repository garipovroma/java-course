package expression.exceptions;

public class StringSource implements ExpressionSource {
    private final String data;
    private int pos;

    public StringSource(String data) {
        this.data = data;
        this.pos = 0;
    }

    private void skipWhitespaces() {
        while (pos < data.length() && Character.isWhitespace(data.charAt(pos))) {
            pos++;
        }
    }

    @Override
    public boolean hasNext() {
        skipWhitespaces();
        return pos < data.length();
    }

    @Override
    public char next() {
        skipWhitespaces();
        return data.charAt(pos++);
    }
    @Override
    public ExpressionException error(String message) {
        return new ExpressionException(pos + ": " + message);
    }
}
