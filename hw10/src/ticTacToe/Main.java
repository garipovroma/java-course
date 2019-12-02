package ticTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        int result;
        do {
            GameConfig cfg = new GameConfig();
            int n = cfg.getN();
            int m = cfg.getM();
            int k = cfg.getK();
            int p = cfg.getP();
            boolean log = cfg.getLog();
            result = (new MnkBoard(n, m, k, p)).play(cfg.getPlayers(), log);
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
