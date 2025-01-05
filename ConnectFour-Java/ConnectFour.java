import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    public ConnectFour() {
        board = new char[ROWS][COLS];
        currentPlayer = 'R'; // R for Red, Y for Yellow
        gameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        printBoard();

        while (!gameOver) {
            System.out.println("\nPlayer " + currentPlayer + "'s turn");
            System.out.print("Enter column (1-7): ");
            int col = scanner.nextInt() - 1;

            if (isValidMove(col)) {
                int row = dropPiece(col);
                if (checkWin(row, col)) {
                    gameOver = true;
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isBoardFull()) {
                    gameOver = true;
                    printBoard();
                    System.out.println("It's a draw!");
                } else {
                    currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
                    printBoard();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }

    private boolean isValidMove(int col) {
        return col >= 0 && col < COLS && board[0][col] == ' ';
    }

    private int dropPiece(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return row;
            }
        }
        return -1;
    }

    private boolean checkWin(int row, int col) {
        return checkHorizontal(row) || checkVertical(col) ||
                checkDiagonalRight(row, col) || checkDiagonalLeft(row, col);
    }

    private boolean checkHorizontal(int row) {
        int count = 0;
        for (int col = 0; col < COLS; col++) {
            if (board[row][col] == currentPlayer) {
                count++;
                if (count == 4)
                    return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean checkVertical(int col) {
        int count = 0;
        for (int row = 0; row < ROWS; row++) {
            if (board[row][col] == currentPlayer) {
                count++;
                if (count == 4)
                    return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean checkDiagonalRight(int row, int col) {
        int count = 0;
        int startRow = row;
        int startCol = col;

        // Move to the top-left most position of the diagonal
        while (startRow > 0 && startCol > 0) {
            startRow--;
            startCol--;
        }

        // Check diagonal from top-left to bottom-right
        while (startRow < ROWS && startCol < COLS) {
            if (board[startRow][startCol] == currentPlayer) {
                count++;
                if (count == 4)
                    return true;
            } else {
                count = 0;
            }
            startRow++;
            startCol++;
        }
        return false;
    }

    private boolean checkDiagonalLeft(int row, int col) {
        int count = 0;
        int startRow = row;
        int startCol = col;

        // Move to the top-right most position of the diagonal
        while (startRow > 0 && startCol < COLS - 1) {
            startRow--;
            startCol++;
        }

        // Check diagonal from top-right to bottom-left
        while (startRow < ROWS && startCol >= 0) {
            if (board[startRow][startCol] == currentPlayer) {
                count++;
                if (count == 4)
                    return true;
            } else {
                count = 0;
            }
            startRow++;
            startCol--;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("\n 1 2 3 4 5 6 7");
        System.out.println("---------------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Connect Four!");
        System.out.println("Player R (Red) vs Player Y (Yellow)");
        System.out.println("Take turns dropping pieces into the columns.");
        System.out.println("Get 4 in a row horizontally, vertically, or diagonally to win!");

        ConnectFour game = new ConnectFour();
        game.play();
    }
}