package tests_server.tests_modules;

import org.junit.jupiter.api.Test;
import server.modules.GameBoard;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void oneArgumentBoardInitialization(){
        GameBoard gameBoard = new GameBoard(5);
        int[] expected_size = {5, 5};
        int[] actual_size = {gameBoard.getBoard().length, gameBoard.getBoard()[0].length};
        assertEquals(expected_size, actual_size);
    }

    @Test
    void twoArgumentsBoardInitialization(){
        GameBoard gameBoard = new GameBoard(5, 4);
        int[] expected_size = {5, 4};
        int[] actual_size = {gameBoard.getBoard().length ,gameBoard.getBoard()[0].length};
        assertArrayEquals(expected_size, actual_size);
    }

    @Test
    void negativeArgumentBoardInitialization(){

    }

}