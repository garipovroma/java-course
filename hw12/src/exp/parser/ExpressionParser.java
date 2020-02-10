package exp.parser;

import exp.*;

import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {
    private String curOperator = ")";
    private static final int highestPriority = 3;
    private static final Map<String, Integer> priority = Map.of(
            "*", 1,
            "/", 1,
            "+", 2,
            "-", 2,
            ")", highestPriority + 1,
            ">>", 3,
            "<<", 3,
            "abs", 2,
            "square", 2
    );
    private static final Map<Character, String> getOperatorByFirstChar = Map.of(
            '*', "*",
            '/', "/",
            '-', "-",
            '+', "+",
            ')', ")",
            '>', ">>",
            '<', "<<",
            'a', "abs",
            's', "square"
    );
    private static final int lowestPriority = 0;

    public CommonExpression parse(String string) {
        createSource(new StringSource(string + ")"));
        nextChar();
        return getExpression(highestPriority);
    }
    private CommonExpression getExpression(int curPriority) {
        if (curPriority == lowestPriority) {
            if (between('a', 'a')) {
                expect("abs");
                return Abs.getAbs(getExpression(0));
            } else if (between('s', 's')) {
                expect("square");
                return Square.getSquare(getExpression(0));
            } else if (test('-')) {
                if (between('0', '9')) {
                    return getConst(true);
                } else {
                    return Negative.getNegative(getExpression(0));
                }
            } else {
                if (between('0', '9')) {
                    return getConst(false);
                } else if (test('(')) {
                    return getExpression(highestPriority);
                } else {
                    return getVariable();
                }
            }
        } else {
            CommonExpression expression = getExpression(curPriority - 1);
            while (priority.get(curOperator) == curPriority) {
                String operator = curOperator;
                expression = makeExpression(expression, getExpression(curPriority - 1), operator);
            }
            if (curPriority == highestPriority) {
                testOperator();
            }
            return expression;
        }
    }

    private CommonExpression makeExpression(CommonExpression left, CommonExpression right, String operator) {
        switch (operator) {
            case "+":
                return new Add(left, right);
            case "-":
                return new Subtract(left, right);
            case "*":
                return new Multiply(left, right);
            case "/":
                return new Divide(left, right);
            case "<<":
                return new LeftShift(left, right);
            case ">>":
                return new RightShift(left, right);
        }
        throw error("Unsupported operator found :" + curOperator);
    }

    private CommonExpression getVariable() {
        StringBuilder variableName = new StringBuilder();
        while (!testOperator()) {
            variableName.append(ch);
            nextChar();
        }
        return new Variable(variableName.toString());
    }

    private CommonExpression getConst(boolean isNegative) {
        StringBuilder constant = new StringBuilder();
        if (isNegative) {
            constant.append("-");
        }
        while (between('0', '9')) {
            constant.append(ch);
            nextChar();
        }
        testOperator();
        try {
            return new Const(Integer.parseInt(constant.toString()));
        } catch (NumberFormatException e) {
            throw error("Illegal constant :" + constant.toString());
        }
    }
    private boolean testOperator() {
        if (!getOperatorByFirstChar.containsKey(ch)) {
            return false;
        }
        getOperator();
        return true;
    }
    private void getOperator() {
        String operator = getOperatorByFirstChar.get(ch);
        expect(operator);
        curOperator = operator;
    }
}
