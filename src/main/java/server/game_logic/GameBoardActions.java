package server.game_logic;

import server.models.GameBoard;
import server.models.Movement;

import java.util.ArrayList;

public class GameBoardActions {

    public static GameBoard generateGameBoard(int gameBoardSize){
        GameBoard gameBoard = new GameBoard(gameBoardSize);
        do {
            gameBoard = randomGameBoardInitialization(gameBoard);
        }while (!isGameBoardSolvable(gameBoard));

        return gameBoard;
    }

    public static GameBoard randomGameBoardInitialization(GameBoard gameBoard) {
       return PreGameAlgorithms.fisherYatesShuffle(gameBoard);
    }

    public static boolean isGameBoardSolvable(GameBoard gameBoard) {
        return PreGameAlgorithms.checkIfSolvable(gameBoard);
    }

    public static ArrayList<Movement> possibleMoves(GameBoard gameBoard) {
        return InGameAlgorithms.checkPossibleMoves(gameBoard);
    }

    public static GameBoard move(GameBoard gameBoard, Movement movement) {
        return InGameAlgorithms.makeMovement(gameBoard, movement);
    }

}
