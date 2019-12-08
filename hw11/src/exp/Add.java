package exp;

import exp.AbstractOperator;
import exp.BinaryExpression;
import exp.MainExpression;

public class Add extends AbstractOperator implements BinaryExpression {
    private MainExpression left,right;
    private int priority = 1;
    public Add (MainExpression left, MainExpression right) {
        super(left, right, "+", false, false);
        this.left = left;
        this.right = right;
    }

    @Override
    public int makeOperation(int left, int right) {
        return left + right;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
    @Override
    public MainExpression getLeft() {
        return this.left;
    }
    @Override
    public MainExpression getRight() {
        return this.right;
    }
    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public int evaluate(int x) {
        return super.evaluate(x);
    }
    @Override
    public boolean equals(Object exp) {
        return super.equals(exp);
    }
}
