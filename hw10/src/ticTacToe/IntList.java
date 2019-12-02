import java.util.*;

public class IntList {
    private int pos = 0;
    private int[] mas = new int[1];        
    IntList(int x) {
        add(x);
    }
    IntList(int a[]) {
        for (int i = 0; i < a.length; i++) {
            add(a[i]);
        }
    }
    public void add(int x) {
        if (mas.length == pos) {            
            mas = Arrays.copyOf(mas, 2 * mas.length);
        }
        mas[pos] = x;
        pos++;
    }
    public int size() {
        return pos;
    }
    public int get(int x) {
        return mas[x];
    }
    public void reputLastElement(int x) {
        mas[pos - 1] = x;
    }
    public String toStr() {
        StringBuilder res = new StringBuilder();
        if (pos > 0) {
            res.append(Integer.toString(mas[0]));            
        }
        for (int i = 1; i < pos; i++) {
            res.append(" ");
            res.append(Integer.toString(mas[i]));
        }
        return res.toString();
    }
    public int getLastElement() {
        return mas[pos - 1];
    }    
}
