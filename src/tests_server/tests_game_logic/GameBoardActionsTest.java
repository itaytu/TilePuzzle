package tests_server.tests_game_logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.game_logic.GameBoardActions;
import server.modules.GameBoard;
import server.modules.Movement;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardActionsTest {

    private static GameBoardActions gameBoardActions;
    private GameBoard gameBoard;
    private GameBoard allMovesGameBoard;

    @BeforeAll
    static void setUp(){
        gameBoardActions = new GameBoardActions();
    }

    @BeforeEach
    void init(){
        gameBoard = new GameBoard(4);
        int[][] allMovesBoard = {{1, 5, 4}, {3, 9, 8}, {7, 2, 6}};
        allMovesGameBoard = new GameBoard();
        allMovesGameBoard.setBoard(allMovesBoard);
        allMovesGameBoard.setBlankPosition(new int[]{1, 1});
    }


    @Test
    void randomBoardsInitialization(){
        int numOfRounds = 10;
        HashSet<GameBoard> gameBoards = new HashSet<>();
        gameBoards.add(gameBoard);
        for (int i = 0; i < numOfRounds; i++) {
            gameBoards.add(gameBoardActions.randomGameBoardInitialization(gameBoard));
        }
        assertEquals(numOfRounds + 1, gameBoards.size(), 1);
    }

    @Test
    void unsolvableBoardEvenSizeOddBlankOddInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 15, 14, 16}};
        gameBoard.setBoard(boardMatrix);

        assertFalse(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvableBoardEvenSizeOddBlankEvenInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {15, 13, 14, 16}};
        gameBoard.setBoard(boardMatrix);

        assertTrue(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void unsolvableBoardEvenSizeEvenBlankEvenInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 16}, {12, 13, 14, 15}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{2, 3});

        assertFalse(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvableBoardEvenSizeEvenBlankOddInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 16, 11}, {12, 13, 14, 15}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{2, 2});

        assertTrue(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void unsolvableBoardOddSizeOddInv() {
        int[][] boardMatrix = {{3, 1, 2}, {4, 5, 9}, {6, 7, 8}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{1, 2});

        assertFalse(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvableBoardOddSizeEvenInv() {
        int[][] boardMatrix = {{1, 2, 3}, {4, 9, 5}, {6, 7, 8}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{1, 1});

        assertTrue(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvableBoardCheck() {
        assertTrue(gameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void checkPossibleMovesLeftBorderTopBorder(){
        int[][] boardMatrix = {{9, 2, 3}, {5, 1, 6}, {7, 8, 4}};
        gameBoard.setBlankPosition(new int[]{0, 0});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.LEFT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesLeftBorderBottomBorder(){
        int[][] boardMatrix = {{1, 2, 3}, {7, 5, 6}, {9, 8, 4}};
        gameBoard.setBlankPosition(new int[]{2, 0});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.LEFT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesLeftBorderOnly(){
        int[][] boardMatrix = {{1, 2, 3}, {9, 5, 6}, {7, 8, 4}};
        gameBoard.setBlankPosition(new int[]{1, 0});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.LEFT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesRightBorderTopBorder(){
        int[][] boardMatrix = {{1, 2, 9}, {3, 5, 6}, {7, 8, 4}};
        gameBoard.setBlankPosition(new int[]{0, 2});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesRightBorderBottomBorder(){
        int[][] boardMatrix = {{1, 2, 4}, {3, 5, 6}, {7, 8, 9}};
        gameBoard.setBlankPosition(new int[]{2, 2});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesRightBorderOnly(){
        int[][] boardMatrix = {{1, 2, 4}, {3, 5, 9}, {7, 8, 6}};
        gameBoard.setBlankPosition(new int[]{1, 2});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesBottomBorderOnly(){
        int[][] boardMatrix = {{1, 2, 4}, {3, 5, 8}, {7, 9, 6}};
        gameBoard.setBlankPosition(new int[]{2, 1});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.LEFT);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesTopBorderOnly(){
        int[][] boardMatrix = {{1, 9, 4}, {3, 5, 8}, {7, 2, 6}};
        gameBoard.setBlankPosition(new int[]{0, 1});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.LEFT);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesNoBorders(){
        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.LEFT);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, gameBoardActions.possibleMoves(allMovesGameBoard));
    }

    @Test
    void checkMoveLeft() {
        int[][] expectedBoard = {{1, 5, 4}, {9, 3, 8}, {7, 2, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        gameBoardActions.move(allMovesGameBoard, Movement.RIGHT);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

    @Test
    void checkMoveRight() {
        int[][] expectedBoard = {{1, 5, 4}, {3, 8, 9}, {7, 2, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        gameBoardActions.move(allMovesGameBoard, Movement.LEFT);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

    @Test
    void checkMoveUp() {
        int[][] expectedBoard = {{1, 9, 4}, {3, 5, 8}, {7, 2, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        gameBoardActions.move(allMovesGameBoard, Movement.DOWN);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

    @Test
    void checkMoveDown() {
        int[][] expectedBoard = {{1, 5, 4}, {3, 2, 8}, {7, 9, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        gameBoardActions.move(allMovesGameBoard, Movement.UP);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

}