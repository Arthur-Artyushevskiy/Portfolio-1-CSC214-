package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiniMaxTest {

    private MiniMax miniMax;

    @BeforeEach
    public void setUp() {
        miniMax = new MiniMax();
    }

    @Test
    public void testIsMoveLeft_EmptySpacesExist() {
        char[][] board = {
                {'O', 'X', 'O'},
                {'X', ' ', 'O'},
                {'X', 'O', 'X'}
        };

        assertEquals(true, miniMax.isMoveLeft(board));
    }

    @Test
    public void testIsMoveLeft_NoEmptySpaces() {
        char[][] board = {
                {'O', 'X', 'O'},
                {'X', 'O', 'O'},
                {'X', 'O', 'X'}
        };

        assertEquals(false, miniMax.isMoveLeft(board));
    }

    @Test
    public void testEvaluate_PlayerWinsHorizontal() {
        char[][] board = {
                {'O', 'O', 'O'},
                {' ', 'X', ' '},
                {' ', ' ', 'X'}
        };

        assertEquals(10, miniMax.evaluate(board));
    }

    @Test
    public void testEvaluate_OpponentWinsVertical() {
        char[][] board = {
                {'X', 'O', ' '},
                {'X', 'O', ' '},
                {'X', ' ', ' '}
        };

        assertEquals(-10, miniMax.evaluate(board));
    }

    @Test
    public void testEvaluate_DrawOrIncomplete() {
        char[][] board = {
                {'O', 'X', 'O'},
                {'X', 'O', 'X'},
                {'X', 'O', 'X'}
        };

        assertEquals(0, miniMax.evaluate(board));
    }

    @Test
    public void testFindBestMove_PlayerWins() {

        char[][] board = {
                {'O', 'O', ' '},
                {'X', 'X', ' '},
                {' ', ' ', ' '}
        };

        miniMax.findBestMove(board);


        assertEquals("1", miniMax.row);
        assertEquals("3", miniMax.col);
    }

    @Test
    public void testFindBestMove_PlayerBlocks() {

        char[][] board = {
                {'X', 'X', ' '},
                {'O', ' ', ' '},
                {' ', ' ', ' '}
        };

        miniMax.findBestMove(board);


        assertEquals("1", miniMax.row);
        assertEquals("3", miniMax.col);
    }

    @Test
    public void testMinimax_DepthPenalty() {

        char[][] board = {
                {'O', 'O', ' '},
                {'X', 'X', 'O'},
                {' ', ' ', ' '}
        };


        miniMax.findBestMove(board);

        assertEquals("1", miniMax.row);
        assertEquals("3", miniMax.col);
    }
}