package exp;

import exp.parser.ExpressionParser;
import exp.parser.Parser;

public class Main {

    public static void main(String[] args) {
        /*CommonExpression kek = new Subtract(
                new Multiply(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)
        );
        System.out.println(kek.evaluate(5));
        System.out.println(kek.toString());
        System.out.println(kek.toMiniString());
        CommonExpression e1 = new Multiply(new Const(2), new Variable("x"));
        CommonExpression e2 = new Multiply(new Variable("x"), new Const(2));
        System.out.println(e1.equals(e1));
        System.out.println(e1.equals(e2));
        CommonExpression ee = new Divide(new Variable("x"), new Const(-2));
        System.out.println(ee.evaluate(1));
        CommonExpression eee = new Add(new Variable("x"), new Variable("x"));
        CommonExpression eeee = new Add(new Variable("x"), new Variable("x"));
        System.out.println(eee.equals(eeee));
        CommonExpression k0 = new Add(new Variable("x"), new Const(2));
        CommonExpression k1 = new Add(new Const(2), new Variable("x"));
        CommonExpression k2 = new Add(new Variable("x"), new Const(2));
        System.out.println(k1.equals(e2));
        System.out.println(k2.equals(k0));
        System.out.println(k2.hashCode());
        CommonExpression kekus = new Subtract(new Const(1), new Subtract(new Const(2), new Const(3)));
        System.out.println(kekus.toMiniString());*/
        Parser myParser = new ExpressionParser();
        //TripleExpression res = myParser.parse("(((((reverse (reverse (x))) << ((x) * (-1504837782))))) + ((1738436142) * (y))) / (((-1121498416) + (1826953505)))");
        TripleExpression res = myParser.parse("((");
        //System.out.println(res.evaluate(-1481111453, -638704270, -532646286));
    }
}
