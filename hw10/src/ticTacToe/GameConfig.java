package ticTacToe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.min;

public class GameConfig {
    private List<Player> players;
    private int n, m, k, p;
    private int fieldCode;
    private boolean log;
    public GameConfig() throws IOException {
        players = new ArrayList<>();
        try (Scanner in = new Scanner(new File("config.cfg"));) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();

            if (k > min(n, m)) {
                throw new IOException("There are mistakes in your config, edit it");
            }
            if (n * m > 2000 || n * m == 0) {
                throw new IOException("There are mistakes in your config, edit it");
            }
            int f = in.nextInt();
            fieldCode = in.nextInt();
            if (n != m && fieldCode == 1) {
                throw new IOException("There are mistakes in your config, edit it. If fieldCode = 1, n should be = m");
            }
            if (f == 1) {
                log = true;
            } else {
                log = false;
            }
            String s = in.next().toUpperCase();
            p = 0;
            for (int i = 0; i < 4; i++) {
                if (s.charAt(i) == '*') {
                    continue;
                }
                if (s.charAt(i) == 'R') {
                    players.add(new RandomPlayer(n, m, k));
                } else if (s.charAt(i) == 'H') {
                    players.add(new HumanPlayer(n, m, k));
                } else if (s.charAt(i) == 'S') {
                    players.add(new SequentialPlayer(n, m, k));
                } else {
                    throw new IOException("Players string can contains only R S H and *");
                }
                p++;
            }
            if (p < 2) {
                throw new IOException("There are mistakes in your config, edit it");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Config not found\nConfig Format : \n n m k l fieldCode\n ^^^^" +
                    "\n l = 1 - logging is ON, 0 - OFF\n ^ = {R, S, H, *} - type of player" +
                    "\n fieldCode = 0 -> Square\nfieldCode = 1 -> Rhombus");

        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("There are mistakes in your config, edit it");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("There are mistakes in your config, edit it");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("There are mistakes in your config, edit it");
        }
    }
    public List <Player> getPlayers() {
        return this.players;
    }
    public int getN() {
        return this.n;
    }
    public int getM() {
        return this.m;
    }
    public int getK() {
        return this.k;
    }
    public int getP() {
        return this.p;
    }
    public int getFieldCode() {
        return this.fieldCode;
    }
    public boolean getLog() {
        return this.log;
    }
}
