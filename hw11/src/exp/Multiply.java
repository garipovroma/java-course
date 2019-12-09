package exp;

public class Multiply extends AbstractOperator implements BinaryExpression, DoubleExpression, TripleExpression {
    private MainExpression left,right;
    private int priority = 2;
    public Multiply (MainExpression left, MainExpression right) {
        super(left, right, "*", false, false);
        this.left = left;
        this.right = right;
    }

    @Override
    public int makeOperation(int left, int right) {
        return left * right;
    }

    @Override
    public double makeOperation(double left, double right) {
        return left * right;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
    @Override
    public MainExpression getLeft() {
        return this.left;
    }
    @Override
    public MainExpression getRight() {
        return this.right;
    }
    @Override
    public int getPriority() {
        return this.priority;
    }
    @Override
    public int evaluate(int x) {
        return super.evaluate(x);
    }
    @Override
    public boolean equals(Object exp) {
        return super.equals(exp);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public double evaluate(double x) {
        return super.evaluate(x);
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return super.evaluate(x, y, z);
    }
}
