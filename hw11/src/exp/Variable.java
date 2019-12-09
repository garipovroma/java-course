package exp;

public class Variable implements MainExpression, DoubleExpression, TripleExpression {
    private String name;
    public Variable(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public String toMiniString() {
        return name;
    }
    @Override
    public int getPriority() {
        return 1;
    }
    @Override
    public int evaluate(int x) {
        return x;
    }
    @Override
    public int evaluate (int x, int y, int z) {
        int result = 0;
        if (name.equals(("x"))) {
            result = x;
        }  else if (name.equals("y")) {
            result = y;
        } else if (name.equals(("z"))) {
            result = z;
        }
        return result;
    }
    @Override
    public boolean equals(Object exp) {
        if (exp != null && exp instanceof Variable) {
            return  (this.name.equals(((Variable) exp).name));
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        /*int result = 0;
        for (int i = 0; i < name.length(); i++) {
            result = result * 2047 + name.charAt(i);
        }
        return result;*/
        return name.hashCode();
    }

    @Override
    public double evaluate(double x) {
        return x;
    }
}
