package exp;

import java.util.Objects;

public class Const implements MainExpression, DoubleExpression {
    private int value;
    private double dValue;
    private boolean isDouble = false;
    public Const (int value) {
        this.value = value;
        this.dValue = value;
    }
    public Const (double dValue) {
        this.dValue = dValue;
        this.value = (int)dValue;
        this.isDouble = true;
    }

    @Override
    public String toString() {
        if (this.isDouble) {
            return Double.toString(dValue);
        } else {
            return Integer.toString(value);
        }
    }
    @Override
    public String toMiniString() {
        if (this.isDouble) {
            return Double.toString(dValue);
        } else {
            return Integer.toString(value);
        }
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int evaluate(int x) {
        return this.value;
    }
    @Override
    public boolean equals(Object exp) {
        if (exp != null && exp instanceof Const) {
            return  (this.value == ((Const) exp).value);
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public double evaluate(double x) {
        return this.dValue;
    }
}
