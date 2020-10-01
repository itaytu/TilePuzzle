package server.controllers;

import server.game_logic.GameBoardActions;
import server.models.GameBoard;
import server.models.Movement;
import server.models.Response;
import server.views.PossibleResponses;
import server.views.Representation;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GameController {

    private GameBoard gameBoardSolution;
    private GameBoard currentGameBoard;
    private ArrayList<Movement> possibleMoves;

    public Response initGame(String boardSize) {
        int boardSizeValidation = boardSizeValidation(boardSize);
        if (boardSizeValidation == -1)
            return buildResponse(null, Response.Status.ERROR, PossibleResponses.getInvalidSizeMessage(), false);

        gameBoardSolution = new GameBoard(boardSizeValidation);
        currentGameBoard = GameBoardActions.generateGameBoard(boardSizeValidation);
        possibleMoves = GameBoardActions.possibleMoves(currentGameBoard);

        StringBuilder gameBoardRepresentation = Representation.boardWithAvailableMoves(currentGameBoard, possibleMoves);
        return buildResponse(gameBoardRepresentation, Response.Status.OK, PossibleResponses.getGameCreatedMessage(), false);
    }

    public Response playerMove(String movement) {
        movement = movement.toUpperCase();
        // Check if player asked to quit
        if (isQuitGame(movement)){
            return buildResponse(null,  Response.Status.OK, PossibleResponses.getGameQuitMessage(), true);
        }
        // Check if move is possible
        else if (!isMovementValid(movement)){
            StringBuilder possibleMovesRepresentation = Representation.availableMoves(possibleMoves);
            return buildResponse(null, Response.Status.ERROR, PossibleResponses.getInvalidMoveMessage(possibleMovesRepresentation), false);
        }
        // Make move
        currentGameBoard = GameBoardActions.move(currentGameBoard, Movement.fromString(movement));
        possibleMoves = GameBoardActions.possibleMoves(currentGameBoard);

        // Check if after movement game is over
        if(GameBoardActions.isGameOver(currentGameBoard, gameBoardSolution)){
            return buildResponse(Representation.board(currentGameBoard), Response.Status.OK, PossibleResponses.getGameFinishedMessage(), true);
        }

        // Return the response for the movement
        StringBuilder gameBoardRepresentation = Representation.boardWithAvailableMoves(currentGameBoard, possibleMoves);
        return buildResponse(gameBoardRepresentation, Response.Status.OK, PossibleResponses.getMoveMadeMessage(), false);
    }

    private int boardSizeValidation(String boardSize) {
        String pattern = "^([1-9][\\d]*)$";
        Pattern r = Pattern.compile(pattern);
        if (!r.matcher(boardSize).find())
            return -1;
        else
            return Integer.parseInt(boardSize);
    }

    private boolean isMovementValid(String movement) {
        StringBuilder possibleMovesPattern = new StringBuilder();
        for (Movement possibleMove: possibleMoves) {
            possibleMovesPattern.append(possibleMove.getTypeOfMove());
        }
        String pattern = String.format("^([%s])$", possibleMovesPattern);
        Pattern r = Pattern.compile(pattern);
        return r.matcher(movement).find();
    }

    private boolean isQuitGame(String pressedKey) {
        String pattern = "^(Q)$";
        Pattern r = Pattern.compile(pattern);
        return r.matcher(pressedKey).find();
    }

    private Response buildResponse(StringBuilder gameBoard, Response.Status status, String responseMessage, boolean isGameOver) {
        return new Response(gameBoard, status, responseMessage, isGameOver);
    }

    public GameBoard getCurrentGameBoard() {
        return new GameBoard(currentGameBoard);
    }

    public ArrayList<Movement> getPossibleMoves() {
        return new ArrayList<>(possibleMoves);
    }
}
