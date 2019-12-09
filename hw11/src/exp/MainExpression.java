package exp;

public interface MainExpression extends Expression{
    public String toString();
    public String toMiniString();
    public int getPriority();
    public int evaluate(int x);
    public double evaluate(double x);
}
