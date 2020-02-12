package expression;

public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {
    public String toString();
    public String toMiniString();
    public int evaluate(int x);
    public double evaluate(double x);
    public int evaluate(int x, int y, int z);
}
