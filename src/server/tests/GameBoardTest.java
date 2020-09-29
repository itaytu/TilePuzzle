package server.tests;

import org.junit.jupiter.api.Test;
import server.modules.GameBoard;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void oneArgumentBoardInitialization(){
        GameBoard gameBoard = new GameBoard(5);
        int[] expected_size = {5, 5};
        int[] actual_size = {gameBoard.getBoard().length, gameBoard.getBoard()[0].length};
        assertArrayEquals(expected_size, actual_size);
    }

    @Test
    void twoArgumentsBoardInitialization(){
        GameBoard gameBoard = new GameBoard(5, 4);
        int[] expected_size = {5, 4};
        int[] actual_size = {gameBoard.getBoard().length ,gameBoard.getBoard()[0].length};
        assertArrayEquals(expected_size, actual_size);
    }

    @Test
    void equalityOfTwoBoardsSameSizeAndValues() {
        GameBoard gameBoardOne = new GameBoard(4);
        GameBoard gameBoardTwo = new GameBoard(4);

        assertEquals(gameBoardOne, gameBoardTwo);
    }

    @Test
    void equalityOfTwoBoardsSameSizeDifferentValues(){
        GameBoard gameBoardOne = new GameBoard(3);
        GameBoard gameBoardTwo = new GameBoard(3);
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 9, 8}};
        gameBoardTwo.setBoard(board);

        assertNotEquals(gameBoardOne, gameBoardTwo);
    }

    @Test
    void equalityOfTwoBoardsDifferentSize(){
        GameBoard gameBoardOne = new GameBoard(3);
        GameBoard gameBoardTwo = new GameBoard(4);

        assertNotEquals(gameBoardOne, gameBoardTwo);
    }

    @Test
    void equalityOfCopiedBoard() {
        GameBoard gameBoard = new GameBoard(3);
        GameBoard copiedGameBoard = new GameBoard(gameBoard);

        assertNotSame(gameBoard.getBoard(), copiedGameBoard.getBoard());
    }
}