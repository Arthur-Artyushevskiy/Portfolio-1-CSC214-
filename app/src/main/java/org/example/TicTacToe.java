package org.example;

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
    public static Renderer printer;
    private String rowInput = "";
    private String colInput = "";


    private final Scanner scanner = new Scanner(System.in);


    public TicTacToe(char X, char O) {
        TicTacToe.X = X;
        TicTacToe.O = O;
        TicTacToe.printer = new Renderer();
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
        return checkHor() || checkVer() || checkDiagLeft() || checkDiagRight();
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

        printer.print(matrix);
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

        printer.print(matrix);

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

    private void run_Human_vs_Human() {

        resetGame();
        printer.print(matrix);
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
            pastXLoser = false;
            pastOLoser = true;
        }
        else if(oWon){
            oWonNum++;
            pastOLoser = false;
            pastXLoser = true;
        }
        else tiesNum++;

        System.out.println();
        printer.print(matrix);
        result();
    }

    static MiniMax AI;

    private void run_AI_vs_Human(){
        AI = new MiniMax();

        resetGame();
        printer.print(matrix);
        while (!full()) {
            if (!win()) {

                System.out.println();
                if (!oWon) {
                    System.out.println("AI makes its move!");

                    AI.findBestMove(matrix);
                    ticChange(X, AI.row, AI.col);
                    printer.print(matrix);
                }
                else{
                    break;
                }


                if (!win() && full()){
                    break;
                }

                if (!xWon) {
                    oMove();

                } else{
                    break;
                }

            } else break;

        }

        if(xWon){xWonNum++;}
        else if(oWon){oWonNum++;}
        else tiesNum++;

        System.out.println();
        printer.print(matrix);
        result();
    }

    private void run_Human_vs_AI(){
        AI = new MiniMax();

        resetGame();
        printer.print(matrix);
        while (!full()) {
            if (!win()) {

                System.out.println();
                if (!oWon) {
                    xMove();
                }
                else{
                    break;
                }


                if (!win() && full()){
                    break;
                }

                if (!xWon) {
                    System.out.println("AI makes its move!");

                    AI.findBestMove(matrix);
                    ticChange(O, AI.row, AI.col);
                    printer.print(matrix);
                } else{
                    break;
                }

            } else break;

        }

        if(xWon){xWonNum++;}
        else if(oWon){oWonNum++;}
        else tiesNum++;

        System.out.println();
        printer.print(matrix);
        result();
    }


    // forces a valid string input
    private String forceValidStrInput(String str){

        while(!(str.equals("yes") || str.equals("no"))){
            System.out.println("That is not a valid entry!");
            System.out.print("Would you like to play again (yes/no)?");
            str = scanner.nextLine();

        }
     return str;
    }

    private String gameModeValidator(String str){

        while(!(str.equals("1") || str.equals("2") || str.equals("3"))){
            System.out.println("That is not a valid entry!");
            System.out.println("What kind of game would you like to play?" + "\n");

            System.out.println("1. Human vs. Human");
            System.out.println("2. Human vs. Computer");
            System.out.println("3. Computer vs. Human");

            System.out.print("What is your selection? ");
            str = scanner.nextLine();

        }
        return str;
    }

    private void Human_vs_Human_Game_Loop(){
        String gameStatus;
        do{
            run_Human_vs_Human();
            System.out.println("The current log is:");
            System.out.println("\n" + "Player " + X + " Wins " + xWonNum);
            System.out.println("Player " + O + " Wins " + oWonNum);
            System.out.println("Ties          " + tiesNum);
            System.out.print("Would you like to play again (yes/no)?");

            gameStatus = scanner.nextLine();
            gameStatus = forceValidStrInput(gameStatus);

            if(pastXLoser) System.out.println("This time " + X + " will go first!");
            else  System.out.println("This time " + O + " will go first!");
        }while(gameStatus.equals("yes"));
    }

    private void Human_vs_AI_Game_Loop(){
        String gameStatus;
        do{
            run_Human_vs_AI();
            System.out.println("The current log is:");
            System.out.println("\n" + "Player " + X + " Wins " + xWonNum);
            System.out.println("AI       Wins " + oWonNum);
            System.out.println("Ties          " + tiesNum);
            System.out.print("Would you like to play again (yes/no)?");

            gameStatus = scanner.nextLine();
            gameStatus = forceValidStrInput(gameStatus);

        }while(gameStatus.equals("yes"));
    }

    private void AI_vs_Human_Game_Loop(){
        String gameStatus;
        do{
            run_AI_vs_Human();
            System.out.println("The current log is:");
            System.out.println("\n" + "AI " + X + " Wins " + xWonNum);
            System.out.println("AI " + O + " Wins " + oWonNum);
            System.out.println("Ties          " + tiesNum);
            System.out.print("Would you like to play again (yes/no)?");

            gameStatus = scanner.nextLine();
            gameStatus = forceValidStrInput(gameStatus);

        }while(gameStatus.equals("yes"));
    }

    public void gameLoop(){

        System.out.println("Welcome to the TIC_TAC_TOE GAME!!!");

        String gameMode;
        System.out.println("What kind of game would you like to play?" + "\n");

        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");

        System.out.print("What is your selection? ");

        gameMode = scanner.nextLine();
        gameMode = gameModeValidator(gameMode);

        if(gameMode.equals("1")) Human_vs_Human_Game_Loop();
        else if (gameMode.equals("2")) Human_vs_AI_Game_Loop();
        else if(gameMode.equals("3")) AI_vs_Human_Game_Loop();


        SaveGame saveGame = new SaveGame();
        saveGame.saveGameLog(xWonNum, oWonNum, tiesNum, X, O);
        xWonNum =0;
        oWonNum = 0;
        tiesNum = 0;
        pastXLoser = true;
        pastOLoser = false;

        System.out.println("Goodbye!");

    }

}
