package ticTacToe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameConfig {
    private List<Player> players;
    private int n, m, k, p;
    private boolean log;
    public GameConfig() {
        players = new ArrayList();
        try (Scanner in = new Scanner(new File("config.cfg"));) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
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
            if (p < 2 || p > 4) {
                System.out.println("Count of players is unavailable, edit config");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Config not found");
            System.exit(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("String length should be 4");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("There are mistakes in your config, rewrite it");
            System.exit(0);
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
