package exp;

import java.util.Objects;

public class Const implements MainExpression {
    private int value;
    public Const (int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
    @Override
    public String toMiniString() {
        return Integer.toString(value);
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
}
