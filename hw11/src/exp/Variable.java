package exp;

public class Variable implements MainExpression {
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
}
