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

    private GameBoard gameBoard;
    private GameBoard allMovesGameBoard;

    @BeforeEach
    void init(){
        gameBoard = new GameBoard(4);
        int[][] allMovesBoard = {{1, 5, 4}, {3, 9, 8}, {7, 2, 6}};
        allMovesGameBoard = new GameBoard();
        allMovesGameBoard.setBoard(allMovesBoard);
        allMovesGameBoard.setBlankPosition(new int[]{1, 1});
    }


    @Test
    void randomBoardsInitializationFromSameBoard() {
        int iterations = 10;
        HashSet<GameBoard> gameBoards = new HashSet<>();
        gameBoards.add(gameBoard);
        for (int i = 0; i < iterations; i++) {
            gameBoards.add(GameBoardActions.randomGameBoardInitialization(gameBoard));
        }
        assertEquals(iterations + 1, gameBoards.size(), 1);
    }

    @Test
    void randomBoardsInitializationFromLastGeneratedBoard() {
        int iterations = 20;
        HashSet<GameBoard> gameBoards = new HashSet<>();
        gameBoards.add(gameBoard);
        GameBoard lastGeneratedBoard = new GameBoard(gameBoard);
        for (int i = 0; i < iterations; i++) {
            lastGeneratedBoard = GameBoardActions.randomGameBoardInitialization(lastGeneratedBoard);
            gameBoards.add(lastGeneratedBoard);
        }
        assertEquals(iterations + 1, gameBoards.size(), 1);
    }

    @Test
    void randomBoardSolvableRatio() {
        int iterations = 10000;
        int solvableCount = 0;
        double expectedRatio = 0.5;
        double delta = 0.05;
        for (int i = 0; i < iterations; i++) {
            GameBoard tmpGameBoard = GameBoardActions.randomGameBoardInitialization(gameBoard);
            solvableCount += (GameBoardActions.isGameBoardSolvable(tmpGameBoard)) ? 1 : 0;
        }

        double actualSolvableRatio = ((double) solvableCount)/iterations;
        assertTrue((actualSolvableRatio > expectedRatio - delta) && (actualSolvableRatio < expectedRatio + delta));
    }

    @Test
    void unsolvable16BoardEvenBlankStepsOddInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 15, 14, 16}};
        gameBoard.setBoard(boardMatrix);

        assertFalse(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvable16BoardZeroBlankStepsEvenInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {15, 13, 14, 16}};
        gameBoard.setBoard(boardMatrix);

        assertTrue(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void unsolvable16BoardOddBlankStepsEvenInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 16}, {12, 13, 14, 15}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{2, 3});

        assertFalse(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvable16BoardEvenBlankStepsEvenInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 16, 11}, {13, 12, 14, 15}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{2, 2});

        assertTrue(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvable16BoardOddBlankStepsOddInv() {
        int[][] boardMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 16, 10, 11}, {13, 12, 14, 15}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{2, 1});

        assertTrue(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void unsolvable9BoardEvenBlankStepsOddInv() {
        int[][] boardMatrix = {{1, 3, 2}, {4, 9, 5}, {6, 7, 8}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{1, 1});

        assertFalse(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvable9BoardZeroBlankStepsEvenInv() {
        int[][] boardMatrix = {{3, 1, 2}, {4, 5, 8}, {6, 7, 9}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{2, 2});

        assertTrue(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void unsolvable9BoardOddBlankStepsEvenInv() {
        int[][] boardMatrix = {{1, 3, 2}, {4, 5, 9}, {6, 7, 8}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{1, 2});

        assertFalse(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvable9BoardEvenBlankStepsEvenInv() {
        int[][] boardMatrix = {{3, 1, 2}, {4, 9, 5}, {6, 7, 8}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{1, 1});

        assertTrue(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void solvable9BoardOddBlankStepsOddInv() {
        int[][] boardMatrix = {{3, 1, 2}, {9, 4, 5}, {6, 7, 8}};
        gameBoard.setBoard(boardMatrix);
        gameBoard.setBlankPosition(new int[]{1, 0});

        assertTrue(GameBoardActions.isGameBoardSolvable(gameBoard));
    }

    @Test
    void checkPossibleMovesLeftBorderTopBorder(){
        int[][] boardMatrix = {{9, 2, 3}, {5, 1, 6}, {7, 8, 4}};
        gameBoard.setBlankPosition(new int[]{0, 0});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.LEFT);

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesLeftBorderBottomBorder(){
        int[][] boardMatrix = {{1, 2, 3}, {7, 5, 6}, {9, 8, 4}};
        gameBoard.setBlankPosition(new int[]{2, 0});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.LEFT);

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
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

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesRightBorderTopBorder(){
        int[][] boardMatrix = {{1, 2, 9}, {3, 5, 6}, {7, 8, 4}};
        gameBoard.setBlankPosition(new int[]{0, 2});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesRightBorderBottomBorder(){
        int[][] boardMatrix = {{1, 2, 4}, {3, 5, 6}, {7, 8, 9}};
        gameBoard.setBlankPosition(new int[]{2, 2});
        gameBoard.setBoard(boardMatrix);

        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
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

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
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

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
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

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(gameBoard));
    }

    @Test
    void checkPossibleMovesNoBorders(){
        ArrayList<Movement> expectedMoves = new ArrayList<>();
        expectedMoves.add(Movement.UP);
        expectedMoves.add(Movement.DOWN);
        expectedMoves.add(Movement.LEFT);
        expectedMoves.add(Movement.RIGHT);

        assertEquals(expectedMoves, GameBoardActions.possibleMoves(allMovesGameBoard));
    }

    @Test
    void checkMoveLeft() {
        int[][] expectedBoard = {{1, 5, 4}, {9, 3, 8}, {7, 2, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        GameBoardActions.move(allMovesGameBoard, Movement.RIGHT);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

    @Test
    void checkMoveRight() {
        int[][] expectedBoard = {{1, 5, 4}, {3, 8, 9}, {7, 2, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        GameBoardActions.move(allMovesGameBoard, Movement.LEFT);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

    @Test
    void checkMoveUp() {
        int[][] expectedBoard = {{1, 9, 4}, {3, 5, 8}, {7, 2, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        GameBoardActions.move(allMovesGameBoard, Movement.DOWN);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

    @Test
    void checkMoveDown() {
        int[][] expectedBoard = {{1, 5, 4}, {3, 2, 8}, {7, 9, 6}};
        GameBoard expectedGameBoard = new GameBoard();
        expectedGameBoard.setBoard(expectedBoard);
        GameBoardActions.move(allMovesGameBoard, Movement.UP);

        assertEquals(expectedGameBoard, allMovesGameBoard);
    }

}