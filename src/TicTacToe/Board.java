package TicTacToe;

public class Board {
    private final char[][] field = new char[3][3];

    public Board() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                field[i][j] = '_';
    }

    public void print() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(STR."\{field[i][j] == '_' ? ' ' : field[i][j]} ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean isCellEmpty(int row, int col) {
        return field[row][col] == '_';
    }

    public void setCell(int row, int col, char symbol) {
        field[row][col] = symbol;
    }

    public boolean checkWin(char s) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == s && field[i][1] == s && field[i][2] == s) return true;
            if (field[0][i] == s && field[1][i] == s && field[2][i] == s) return true;
        }
        return (field[0][0] == s && field[1][1] == s && field[2][2] == s)
                || (field[0][2] == s && field[1][1] == s && field[2][0] == s);
    }

    public boolean hasEmptyCells() {
        for (char[] row : field)
            for (char c : row)
                if (c == '_') return true;
        return false;
    }
}
