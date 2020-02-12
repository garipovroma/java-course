package expression.exceptions;

import expression.CommonExpression;
import expression.Const;

public class CheckedNegate implements CommonExpression {
    private CommonExpression exp;

    public CheckedNegate(CommonExpression exp) {
        this.exp = exp;
    }
    public static CommonExpression getNegative(CommonExpression exp) {
        if (exp instanceof Const) {
            return new Const(-exp.evaluate(0));
        } else if (exp instanceof CheckedNegate) {
            return ((CheckedNegate) exp).exp;
        }
        return new CheckedNegate(exp);
    }
    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder("-(");
        stringBuilder.append(exp.toMiniString());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
    public String toString() {
        return this.toMiniString();
    }

    @Override
    public int evaluate(int x) {
        int val = exp.evaluate(x);
        /*if (val == Integer.MIN_VALUE) {
            throw new OverflowException("-(" + val + ") - overflows");
        }*/
        return -val;
    }

    @Override
    public double evaluate(double x) {
        return -exp.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val = exp.evaluate(x, y, z);
        if (val == Integer.MIN_VALUE) {
            throw new OverflowException("-(" + val + ") - overflows");
        }
        return -val;
    }
}
