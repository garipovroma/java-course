package exp;

public class LeftShift extends AbstractOperator {
    public LeftShift (CommonExpression left, CommonExpression right) {
        super(left, right);
    }
    @Override
    protected int makeOperation(int left, int right) {
        return (left << right);
    }

    @Override
    protected double makeOperation(double left, double right) {
        return this.makeOperation((int)left, (int)right);
    }

    @Override
    protected String getOperator() {
        return "<<";
    }

    @Override
    protected int getPriority() {
        return 0;
    }
}
