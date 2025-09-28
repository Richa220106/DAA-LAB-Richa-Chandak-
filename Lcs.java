public class Lcs {
    static class Cell {
        int value;
        char dir;
    }

    public static void lcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        Cell[][] c = new Cell[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                c[i][j] = new Cell();
                if (i == 0 || j == 0) {
                    c[i][j].value = 0;
                    c[i][j].dir = 'h';
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
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
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(c[i][j].value + "" + c[i][j].dir + "\t");
            }
            System.out.println();
        }

        System.out.println("cost of LCS: " + c[m][n].value);
        System.out.print("LCS: ");
        print_lcs(c, a, m, n);
        System.out.println();
    }

    private static void print_lcs(Cell[][] c, String a, int i, int j) {
        if (i == 0 || j == 0) return;
        char dir = c[i][j].dir;
        if (dir == 'd') {
            print_lcs(c, a, i - 1, j - 1);
            System.out.print(a.charAt(i - 1));
        } else if (dir == 'u') {
            print_lcs(c, a, i - 1, j);
        } else if (dir == 's') {
            print_lcs(c, a, i, j - 1);
        }
    }

    public static void main(String[] args) {
        String x = "AGCCCTAAGGGCTACCTAGCTT";
        String y = "GACAGCCTACAAGCGTTAGCTTG";
        lcs(x, y);
    }
}