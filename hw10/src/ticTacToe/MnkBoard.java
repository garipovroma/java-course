package ticTacToe;

public class MNKBoard implements Position {
    private ServerBoard server;
    public MNKBoard(int n, int m, int k) {
        server = new ServerBoard(n, m, k);
    }

    @Override
    public boolean isValid(Move move) {
        return server.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return server.getCell(r, c);
    }

    public int play() {
        Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        return game.play(server);
    }
}
