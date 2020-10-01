package server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controllers.GameController;
import server.game_logic.GameBoardActions;
import server.models.GameBoard;
import server.models.Movement;
import server.models.Response;
import server.views.PossibleResponses;
import server.views.Representation;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {

    private static GameController gameController;
    private ArrayList<Movement> possibleMoves;

    @BeforeAll
    static void setGameController(){
        gameController = new GameController();
    }

    @BeforeEach
    void setGameBoards(){
        gameController.initGame("3");
        possibleMoves = gameController.getPossibleMoves();
    }

    @Test
    void initGameInvalidSizeNegativeNumber() {
        Response expected = new Response(null, Response.Status.ERROR, PossibleResponses.getInvalidSizeMessage(), false);
        Response actual = gameController.initGame("-3");

        assertEquals(actual, expected);
    }

    @Test
    void initGameInvalidSizeFloatingNumber() {
        Response expected = new Response(null, Response.Status.ERROR, PossibleResponses.getInvalidSizeMessage(), false);
        Response actual = gameController.initGame("3.2");

        assertEquals(actual, expected);
    }

    @Test
    void initGameInvalidSizeNotANumber() {
        Response expected = new Response(null, Response.Status.ERROR, PossibleResponses.getInvalidSizeMessage(), false);
        Response actual = gameController.initGame("3a");

        assertEquals(actual, expected);
    }

    @Test
    void initGameValidSize() {
        Response actual = gameController.initGame("3");
        GameBoard current = gameController.getCurrentGameBoard();
        StringBuilder boardRepresentation = Representation.boardWithAvailableMoves(current, GameBoardActions.possibleMoves(current));
        Response expected  = new Response(boardRepresentation, Response.Status.OK, PossibleResponses.getGameCreatedMessage(), false);

        assertEquals(actual, expected);
    }

    @Test
    void playerMoveInvalidMove() {
        StringBuilder possibleMovesRepresentation = Representation.availableMoves(possibleMoves);
        Response expected = new Response(null, Response.Status.ERROR, PossibleResponses.getInvalidMoveMessage(possibleMovesRepresentation), false);
        Response actual = gameController.playerMove("K");

        assertEquals(actual, expected);
    }

    @Test
    void playerMoveQuitGame() {
        Response expected = new Response(null, Response.Status.OK, PossibleResponses.getGameQuitMessage(), true);
        expected.setGameOver(true);
        Response actual = gameController.playerMove("Q");

        assertEquals(actual, expected);
    }

    @Test
    void playerMoveFinishGame() {
        int[][] initialBoard = {{1, 2, 3}, {4, 5, 6}, {7, 9, 8}};

        GameBoard currentGameBoard = new GameBoard();
        GameBoard gameBoardSolution = new GameBoard(3);

        currentGameBoard.setBoard(initialBoard);
        currentGameBoard.setBlankPosition(new int[]{2, 1});

        currentGameBoard = GameBoardActions.move(currentGameBoard, Movement.fromString("L"));
        possibleMoves = GameBoardActions.possibleMoves(currentGameBoard);

        Response actual;
        if(currentGameBoard.equals(gameBoardSolution))
            actual = new Response(Representation.board(currentGameBoard), Response.Status.OK, PossibleResponses.getGameFinishedMessage(), true);
        else {
            StringBuilder gameBoardRepresentation = Representation.boardWithAvailableMoves(currentGameBoard, possibleMoves);
            actual = new Response(gameBoardRepresentation, Response.Status.OK, PossibleResponses.getMoveMadeMessage(), false);

        }

        StringBuilder boardRepresentation = Representation.board(gameBoardSolution);
        Response expected = new Response(boardRepresentation, Response.Status.OK, PossibleResponses.getGameFinishedMessage(), true);

        assertEquals(actual, expected);
    }

    @Test
    void playerMoveValid() {
        int[][] initialBoard = {{1, 2, 3}, {4, 5, 6}, {9, 8, 7}};
        int[][] afterMoveBoard = {{1, 2, 3}, {4, 5, 6}, {8, 9, 7}};
        GameBoard currentGameBoard = new GameBoard();
        GameBoard expectedGameBoard = new GameBoard();
        GameBoard gameBoardSolution = new GameBoard(3);
        currentGameBoard.setBoard(initialBoard);
        currentGameBoard.setBlankPosition(new int[]{2, 0});
        expectedGameBoard.setBoard(afterMoveBoard);
        expectedGameBoard.setBlankPosition(new int[]{2, 1});

        currentGameBoard = GameBoardActions.move(currentGameBoard, Movement.fromString("L"));
        possibleMoves = GameBoardActions.possibleMoves(currentGameBoard);

        Response actual;
        if(currentGameBoard.equals(gameBoardSolution))
            actual = new Response(Representation.board(currentGameBoard), Response.Status.OK, PossibleResponses.getGameFinishedMessage(),true);
        else {
            StringBuilder gameBoardRepresentation = Representation.boardWithAvailableMoves(currentGameBoard, possibleMoves);
            actual = new Response(gameBoardRepresentation, Response.Status.OK, PossibleResponses.getMoveMadeMessage(), false);
        }

        StringBuilder boardRepresentation = Representation.boardWithAvailableMoves(expectedGameBoard, GameBoardActions.possibleMoves(expectedGameBoard));
        Response expected = new Response(boardRepresentation, Response.Status.OK, PossibleResponses.getMoveMadeMessage(), false);

        assertEquals(actual, expected);
    }
}