package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveGame {

    public static void saveGameLog(int xWonWins, int oWonWins, int ties, char X, char O) {
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

}

