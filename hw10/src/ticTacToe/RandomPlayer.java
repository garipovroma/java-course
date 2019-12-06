package ticTacToe;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;
    private int n, m, k;
    public RandomPlayer(final Random random, int n, int m, int k) {
        this.random = random;
        this.n = n;
        this.m = m;
        this.k = k;
    }

    public RandomPlayer(int n, int m, int k) {
        this(new Random(), n, m, k);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
