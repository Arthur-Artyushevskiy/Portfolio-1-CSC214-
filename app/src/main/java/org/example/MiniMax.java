package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MiniMax {
    public String row, col;
    public char player = 'O';
    public char opponent = 'X';


    public boolean isMoveLeft(char[][] matrix) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (matrix[r][c] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }


    public int evaluate(char[][] b) {
        for (int r = 0; r < 3; r++) {
            if (b[r][0] == b[r][1] && b[r][1] == b[r][2]) {
                if (b[r][0] == player) return +10;
                else if (b[r][0] == opponent) return -10;
            }
        }


        for (int c = 0; c < 3; c++) {
            if (b[0][c] == b[1][c] && b[1][c] == b[2][c]) {
                if (b[0][c] == player) return +10;
                else if (b[0][c] == opponent) return -10;
            }
        }


        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player) return +10;
            else if (b[0][0] == opponent) return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player) return +10;
            else if (b[0][2] == opponent) return -10;
        }

        return 0;
    }


    public int minimax(char[][] matrix, int depth, boolean isMax) {
        int score = evaluate(matrix);

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!isMoveLeft(matrix)) return 0;

        if (isMax) {
            int best = -1000;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (matrix[r][c] == ' ') {
                        matrix[r][c] = player;
                        best = Math.max(best, minimax(matrix, depth + 1, false));
                        matrix[r][c] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (matrix[r][c] == ' ') {
                        matrix[r][c] = opponent;
                        best = Math.min(best, minimax(matrix, depth + 1, true));
                        matrix[r][c] = ' ';
                    }
                }
            }
            return best;
        }
    }


    public void findBestMove(char[][] matrix) {
        Random random = new Random();
        int roll = random.nextInt(100) + 1;

        if (roll > 0) {
            int bestVal = -1000;
            row = "0";
            col = "0";

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (matrix[r][c] == ' ') {
                        matrix[r][c] = player;
                        int moveVal = minimax(matrix, 0, false);
                        matrix[r][c] = ' ';

                        if (moveVal > bestVal) {
                            row = String.valueOf(r + 1);
                            col = String.valueOf(c + 1);
                            bestVal = moveVal;
                        }
                    }
                }
            }
        } else {

            List<int[]> availableMoves = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] == ' ') {
                        availableMoves.add(new int[]{i, j});
                    }
                }
            }

            if (!availableMoves.isEmpty()) {
                int[] move = availableMoves.get(random.nextInt(availableMoves.size()));
                row = String.valueOf(move[0] + 1);
                col = String.valueOf(move[1] + 1);
            }
        }
    }
}