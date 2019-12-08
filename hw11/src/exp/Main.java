package exp;

public class Main {

    public static void main(String[] args) {
        MainExpression kek = new Subtract(
                new Multiply(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)
        );
        System.out.println(kek.evaluate(5));
        System.out.println(kek.toString());
        System.out.println(kek.toMiniString());
        MainExpression e1 = new Multiply(new Const(2), new Variable("x"));
        MainExpression e2 = new Multiply(new Variable("x"), new Const(2));
        System.out.println(e1.equals(e1));
        System.out.println(e1.equals(e2));
        MainExpression ee = new Divide(new Variable("x"), new Const(-2));
        System.out.println(ee.evaluate(1));
        MainExpression eee = new Add(new Variable("x"), new Variable("x"));
        MainExpression eeee = new Add(new Variable("x"), new Variable("x"));
        System.out.println(eee.equals(eeee));
        MainExpression k0 = new Add(new Variable("x"), new Const(2));
        MainExpression k1 = new Add(new Const(2), new Variable("x"));
        MainExpression k2 = new Add(new Variable("x"), new Const(2));
        System.out.println(k1.equals(e2));
        System.out.println(k2.equals(k0));
        System.out.println(k2.hashCode());
        MainExpression kekus = new Subtract(new Const(1), new Subtract(new Const(2), new Const(3)));
        System.out.println(kekus.toMiniString());
    }
}
