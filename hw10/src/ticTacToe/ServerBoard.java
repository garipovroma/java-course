package ticTacToe;

import java.util.Arrays;
import java.util.Map;

import static java.lang.Math.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    protected Cell turn;
    private int n, m, k, empty;
    private boolean writeIndexes;

    public TicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        writeIndexes = (max(n, m) < 10);
        empty = n * m;
    }

    @Override
    public Position getPosition() {

    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int inRow = 0, inColumn = 0, inDiag1 = 0, inDiag2 = 0;
        int x = move.getRow();
        int y = move.getColumn();
        for (int i = max(0, x - k + 1); i <= min(n - 1, x + k - 1); i++) {
            if (cells[i][y] == turn)
                inColumn++;
        }
        for (int j = max(0, y - k + 1); j <= min(m - 1, y + k - 1); j++) {
            if (cells[x][j] == turn)
                inRow++;
        }
        for (int i = 0; i < 2 * k + 1; i++) {
            int nx = x - k + 1 + i;
            int ny = y - k + 1 + i;
            if (inField(nx, ny) && cells[nx][ny] == turn)
                inDiag1++;
        }
        for (int i = 0; i < 2 * k + 1; i++) {
            int nx = x - k + 1 + i;
            int ny = y + k - 1 - i;
            if (inField(nx, ny) && cells[nx][ny] == turn)
                inDiag2++;
        }
        if (inColumn == k || inRow == k || inDiag1 == k || inDiag2 == k)
            return Result.WIN;
        empty--;
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    public boolean inField(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m);
    }
    @Override
    public boolean isValid(final Move move) {
        return inField(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell(move.getRow(), move.getColumn());
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        if (writeIndexes) {
            for (int c = 0; c < m; c++) {
                sb.append(c);
                sb.append(" ");
            }
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            if (writeIndexes) {
                sb.append(r);
            }
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
