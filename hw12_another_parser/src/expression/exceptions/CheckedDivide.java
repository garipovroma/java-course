package expression.exceptions;

import expression.BinaryOperator;
import expression.CommonExpression;

public class CheckedDivide extends BinaryOperator {
    public CheckedDivide (CommonExpression left, CommonExpression right) {
        super(left, right);
    }
    @Override
    public int makeOperation(int left, int right) {
        /*if (super.checkOverflow(left, right)) {
            throw new OverflowException(left + " / " + right + " - overflows");
        }
        if (super.checkDivisionByZero(left, right)) {
            throw new DivideByZeroException(left + " / " + right + " - division by zero");
        }*/
        return left / right;
    }
    @Override
    public double makeOperation(double left, double right) {
        return left / right;
    }
    @Override
    public String getOperator() {
        return "/";
    }
    @Override
    public int getPriority() {
        return 2;
    }
}
