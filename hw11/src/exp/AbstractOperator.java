package exp;

import java.util.Objects;

public abstract class AbstractOperator implements MainExpression {
    private MainExpression left, right;
    private String operationSym;
    private boolean isDivide;
    private boolean isSubtract;
    public AbstractOperator(MainExpression left, MainExpression right, String sym,
                            boolean isSubtract, boolean isDivide) {
        this.left = left;
        this.right = right;
        this.operationSym = sym;
        this.isSubtract = isSubtract;
        this.isDivide = isDivide;
    }
    public abstract int makeOperation(int left, int right);
    public abstract double makeOperation(double left, double right);
    public String toString() {
        String left = this.left.toString();
        String right = this.right.toString();
        return "(" + left + " " + this.operationSym + " " + right + ")";
    }
    public String toMiniString() {
        String left = this.left.toMiniString();
        String right = this.right.toMiniString();
        int p1 = this.left.getPriority();
        int p2 = this.right.getPriority();
        boolean f1 = this.left instanceof AbstractOperator;
        boolean f2 = this.right instanceof AbstractOperator;
        String leftStr = left, rightStr = right;
        if (f1 && f2) {
            if (p1 == p2) {
                if (this.isSubtract && this.getPriority() == this.left.getPriority()) {
                    rightStr = "(" + right + ")";
                }
                if (this.isDivide) {
                    rightStr = "(" + right + ")";
                    if (this.left.getPriority() != this.getPriority()) {
                        leftStr = "(" + left + ")";
                    }
                }
                if (!this.isDivide && this.getPriority() == 2) {
                    if (this.left.getPriority() == 1) {
                        rightStr = "(" + right + ")";
                        leftStr = "(" + left + ")";
                    }
                    if (this.left.getPriority() == 2 && ((AbstractOperator)this.right).isDivide) {
                        rightStr = "(" + right + ")";
                    }
                }
            } else if (p1 > p2) {
                if (this.getPriority() == 2) {
                    rightStr = "(" + right + ")";
                }
            } else {
                if (this.getPriority() == 2 || this.isSubtract && this.left.getPriority() != 1) {
                    leftStr = "(" + left + ")";
                }
                if (((AbstractOperator)this.right).getPriority() == 2 && this.getPriority() != 1) {
                    rightStr = "(" + right + ")";
                }
            }
        } else if (f1) {
            if (this.getPriority() == 2 && this.left.getPriority() == 1) {
                leftStr = "(" + left + ")";
            }
        } else if (f2) {
            if (this.isSubtract && this.getPriority() == 1 && this.right.getPriority() != 2
                    || ((AbstractOperator)this.right).isDivide && this.getPriority() != 1 || this.isDivide ||
                    this.getPriority() == 2 && !this.isDivide && this.right.getPriority() == 1) {
                rightStr = "(" + right + ")";
            }
        }
        String result = leftStr + " " + operationSym + " " + rightStr;
        return result;
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
    @Override
    public boolean equals(Object exp) {
        //if (exp != null && exp instanceof AbstractOperator) {
        if (exp != null && exp.getClass() == getClass()) {
            AbstractOperator cur = (AbstractOperator) exp;
            boolean ok1 = cur.left.equals(this.left);
            boolean ok2 = cur.right.equals(this.right);
            boolean ok3 = cur.operationSym.equals(this.operationSym);
            return ((AbstractOperator) exp).left.equals(this.left) && ((AbstractOperator) exp).right.equals(this.right)
                    && ((AbstractOperator) exp).operationSym.equals(this.operationSym);
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        // return Objects.hashCode(left) * 2047 * 2047 + Objects.hashCode(operationSym) * 2047 + Objects.hashCode(right);
        int left = this.left.hashCode();
        int right = this.right.hashCode();
        //return (left * 2047 * 2047) + operationSym.hashCode() * 2047 + right;
        //return left * 57 + right * 57 * 57 + operationSym.hashCode();
        return Objects.hash(left, right, operationSym);
    }
}
