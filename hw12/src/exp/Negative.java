package exp;

public class Negative implements CommonExpression {
    private CommonExpression exp;

    public Negative (CommonExpression exp) {
        this.exp = exp;
    }
    public static CommonExpression getNegative(CommonExpression exp) {
        if (exp instanceof Const) {
            return new Const(-exp.evaluate(0));
        } else if (exp instanceof Negative) {
            return ((Negative) exp).exp;
        }
        return new Negative(exp);
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
        return -exp.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return -exp.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -exp.evaluate(x, y, z);
    }
}
