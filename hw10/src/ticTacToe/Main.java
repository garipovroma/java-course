package ticTacToe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Config Format : \n n m k l fieldCode\n ^^^^" +
                "\n l = 1 - logging is ON, 0 - OFF\n ^ = {R, S, H, *} - type of player" +
                "\n fieldCode = 0 -> Square\n fieldCode = 1 -> Rhombus\n\n\n");
        try {
            int result;
            do {
                GameConfig cfg = new GameConfig();
                int n = cfg.getN();
                int m = cfg.getM();
                int k = cfg.getK();
                int p = cfg.getP();
                int fieldCode = cfg.getFieldCode();
                boolean log = cfg.getLog();
                result = (new MnkBoard(n, m, k, p, fieldCode)).play(cfg.getPlayers(), log);
                System.out.println("Game result: " + result);
            } while (result != 0);
        } catch (StringIndexOutOfBoundsException | IOException | NoSuchElementException e) {
            System.out.println("Config Error : " + e.getMessage());
        }
    }
}
