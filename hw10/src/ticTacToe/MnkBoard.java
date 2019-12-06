package ticTacToe;

import java.util.List;

public class MnkBoard implements Position {
    private ServerBoard server;
    public MnkBoard(int n, int m, int k, int p, int fieldCode) {
        server = new ServerBoard(n, m, k, p, fieldCode);
    }
    public MnkBoard(ServerBoard server) {
        this.server = server;
    }
    @Override
    public boolean isValid(Move move) {
        return server.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return server.getCell(r, c);
    }

    public int play(List<Player> players, boolean log) {
        Game game = new Game(log, players);
        return game.play(server);
    }
    @Override
    public String toString() {
        return server.toString();
    }
}
