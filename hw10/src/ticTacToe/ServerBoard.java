package ticTacToe;

import java.util.Arrays;
import java.util.Map;

import static java.lang.Math.*;

public class ServerBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.T, '-',
            Cell.L, '|'
    );
    private  Map<Cell, Cell> NEXTCELL;
    private final Cell[][] cells;
    private boolean ok[][];
    protected Cell turn;
    private int n, m, k, empty, fieldCode;
    private boolean writeIndexes;
    private IntList cur[] = new IntList[4];
    public ServerBoard(int n, int m, int k, int p, int fieldCode) {
        cur[0] = new IntList();
        cur[1] = new IntList();
        cur[2] = new IntList();
        cur[3] = new IntList();
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];
        this.ok = new boolean[n][m];
        this.fieldCode = fieldCode;

        int l = n / 2 - (1 - n % 2), r = n / 2;
        empty = n * m;
        if (fieldCode == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    ok[i][j] = false;
                }
            }
            for (int i = 0; i < n / 2; i++) {
                for (int j = l; j <= r; j++) {
                    ok[i][j] = true;
                }
                l--;
                r++;
            }
            l += (1 - n % 2);
            r -= (1 - n % 2);
            for (int i = n / 2; i < n; i++) {
                for (int j = l; j <= r; j++) {
                    ok[i][j] = true;
                }
                l++;
                r--;
            }
        } else if (fieldCode == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    ok[i][j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!ok[i][j]) {
                    empty--;
                }
            }
        }
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        writeIndexes = (max(n, m) < 10);
        if (p == 2) {
            NEXTCELL = Map.of(
                    Cell.X, Cell.O,
                    Cell.O, Cell.X
            );
        } else if (p == 3) {
            NEXTCELL = Map.of(
                    Cell.X, Cell.O,
                    Cell.O, Cell.T,
                    Cell.T, Cell.X
            );
        } else if (p == 4) {
            NEXTCELL = Map.of(
                    Cell.X, Cell.O,
                    Cell.O, Cell.T,
                    Cell.T, Cell.L,
                    Cell.L, Cell.X
            );
        }
    }

    @Override
    public Position getPosition() {
        return new MnkBoard(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }
    public void go0(IntList cur, int x, int y, int l, int r, boolean ok) {
        for (int i = l; i <= r; i++) {
            if (ok) {
                if (cells[i][y] == turn) {
                    cur.add(1);
                } else {
                    cur.add(0);
                }
            } else {
                if (cells[x][i] == turn) {
                    cur.add(1);
                } else {
                    cur.add(0);
                }
            }
        }
    }
    public void go2(IntList cur, int x, int y, int sign) {
        for (int i = 0; i < 2 * k + 1; i++) {
            int nx = x - k + 1 + i;
            int ny = y + (-k + 1 + i) * sign;
            if (inField(nx, ny) && cells[nx][ny] == turn) {
                cur.add(1);
            } else {
                cur.add(0);
            }
        }
    }
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cur[0].clear();
        cur[1].clear();
        cur[2].clear();
        cur[3].clear();
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int x = move.getRow();
        int y = move.getColumn();
        go0(cur[0], x, y, max(0, x - k + 1), min(n - 1, x + k - 1), true);
        go0(cur[1], x, y, max(0, y - k + 1), min(m - 1, y + k - 1), false);
        go2(cur[2], x, y, 1);
        go2(cur[3], x, y, -1);
        if (check(cur[0]) || check(cur[1]) || check(cur[2]) || check(cur[3]))
            return Result.WIN;
        empty--;
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = NEXTCELL.get(turn);
        return Result.UNKNOWN;
    }

    private boolean check (IntList mas) {
        int cur = -1;
        int mx = 0;
        for (int i = 0; i < mas.size(); i++) {
            mx = max(mx, cur);
            if (mas.get(i) == 1 && cur == -1) {
                cur = 1;
            } else if (mas.get(i) == 1 && cur != -1) {
                cur++;
            } else if (mas.get(i) == 0) {
                cur = -1;
            }
        }
        mx = max(mx, cur);
        return (mx == k);
    }

    public boolean inField(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m && ok[x][y]);
    }
    public boolean isValid(final Move move) {
        return inField(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

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
                if (!ok[r][c]) {
                    sb.append(" ");
                } else {
                    sb.append(SYMBOLS.get(cells[r][c]));
                }
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
