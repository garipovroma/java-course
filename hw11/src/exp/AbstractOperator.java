package exp;

import java.util.Objects;

public abstract class AbstractOperator implements MainExpression {
    private MainExpression left, right;
    public AbstractOperator(MainExpression left, MainExpression right) {
        this.left = left;
        this.right = right;
    }
    protected abstract int makeOperation(int left, int right);
    protected abstract double makeOperation(double left, double right);
    protected abstract String getOperator();
    protected abstract int getPriority();
    public String toString() {
        String left = this.left.toString();
        String right = this.right.toString();
        return "(" + left + " " + this.getOperator() + " " + right + ")";
    }
    private String getExpressionString(Expression e, boolean brackets) {
        if (brackets) {
            return "(" + e.toMiniString() + ")";
        } else {
            return e.toMiniString();
        }
    }
    private boolean checkPriority(Expression e) {
        return (e instanceof AbstractOperator && ((AbstractOperator) e).getPriority() < this.getPriority());
    }
    private boolean needBracket(Expression e) {
        return  (e instanceof AbstractOperator && ((this instanceof Subtract && ((AbstractOperator) e).getPriority() == 1)
                || this instanceof Divide)) ||
                (e instanceof Divide && this.getPriority() == 2);
    }
    public String toMiniString() {
        return getExpressionString(this.left, checkPriority(this.left)) + " " + getOperator() + " "
                + getExpressionString(this.right, checkPriority(this.right) || needBracket(this.right));
    }
    public int evaluate(int x) {
        int left = this.left.evaluate(x);
        int right = this.right.evaluate(x);
        return makeOperation(left, right);
    }
    public double evaluate(double x) {
        double left = this.left.evaluate(x);
        double right = this.right.evaluate(x);
        return makeOperation(left, right);
    }
    public int evaluate(int x, int y, int z) {
        int left = this.left.evaluate(x, y, z);
        int right = this.right.evaluate(x, y, z);
        return makeOperation(left, right);
    }
    @Override
    public boolean equals(Object exp) {
        if (exp != null && exp.getClass() == getClass()) {
            return ((AbstractOperator) exp).left.equals(this.left) && ((AbstractOperator) exp).right.equals(this.right)
                    && ((AbstractOperator) exp).getOperator().equals(this.getOperator());
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        int left = this.left.hashCode();
        int right = this.right.hashCode();
        return Objects.hash(left, right, this.getOperator());
    }
}
