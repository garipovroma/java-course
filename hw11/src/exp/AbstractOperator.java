package exp;

import java.util.Objects;

public abstract class AbstractOperator {
    private MainExpression left, right;
    private String operationSym;
    private boolean needRight;
    private boolean needLeft;
    public AbstractOperator(MainExpression left, MainExpression right, String sym, boolean needRight, boolean needLeft) {
        this.left = left;
        this.right = right;
        this.operationSym = sym;
        this.needRight = needRight;
        this.needLeft = needLeft;
    }
    public abstract int makeOperation(int left, int right);
    public String toString() {
        String left = this.left.toString();
        String right = this.right.toString();
        return "(" + left + " " + this.operationSym + " " + right + ")";
    }
    public String toMiniString() {
        String left = this.left.toMiniString();
        String right = this.right.toMiniString();
        int lPrior = this.left.getPriority();
        int rPrior = this.right.getPriority();
        boolean f1 = this.left instanceof AbstractOperator;
        boolean f2 = this.right instanceof AbstractOperator;
        String result = "";
        if (f1 && f2 && lPrior != rPrior) {
            if (lPrior > rPrior) {
                result = "(" + left + ") " + operationSym + " " + right;
            } else if (lPrior < rPrior) {
                result = left + " " + operationSym + " (" + right + ")";
            } else if (((AbstractOperator)this.right).needRight) {
                result = left + " " + operationSym + " (" + right + ")";
            } else if (((AbstractOperator)this.left).needLeft) {
                result = "(" + left + ") " + operationSym + " " + right;
            }
        } else {
            if (f2 && ((AbstractOperator)this.right).needRight) {
                result = left + " " + operationSym + " (" + right + ")";
            } else
                result = left + " " + operationSym + " " + right;
        }
        return result;
    }
    public int evaluate(int x) {
        int left = this.left.evaluate(x);
        int right = this.right.evaluate(x);
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
