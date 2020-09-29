package server;

import server.game_logic.GameBoardActions;
import server.modules.GameBoard;
import server.modules.Movement;
import server.views.PossibleResponses;
import server.modules.Response;
import server.views.Representation;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GameController {
    static {
        System.out.println(PossibleResponses.getInitiateGame());
    }

    private GameBoard gameBoardSolution;
    private GameBoard currentGameBoard;
    private ArrayList<Movement> possibleMoves;

    public Response initGame(String boardSize) {
        int boardSizeValidation = boardSizeValidation(boardSize);
        if (boardSizeValidation == -1)
            return buildResponse(null, "Failed", PossibleResponses.getInvalidSizeMessage());

        gameBoardSolution = new GameBoard(boardSizeValidation);
        currentGameBoard = GameBoardActions.generateGameBoard(boardSizeValidation);
        possibleMoves = GameBoardActions.possibleMoves(currentGameBoard);

        StringBuilder gameBoardRepresentation = Representation.boardWithAvailableMoves(currentGameBoard, possibleMoves);
        return buildResponse(gameBoardRepresentation, "Success", PossibleResponses.getGameCreatedMessage());
    }

    public Response playerMove(String movement) {
        movement = movement.toUpperCase();
        // Check if player asked to quit
        if (isQuitGame(movement)){
            Response response = buildResponse(null,  "Success", PossibleResponses.getGameQuitMessage());
            response.setGameOver(true);
            return response;
        }
        // Check if move is possible
        else if (!isMovementValid(movement)){
            StringBuilder possibleMovesRepresentation = Representation.availableMoves(possibleMoves);
            return buildResponse(null, "Failed", PossibleResponses.getInvalidMoveMessage(possibleMovesRepresentation));
        }
        // Make move
        GameBoardActions.move(currentGameBoard, Movement.fromString(movement));
        possibleMoves = GameBoardActions.possibleMoves(currentGameBoard);

        // Check if after movement game is over
        if(isGameOver(currentGameBoard, gameBoardSolution)){
            Response response = buildResponse(Representation.board(currentGameBoard), "Success", PossibleResponses.getGameFinishedMessage());
            response.setGameOver(true);
            return response;
        }

        // Return the response for the movement
        StringBuilder gameBoardRepresentation = Representation.boardWithAvailableMoves(currentGameBoard, possibleMoves);
        return buildResponse(gameBoardRepresentation, "Success", PossibleResponses.getMoveMadeMessage());
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

    private boolean isGameOver(GameBoard currentGameBoard, GameBoard solutionGameBoard) {
        return currentGameBoard.equals(solutionGameBoard);
    }

    private Response buildResponse(StringBuilder gameBoard, String status, String responseMessage) {
        return new Response(gameBoard, status, responseMessage);
    }

    protected GameBoard getCurrentGameBoard() {
        return currentGameBoard;
    }
}
