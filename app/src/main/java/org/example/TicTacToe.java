package org.example;
import java.util.Scanner;

public class TicTacToe {
    
    public char[][] matrix = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public char X;
    public char O;
    public boolean xWon = false;
    public boolean oWon = false;
    private String rowInput = "";
    private String colInput = "";

    private Scanner scanner = new Scanner(System.in);


    public TicTacToe(char X, char O) {
        this.X = X;
        this.O = O;
    }

    // helper method to change the grid based on rules
    public boolean ticChange(char c, String row, String col) {
        if (row.length() > 1 || col.length() > 1) {
            System.out.println("OUT OF BOUNDS!!!");
            return false;
        }

        if (!Character.isDigit(row.charAt(0)) || !Character.isDigit(col.charAt(0))) {
            System.out.println("USE NUMBERS FOR THE ROW AND COL!!!");
            return false;
        }

        int iRow = (row.charAt(0) - '0') - 1;
        int iCol = (col.charAt(0) - '0') - 1;

        if (iRow < 0 || iRow > 2) {
            System.out.println("OUT OF BOUNDS!!!");
            return false;
        }
        if (iCol < 0 || iCol > 2) {
            System.out.println("OUT OF BOUNDS!!!");
            return false;
        }

        if (matrix[iRow][iCol] == X || matrix[iRow][iCol] == O) {
            System.out.println("THIS SPACE IS ALREADY TAKEN, TRY ANOTHER ONE!!!");
            return false;
        }

        matrix[iRow][iCol] = c;
        return true;
    }

    // checks if one of the players won the game
    public boolean win() {
        if (checkHor() || checkVer() || checkDiagLeft() || checkDiagRight()) {
            return true;
        }
        return false;
    }

    // helper method to check if the grid is full
    public boolean full() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (matrix[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkHor() {
        for (int row = 0; row < 3; row++) {
            if (matrix[row][0] != ' ' && matrix[row][0] == matrix[row][1] && matrix[row][1] == matrix[row][2]) {
                if (matrix[row][2] == X) {
                    xWon = true;
                    return true;
                } else if (matrix[row][2] == O) {
                    oWon = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVer() {
        for (int col = 0; col < 3; col++) {
            if (matrix[0][col] != ' ' && matrix[0][col] == matrix[1][col] && matrix[1][col] == matrix[2][col]) {
                if (matrix[2][col] == X) {
                    xWon = true;
                    return true;
                } else if (matrix[2][col] == O) {
                    oWon = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagRight() {
        if (matrix[2][0] != ' ' && matrix[2][0] == matrix[1][1] && matrix[1][1] == matrix[0][2]) {
            if (matrix[0][2] == X) {
                xWon = true;
                return true;
            } else if (matrix[0][2] == O) {
                oWon = true;
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagLeft() {
        if (matrix[0][0] != ' ' && matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) {
            if (matrix[2][2] == X) {
                xWon = true;
                return true;
            } else if (matrix[2][2] == O) {
                oWon = true;
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("   1     2     3");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 3; col++) {
                System.out.print("| " + matrix[row][col] + " |" + " ");
            }
            System.out.println();
        }
    }

    public void xMove() {
        System.out.println("First Player (" + X + "):");
        boolean moveSuccess = false;
        while (!moveSuccess) {
            System.out.print("Input row (1-3): ");
            rowInput = scanner.nextLine();
            System.out.print("Input col (1-3): ");
            colInput = scanner.nextLine();
            System.out.println();
            moveSuccess = ticChange(X, rowInput, colInput);
        }
        print();
        System.out.println();
        win();
    }

    public void oMove() {
        System.out.println("Second Player (" + O + "):");
        System.out.println();
        boolean moveSuccess = false;
        while (!moveSuccess) {
            System.out.print("Input row (1-3): ");
            rowInput = scanner.nextLine();
            System.out.print("Input col (1-3): ");
            colInput = scanner.nextLine();
            System.out.println();
            moveSuccess = ticChange(O, rowInput, colInput);
        }
        System.out.println();
        win();
    }

    public void result() {
        System.out.print("The Result is: ");
        if (full() && !xWon && !oWon) {
            System.out.println("Tie!");
        } else if (xWon && !oWon) {
            System.out.println(X + " Won!");
        } else if (oWon && !xWon) {
            System.out.println(O + " Won!");
        }
    }


    public void run() {
        System.out.println("Welcome to the TIC_TAC_TOE GAME!!!");
        while (!full()) {
                if (!win()) {
                    print();
                    System.out.println();
                    if (!oWon) {
                        xMove();
                    } else break;

                    if (!win() && full()) break;

                    if (!xWon) {
                        oMove();
                    } else break;
                } else break;
            }

        System.out.println();
        print();
        result();
    }


}
