import java.util.Scanner;

public class Game2048 {

    public static  int score=0;
    private static void initBoard(int board[][],int SIZE) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
            }
        }
        int firstRandomRow = (int) (Math.random() * SIZE);
        int firstRandomCol = (int) (Math.random() * SIZE);
        board[firstRandomRow][firstRandomCol] = 2;
        int secondRandomRow = (int) (Math.random() * SIZE);
        int secondRandomCol = (int) (Math.random() * SIZE);
        while (secondRandomRow == firstRandomRow && secondRandomCol == firstRandomCol) {
            secondRandomRow = (int) (Math.random() * SIZE);
            secondRandomCol = (int) (Math.random() * SIZE);
        }
        board[secondRandomRow][secondRandomCol] = 2;
    }


    static void printBoard(int board[][],int SIZE) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean moveLeft(int board[][],int SIZE) {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                int k = j;
                while (k > 0 && board[i][k - 1] == 0) {
                    k--;
                }
                if (k != j) {
                    board[i][k] = board[i][j];
                    board[i][j] = 0;
                    moved = true;
                }
            }
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i][j - 1] == board[i][j]) {
                    board[i][j - 1] *= 2;
                    board[i][j] = 0;
                    score += board[i][j - 1];

                    moved = true;
                }
            }
        }
        return moved;
    }

    static boolean moveRight(int board[][],int SIZE) {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (board[i][j] == 0) {
                    continue;
                }
                int k = j;
                while (k < SIZE - 1 && board[i][k + 1] == 0) {
                    k++;
                }
                if (k != j) {
                    board[i][k] = board[i][j];
                    board[i][j] = 0;
                    moved = true;
                }
            }
            for (int j = SIZE - 2; j >= 0; j--) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i][j + 1] == board[i][j]) {
                    board[i][j + 1] *= 2;
                    board[i][j] = 0;
                    score += board[i][j + 1];

                    moved = true;
                }
            }
        }
        return moved;
    }

    static boolean moveUp(int board[][],int SIZE) {
        boolean moved = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (board[i][j] == 0) {
                    continue;
                }
                int k = i;
                while (k > 0 && board[k - 1][j] == 0) {
                    k--;
                }
                if (k != i) {
                    board[k][j] = board[i][j];
                    board[i][j] = 0;
                    moved = true;
                }
            }
            for (int i = 1; i < SIZE; i++) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i - 1][j] == board[i][j]) {
                    board[i - 1][j] *= 2;
                    board[i][j] = 0;
                    score += board[i-1][j];

                    moved = true;
                }
            }
        }
        return moved;
    }

    static boolean moveDown(int board[][],int SIZE) {
        boolean moved = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (board[i][j] == 0) {
                    continue;
                }
                int k = i;
                while (k < SIZE - 1 && board[k + 1][j] == 0) {
                    k++;
                }
                if (k != i) {
                    board[k][j] = board[i][j];

                    board[i][j] = 0;
                    moved = true;
                }
            }
            for (int i = SIZE - 2; i >= 0; i--) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i + 1][j] == board[i][j]) {
                    board[i + 1][j] *= 2;
                    board[i][j] = 0;
                    score += board[i+1][j];

                    moved = true;
                }
            }
        }
        return moved;
    }


    public static void main(String[] args) {
        final int SIZE = 4;
        int[][] board = new int[SIZE][SIZE];
        Scanner sc = new Scanner(System.in);
        initBoard(board, SIZE);
        System.out.println("Welcome to the game 2048!");
        while (true) {
            System.out.println("Use w, a, s, d to move up, left, down, right respectively");
            System.out.println("Use q to quit");
            System.out.print("The score is : "+ score + " \n");
            System.out.println("Current board:");

            printBoard(board, SIZE);
            System.out.print("Enter your move: ");
            String move = sc.nextLine();
            boolean moved = false;
            switch (move) {
                case "w":
                    moved = moveUp(board,SIZE);
                    break;
                case "a":
                    moved = moveLeft(board, SIZE);
                    break;
                case "s":
                    moved = moveDown(board, SIZE);
                    break;
                case "d":
                    moved = moveRight(board, SIZE);
                    break;
                case "q":
                    System.out.println("Thank you for playing!");
                    return;
                default:
                    System.out.println("Invalid move, try again");
                    continue;
            }

            if (moved) {
                int x = (int) (Math.random() * SIZE);
                int y = (int) (Math.random() * SIZE);
                while (board[x][y] != 0) {
                    x = (int) (Math.random() * SIZE);
                    y = (int) (Math.random() * SIZE);
                }
                board[x][y] = 2;
            } else {
                boolean gameOver = true;
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (board[i][j] == 0) {
                            gameOver = false;
                        }
                        if (i > 0 && board[i][j] == board[i - 1][j]) {
                            gameOver = false;
                        }
                        if (j > 0 && board[i][j] == board[i][j - 1]) {
                            gameOver = false;
                        }
                        if(board[i][j]==2048){
                            gameOver=false;
                        }
                    }
                }
                if (gameOver) {
                    System.out.println("Game Over!");
                    System.out.println("You have reached the end of the game. Better luck next time!");
                    return;
                }

            }

        }
    }
}