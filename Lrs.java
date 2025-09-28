public class Lrs {
    static class Cell {
        int value;
        char dir;
    }

    public static void lrs(String s) {
        int n = s.length();
        Cell[][] c = new Cell[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                c[i][j] = new Cell();
                if (i == 0 || j == 0) {
                    c[i][j].value = 0;
                    c[i][j].dir = 'h';
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    c[i][j].value = c[i - 1][j - 1].value + 1;
                    c[i][j].dir = 'd';
                } else {
                    if (c[i - 1][j].value >= c[i][j - 1].value) {
                        c[i][j].value = c[i - 1][j].value;
                        c[i][j].dir = 'u';
                    } else {
                        c[i][j].value = c[i][j - 1].value;
                        c[i][j].dir = 's';
                    }
                }
            }
        }

        System.out.println("Cost Matrix:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(c[i][j].value + "" + c[i][j].dir + "\t");
            }
            System.out.println();
        }

        System.out.println("cost of LRS: " + c[n][n].value);
        System.out.print("LRS: ");
        print_lrs(c, s, n, n);
        System.out.println();
    }

    private static void print_lrs(Cell[][] c, String s, int i, int j) {
        if (i == 0 || j == 0) return;
        char dir = c[i][j].dir;
        if (dir == 'd') {
            print_lrs(c, s, i - 1, j - 1);
            System.out.print(s.charAt(i - 1));
        } else if (dir == 'u') {
            print_lrs(c, s, i - 1, j);
        } else if (dir == 's') {
            print_lrs(c, s, i, j - 1);
        }
    }

    public static void main(String[] args) {
        String s = "AABCBDC";
        lrs(s);
    }
}