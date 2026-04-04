package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public char[][] matrix = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static char X;
    public static char O;
    public boolean xWon = false;
    public boolean oWon = false;
    private String rowInput = "";
    private String colInput = "";

    private Scanner scanner = new Scanner(System.in);


    public TicTacToe(char X, char O) {
        this.X = X;
        this.O = O;
    }

    private void resetGame(){
        for (char[] row : matrix) {
            Arrays.fill(row, ' ');
        }
        xWon = false;
        oWon = false;

    }

    // helper method to change the grid based on rules
    public boolean ticChange(char c, String row, String col) {
        if(row.isEmpty() || col.isEmpty()){
            System.out.println("NO EMPTY INPUTS");
            return false;
        }



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

    private void xMove() {
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

    private void oMove() {
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
        print();

        System.out.println();
        win();
    }

    private void result() {
        System.out.print("The Result is: ");
        if (full() && !xWon && !oWon) {
            System.out.println("Tie!");
        } else if (xWon && !oWon) {
            System.out.println(X + " Won!");
        } else if (oWon && !xWon) {
            System.out.println(O + " Won!");
        }
    }

    static int xWonNum;
    static int oWonNum;
    static int tiesNum;
    static boolean pastXLoser = true;
    static boolean pastOLoser = false;

    private void run() {
        resetGame();
        print();
        while (!full()) {
                if (!win()) {

                    System.out.println();
                    if (!oWon && !xWon) {
                        if(pastXLoser && !pastOLoser){
                            xMove();
                        }
                        else if(pastOLoser && !pastXLoser) oMove();
                    }
                    else{
                        break;
                    }

                    if (!win() && full()){
                      break;
                    }

                    if (!xWon && !oWon) {
                        if(!pastOLoser && pastXLoser){
                            oMove();
                        }
                        else if(!pastXLoser && pastOLoser) xMove();
                    } else{
                        break;
                    }
                } else break;
            }

        if(xWon){
            xWonNum++;
            pastXLoser = true;
            pastOLoser = false;
        }
        else if(oWon){
            oWonNum++;
            pastOLoser = true;
            pastXLoser = false;
        }
        else tiesNum++;

        System.out.println();
        print();
        result();
    }

    // forces a valid string input
    private String forceValidStrInput(String str){

        while(!(str.equals("yes") || str.equals("no"))){
            System.out.println("That is not a valid entry!");
            System.out.println("Would you like to play again (yes/no)?");
            str = scanner.nextLine();

        }
     return str;
    }

    public static void saveGameLog(int xWonWins, int oWonWins, int ties) {
        try (PrintWriter out = new PrintWriter(new FileWriter("game_log.txt"))) {
            out.println("--- Tic-Tac-Toe Game Log ---");
            out.println("Player " + X  + " Total Wins: " + xWonWins);
            out.println("Player " + O + " Total Wins: " + oWonWins);
            out.println("Total Tie Games: " + ties);
            System.out.println("Log successfully saved to game_log.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the log: " + e.getMessage());
        }
    }


    public void gameLoop(){
        System.out.println("Welcome to the TIC_TAC_TOE GAME!!!");
        String str;
        do{
            run();
            System.out.println("The current log is:");
            System.out.println("\n" + "Player " + X + " Wins " + xWonNum);
            System.out.println("Player " + O + " Wins " + oWonNum);
            System.out.println("Ties          " + tiesNum);
            System.out.print("Would you like to play again (yes/no)?");
            str = scanner.nextLine();
            str = forceValidStrInput(str);
            if(pastXLoser) System.out.println("This time " + X + " will go first!");
            else  System.out.println("This time " + O + " will go first!");
        }while(str.equals("yes"));
        saveGameLog(xWonNum, oWonNum, tiesNum);
        xWonNum =0;
        oWonNum = 0;
        tiesNum = 0;
        pastXLoser = true;
        pastOLoser = false;

        System.out.println("Goodbye!");

    }

}
