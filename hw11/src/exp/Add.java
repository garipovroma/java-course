package exp;

public class Add extends AbstractOperator implements DoubleExpression, TripleExpression {
    public Add (MainExpression left, MainExpression right) {
        super(left, right);
    }
    @Override
    public int makeOperation(int left, int right) {
        return left + right;
    }
    @Override
    public double makeOperation(double left, double right) {
        return left + right;
    }
    @Override
    public String getOperator() {
        return "+";
    }
    @Override
    public int getPriority() {
        return 1;
    }
}
