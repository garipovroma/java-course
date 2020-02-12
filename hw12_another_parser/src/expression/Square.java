package expression;

public class Square implements CommonExpression {
    CommonExpression exp;
    public Square(CommonExpression exp) {
        this.exp = exp;
    }
    public static CommonExpression getSquare(CommonExpression exp) {
        if (exp instanceof Const) {
            return new Const(exp.evaluate(0) * exp.evaluate(0));
        }
        return new Square(exp);
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

    @Override
    public int evaluate(int x) {
        int val = exp.evaluate(x);
        return val * val;
    }

    @Override
    public double evaluate(double x) {
        int val = this.evaluate((int) x);
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val = exp.evaluate(x, y, z);
        return val * val;
    }
}
