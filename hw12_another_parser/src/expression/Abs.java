package expression;

public class Abs implements CommonExpression {
    CommonExpression exp;
    public Abs(CommonExpression exp) {
        this.exp = exp;
    }

    public static CommonExpression getAbs(CommonExpression expression) {
        if (expression instanceof Const) {
            int val = expression.evaluate(0);
            if (val < 0) {
                val = -val;
            }
            return new Const(val);
        }
        return new Abs(expression);
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder("square ");
        stringBuilder.append(exp.toMiniString());
        return stringBuilder.toString();
    }
    public String toString() {
        return this.toMiniString();
    }
    private int getAbs(int x) {
        return (x < 0 ? -x : x);
    }
    @Override
    public int evaluate(int x) {
        return getAbs(this.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return getAbs(this.exp.evaluate((int)x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getAbs(this.exp.evaluate(x, y, z));
    }
}
