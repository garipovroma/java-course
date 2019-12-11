package ticTacToe;

import java.util.List;

public class Game {
    private final boolean log;
    private final List<Player> players;

    public Game(final boolean log, List<Player> list) {
        this.log = log;
        this.players = list;
    }

    public int play(Board board) {
        if (players.size() < 2) {
            return -2;
        }
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                final int result = move(board, players.get(i), i + 1);
                if (result != -1)
                    return result;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return players.size() + 1 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
