package server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.game_logic.GameBoardActions;
import server.modules.GameBoard;
import server.modules.Movement;
import server.views.PossibleResponses;
import server.modules.Response;
import server.views.Representation;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameControllerTest {

    private static GameController gameController;
    private static GameBoard goalGameBoard;
    private ArrayList<Movement> possibleMoves;

    private GameBoard initialGameBoard;

    private static final String[] statuses = {"Success", "Failed"};

    @BeforeAll
    static void setGameController(){
        gameController = new GameController();
        goalGameBoard = new GameBoard(3);
    }

    @BeforeEach
    void setGameBoards(){
        gameController.initGame("3");
        initialGameBoard = gameController.getCurrentGameBoard();
        possibleMoves = GameBoardActions.possibleMoves(initialGameBoard);
    }

    @Test
    void initGameInvalidSizeNegativeNumber() {
        Response expected = new Response(null, statuses[1], PossibleResponses.getInvalidSizeMessage());
        Response actual = gameController.initGame("-3");

        assertTrue((expected.getGameBoard() == null) &&
                (expected.getStatus().equals(actual.getStatus())) &&
                (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }

    @Test
    void initGameInvalidSizeFloatingNumber() {
        Response expected = new Response(null, statuses[1], PossibleResponses.getInvalidSizeMessage());
        Response actual = gameController.initGame("3.2");

        assertTrue((expected.getGameBoard() == null) &&
                (expected.getStatus().equals(actual.getStatus())) &&
                (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }

    @Test
    void initGameInvalidSizeNotANumber() {
        Response expected = new Response(null, statuses[1], PossibleResponses.getInvalidSizeMessage());
        Response actual = gameController.initGame("3a");

        assertTrue((actual.getGameBoard() == null)
                && (expected.getStatus().equals(actual.getStatus()))
                && (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }

    @Test
    void initGameValidSize() {
        String statusExpected = statuses[0];
        String messageExpected = PossibleResponses.getGameCreatedMessage();
        Response actual = gameController.initGame("3");

        assertTrue((actual.getStatus().equals(statusExpected))
                && (actual.getResponseMessage().equals(messageExpected)));
    }

    @Test
    void playerMoveInvalidMove() {
        StringBuilder possibleMovesRepresentation = Representation.availableMoves(possibleMoves);
        Response expected = new Response(null, statuses[1], PossibleResponses.getInvalidMoveMessage(possibleMovesRepresentation));
        Response actual = gameController.playerMove("K");

        assertTrue((actual.getGameBoard() == null)
                && (expected.getStatus().equals(actual.getStatus()))
                && (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }

    @Test
    void playerMoveQuitGame() {
        Response expected = new Response(null, statuses[0], PossibleResponses.getGameQuitMessage());
        Response actual = gameController.playerMove("Q");

        assertTrue((actual.getGameBoard() == null)
                && (expected.getStatus().equals(actual.getStatus()))
                && (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }

    @Test
    void playerMoveFinishGame() {
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 9, 8}};
        initialGameBoard.setBoard(board);
        initialGameBoard.setBlankPosition(new int[]{2, 1});

        Response expected = new Response(Representation.board(goalGameBoard), statuses[0], PossibleResponses.getGameFinishedMessage());
        Response actual = gameController.playerMove("L");

        assertTrue((expected.getGameBoard().toString().equals(actual.getGameBoard().toString()))
                && (expected.getStatus().equals(actual.getStatus()))
                && (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }

    @Test
    void playerMoveValid() {
        int[][] initialBoard = {{1, 2, 3}, {4, 5, 6}, {9, 8, 7}};
        int[][] afterMoveBoard = {{1, 2, 3}, {4, 5, 6}, {8, 9, 7}};
        initialGameBoard.setBoard(initialBoard);
        initialGameBoard.setBlankPosition(new int[]{2, 0});

        GameBoard afterMoveGameBoard = new GameBoard();
        afterMoveGameBoard.setBoard(afterMoveBoard);
        afterMoveGameBoard.setBlankPosition(new int[]{2, 1});

        StringBuilder boardRepresentation = Representation.boardWithAvailableMoves(afterMoveGameBoard, GameBoardActions.possibleMoves(afterMoveGameBoard));
        Response expected = new Response(boardRepresentation, statuses[0], PossibleResponses.getMoveMadeMessage());
        Response actual = gameController.playerMove("L");

        assertTrue((expected.getGameBoard().toString().equals(actual.getGameBoard().toString()))
                && (expected.getStatus().equals(actual.getStatus()))
                && (expected.getResponseMessage().equals(actual.getResponseMessage())));
    }
}