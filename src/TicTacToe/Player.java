package TicTacToe;

import java.util.Scanner;

public class Player {
    private final char symbol;
    private final Scanner scanner = new Scanner(System.in);

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public int[] getMove(Board board) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String[] parts = scanner.nextLine().trim().split(" ");

            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int x, y;
            try {
                x = Integer.parseInt(parts[0]);
                y = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            int row = y - 1;
            int col = x - 1;

            if (!board.isCellEmpty(row, col)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            return new int[]{row, col};
        }
    }
}
