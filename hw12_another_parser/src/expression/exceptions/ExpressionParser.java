package expression.exceptions;

import expression.*;

import java.util.Map;
import java.util.Set;

public class ExpressionParser extends BaseParser implements Parser {
    Token curToken = Token.NUM;
    private int curConst;
    private String variableName;
    private boolean getNegativeConst = false;
    private static final Set<Character> variablesName = Set.of(
            'x', 'y', 'z'
    );

    private static final int highestPriority = 2;
    private static final Map<Token, Integer> priority = Map.of(
            Token.MUL, 1,
            Token.DIV, 1,
            Token.ADD, 2,
            Token.SUB, 2
    );
    private static final Map<Integer, Set<Token>> getOperationsByPriority = Map.of(
            1, Set.of(Token.MUL, Token.DIV),
            2, Set.of(Token.ADD, Token.SUB)
    );
    private static final int lowestPriority = 0;

    @Override
    public CommonExpression parse(String expression) throws Exception {
        createSource(new StringSource(expression));
        nextChar();
        getToken();
        return parseExpression(highestPriority, false);
    }
    private Token getConst() {
        StringBuilder value = new StringBuilder();
        if (getNegativeConst) {
            value.append("-");
            getNegativeConst = false;
        }
        while (between('0', '9')) {
            value.append(ch);
            nextChar();
        }
        try {
            curConst = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            throw error("Illegal constant :" + value.toString());
        }
        return curToken = Token.NUM;
    }
    private Token getToken() {
        if (between('0', '9')) {
            return getConst();
        } else {
            switch  (ch) {
                case '\0':
                    return curToken = Token.END;
                case '*':
                    nextChar();
                    return curToken = Token.MUL;
                case '/':
                    nextChar();
                    return curToken = Token.DIV;
                case '+':
                    nextChar();
                    return curToken = Token.ADD;
                case '-':
                    nextChar();
                    return curToken = Token.SUB;
                case '(':
                    nextChar();
                    return curToken = Token.LBRACKET;
                case ')':
                    nextChar();
                    return curToken = Token.RBRACKET;
                default:
                    if (Character.isAlphabetic(ch)) {
                        StringBuilder name = new StringBuilder();
                        if (variablesName.contains(ch)) {
                            name.append(ch);
                            variableName = name.toString();
                            nextChar();
                            return curToken = Token.NAME;
                        } else {
                            throw new UndefinedVariableException(variableName.toString() +
                                    " - undefined variable");
                        }
                    } else {
                        throw new UndefinedSignException(ch + " - undefined sign");
                    }
            }
        }
    }

    private CommonExpression parsePrimeExpression(boolean get) {
        if (get) {
            getToken();
        }
        switch (curToken) {
            case NUM:
                CommonExpression cur = new Const(curConst);
                getToken();
                return cur;
            case NAME:
                CommonExpression variable = new Variable(variableName);
                getToken();
                return variable;
            case SUB:
                if (testBetween('0', '9')) {
                    getNegativeConst = true;
                    getToken();
                    cur = new Const(curConst);
                    getToken();
                    return cur;
                }
                return CheckedNegate.getNegative(parsePrimeExpression(true));
            case LBRACKET:
                cur = parseExpression(2, true);
                if (curToken != Token.RBRACKET) {
                    throw new BracketNotFoundException("Bracket not found after :" + cur.toString());
                }
                getToken();
                return cur;
            default:
                throw new UndefinedSignException(ch + " - undefined sign");
        }
    }

    private CommonExpression parseExpression(int priority, boolean get) {
        if (priority == lowestPriority) {
            return parsePrimeExpression(get);
        } else {
            CommonExpression res = parseExpression(priority - 1, get);
            for ( ; ; ) {
                Token curTok = curToken;
                if (getOperationsByPriority.get(priority).contains(curToken)) {
                    CommonExpression curExpression = parseExpression(priority - 1, true);
                    res = makeExpression(res, curExpression, curTok);
                } else {
                    return res;
                }
            }
        }
    }
    private CommonExpression makeExpression(CommonExpression left, CommonExpression right, Token operator) {
        switch (operator) {
            case ADD:
                return new CheckedAdd(left, right);
            case SUB:
                return new CheckedSubtract(left, right);
            case MUL:
                return new CheckedMultiply(left, right);
            case DIV:
                return new CheckedDivide(left, right);
        }
        throw new UndefinedOperatorException(operator + "- undefined operator");
    }
}
