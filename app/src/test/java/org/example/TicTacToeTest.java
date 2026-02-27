package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {
    private TicTacToe game;

    @BeforeEach
    void setUp() {
        // Initialize a fresh game before each test
        game = new TicTacToe('X', 'O');
    }

    @Test
    void testTicChangeValidMove() {
        // returns true if the move is valid and space is empty
        boolean result = game.ticChange('X', "1", "1");
        assertTrue(result, "A valid move to an empty space should return true.");
        assertEquals('X', game.matrix[0][0], "The matrix should be updated with the correct character.");
    }

    @Test
    void testTicChangeInvalidOutOfBounds() {
        // returns false if coordinates are outside 1-3
        boolean result = game.ticChange('X', "4", "1");
        assertFalse(result, "A row of 4 should be out of bounds and return false.");
    }

    @Test
    void testTicChangeAlreadyTaken() {
        // returns false if the space is already occupied
        game.ticChange('X', "1", "1");
        boolean result = game.ticChange('O', "1", "1");
        assertFalse(result, "Placing a piece on an occupied spot should return false.");
    }

    @Test
    void testCheckHorWin() {
        // setting a horizontal win for X in row 1
        game.matrix[0][0] = 'X';
        game.matrix[0][1] = 'X';
        game.matrix[0][2] = 'X';

        assertTrue(game.checkHor(), "checkHor should return true for three in a row horizontally.");
        assertTrue(game.xWon, "xWon flag should be set to true.");
    }

    @Test
    void testCheckVerWin() {
        // setting a vertical win for O in column 2
        game.matrix[0][1] = 'O';
        game.matrix[1][1] = 'O';
        game.matrix[2][1] = 'O';

        assertTrue(game.checkVer(), "checkVer should return true for three in a row vertically.");
        assertTrue(game.oWon, "oWon flag should be set to true.");
    }

    @Test
    void testCheckDiagLeftWin() {
        // diagonal from top-left (0,0) to bottom-right (2,2)
        game.matrix[0][0] = 'X';
        game.matrix[1][1] = 'X';
        game.matrix[2][2] = 'X';

        assertTrue(game.checkDiagLeft(), "checkDiagLeft should detect a top-left to bottom-right win.");
    }

    @Test
    void testFullBoard() {
        // fill the board completely
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.matrix[i][j] = 'Z';
            }
        }
        assertTrue(game.full(), "full() should return true when no spaces are ' '.");
    }


}
