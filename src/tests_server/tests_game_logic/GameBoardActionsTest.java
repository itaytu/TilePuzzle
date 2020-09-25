package tests_server.tests_game_logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.game_logic.GameBoardActions;
import server.modules.GameBoard;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardActionsTest {

    private static GameBoardActions gameBoardActions;
    private static GameBoard gameBoard;

    @BeforeAll
    static void setUp(){
        gameBoardActions = new GameBoardActions();
    }

    @BeforeEach
    void init(){
        gameBoard = new GameBoard(4);
    }


    @Test
    void randomBoardInitialization(){
        GameBoard randomGameBoardOne = gameBoardActions.randomGameBoardInitialization(gameBoard);

        assertNotEquals(gameBoard, randomGameBoardOne);
    }

    @Test
    void unsolvableBoardCheck() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 15, 14, 16}};
        gameBoard.setBoard(boardMatrix);

        assertFalse(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvableBoardCheck() {
        assertTrue(gameBoardActions.isGameBoardSolvable(gameBoard));
    }


}