package client;

import org.junit.jupiter.api.Test;
import server.controllers.GameController;
import server.game_logic.GameBoardActions;
import server.models.GameBoard;
import server.models.Movement;
import server.models.Response;
import server.views.PossibleResponses;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void fullCycleTest() {
        GameController gameController = new GameController();

        String invalidInputSize = "-1";
        gameController.initGame(invalidInputSize);

        String validInputSize = "3";
        gameController.initGame(validInputSize);
        GameBoard expectedGameBoard = gameController.getCurrentGameBoard();

        String invalidMove = "Z";
        gameController.playerMove(invalidMove);

        ArrayList<Movement> possibleMoves = GameBoardActions.possibleMoves(gameController.getCurrentGameBoard());
        String validMove = possibleMoves.get(0).getTypeOfMove();
        gameController.playerMove(validMove);
        GameBoardActions.move(expectedGameBoard, Movement.fromString(validMove));

        String quitMove = "q";
        Response actualQuitResponse = gameController.playerMove(quitMove);
        Response expectedQuitResponse = new Response(null, Response.Status.OK, PossibleResponses.getGameQuitMessage(), true);

        assertTrue((actualQuitResponse.equals(expectedQuitResponse))
                && (expectedGameBoard.equals(gameController.getCurrentGameBoard())));
    }
}