package expression.exceptions;

public class BaseParser {
    private ExpressionSource source;
    protected char ch;
    public void createSource(ExpressionSource source) {
        this.source = source;
    }
    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }
    protected boolean testBetween(char l, char r) {
        if (l <= ch && ch <= r) {
            return true;
        }
        return false;
    }
    protected void expect(final char value) {
        if (ch != value) {
            throw error("Expected '" + value + "' , found '" + ch + "'");
        }
        nextChar();
    }
    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }
    protected RuntimeException error(final String message) {
        return source.error(message);
    }
    protected boolean between(char l, char r) {
        return (l <= ch && ch <= r);
    }
}
