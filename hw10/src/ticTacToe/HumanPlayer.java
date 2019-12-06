package ticTacToe;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    private int n, m, k;
    public HumanPlayer(final PrintStream out, final Scanner in, int n, int m, int k) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer(int n, int m, int k) {
        this(System.out, new Scanner(System.in), n, m, k);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            IntList list = new IntList();
            while (lineScanner.hasNextInt()) {
                list.add(lineScanner.nextInt());
            }
            if (list.size() != 2) {
                out.println(line + " Isn't a move! You should enter 2 integers !");
                continue;
            }
            final Move move = new Move(list.get(0), list.get(1), cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
