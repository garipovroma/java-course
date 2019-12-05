package ticTacToe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.min;

public class GameConfig {
    private List<Player> players;
    private int n, m, k, p;
    private boolean log;
    public GameConfig() throws IOException {
        players = new ArrayList<>();
        try (Scanner in = new Scanner(new File("config.cfg"));) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            if (k > min(n, m)) {
                throw new IOException("k can't be greater than min(n, m)");
            }
            int f = in.nextInt();
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
                }
                p++;
            }
            if (p < 2) {
                //System.out.println("Count of players is unavailable, edit config");
                throw new IOException("Count of players is unavailable, edit config");
                // System.exit(0);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Config not found\nConfig Format : \n n m k l \n ^^^^" +
                    "\n l = 1 - logging is ON, 0 - OFF\n ^ = {R, S, H, *} - type of player");

        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Players-String length should be 4");
        } catch (IOException e) {
            throw new IOException("There are mistakes in your config, rewrite it");
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
    public boolean getLog() {
        return this.log;
    }
}
