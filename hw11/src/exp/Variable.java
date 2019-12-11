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
    public int evaluate(int x) {
        return x;
    }
    @Override
    public int evaluate (int x, int y, int z) {
        int result = 0;
        switch (name) {
            case ("x"):
                result = x;
                break;
            case "y":
                result = y;
                break;
            case ("z"):
                result = z;
                break;
        }
        return result;
    }
    @Override
    public boolean equals(Object exp) {
        if (exp instanceof Variable) {
            return  (this.name.equals(((Variable) exp).name));
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public double evaluate(double x) {
        return x;
    }
}
