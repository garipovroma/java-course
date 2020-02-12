package expression;

import expression.exceptions.CheckedDivide;
import expression.exceptions.CheckedSubtract;

import java.util.Objects;

public abstract class BinaryOperator implements CommonExpression {
    private CommonExpression left, right;
    public BinaryOperator(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }
    protected boolean checkOverflow(int left, int right) {
        switch (getOperator()) {
            case "+":
                int val = left + right;
                if ((left > 0 && right > 0 && val <= 0) || (left < 0 && right < 0 && val >= 0)) {
                    return true;
                }
                break;
            case "-":
                val = left - right;
                if ((left > 0 && right < 0 && val <= 0) || (left < 0 && right > 0 && val >= 0) ||
                        (left == 0 && right == Integer.MIN_VALUE)) {
                    return true;
                }
                break;
            case "*":
                if (left != 0 && right != 0 && ((left * right) / right != left || (left * right) / left != right)) {
                    return true;
                }
                break;
            case "/":
                if (left == Integer.MIN_VALUE && right == -1) {
                    return true;
                }
        }
        return false;
    }
    protected boolean checkDivisionByZero(int left, int right) {
        return (getOperator() == "/" && right == 0);
    }
    protected abstract int makeOperation(int left, int right);
    protected abstract double makeOperation(double left, double right);
    protected abstract String getOperator();
    protected abstract int getPriority();
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(left.toString());
        sb.append(" ");
        sb.append(this.getOperator());
        sb.append(" ");
        sb.append(right.toString());
        sb.append(")");
        return sb.toString();/*
        String left = this.left.toString();
        String right = this.right.toString();
        return "(" + left + " " + this.getOperator() + " " + right + ")";*/
    }
    private StringBuilder getExpressionString(Expression e, boolean brackets) {
        if (brackets) {
            StringBuilder sb = new StringBuilder("(");
            sb.append(e.toMiniString());
            sb.append(")");
            return sb;
            //return "(" + e.toMiniString() + ")";
        } else {
            return new StringBuilder(e.toMiniString());
        }
    }
    private boolean checkPriority(Expression e) {
        return (e instanceof BinaryOperator && ((BinaryOperator) e).getPriority() < this.getPriority());
    }
    private boolean needBracket(Expression e) {
        return  (e instanceof BinaryOperator && ((this instanceof CheckedSubtract
                && ((BinaryOperator) e).getPriority() == 1) || this instanceof CheckedDivide)) ||
                (e instanceof CheckedDivide && this.getPriority() == 2);
    }
    public String toMiniString() {
        StringBuilder sb = getExpressionString(this.left, checkPriority(this.left));
        sb.append(" ");
        sb.append(getOperator());
        sb.append(" ");
        sb.append(getExpressionString(this.right, checkPriority(this.right) || needBracket(this.right)));
        return sb.toString();
        /*return getExpressionString(this.left, checkPriority(this.left)) + " " + getOperator() + " "
                + getExpressionString(this.right, checkPriority(this.right) || needBracket(this.right));*/
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
            return ((BinaryOperator) exp).left.equals(this.left) && ((BinaryOperator) exp).right.equals(this.right)
                    && ((BinaryOperator) exp).getOperator().equals(this.getOperator());
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
