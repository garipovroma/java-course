package exp;

public interface BinaryExpression extends MainExpression {
    public MainExpression getRight();
    public MainExpression getLeft();
}
