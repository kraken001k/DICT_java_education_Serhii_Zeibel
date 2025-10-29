package TicTacToe;

public class TicTacToe {
    private final Board board = new Board();
    private final Player playerX = new Player('X');
    private final Player playerO = new Player('O');

    public void start() {
        board.print();

        Player currentPlayer = playerX;

        while (true) {
            int[] move = currentPlayer.getMove(board);
            board.setCell(move[0], move[1], currentPlayer.getSymbol());
            board.print();

            if (board.checkWin(currentPlayer.getSymbol())) {
                System.out.println(currentPlayer.getSymbol() + " wins");
                break;
            }

            if (!board.hasEmptyCells()) {
                System.out.println("Draw");
                break;
            }

            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }
    }
}
