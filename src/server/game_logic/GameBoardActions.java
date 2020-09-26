package server.game_logic;

import server.modules.GameBoard;
import server.modules.Movement;

import java.util.ArrayList;

public class GameBoardActions {

    public GameBoard generateGameBoard(int gameBoardSize){
        GameBoard gameBoard = new GameBoard(gameBoardSize);
        do {
            gameBoard = randomGameBoardInitialization(gameBoard);
        }while (!isGameBoardSolvable(gameBoard));

        return gameBoard;
    }

    public GameBoard randomGameBoardInitialization(GameBoard gameBoard) {
       return PreGameAlgorithms.fisherYatesShuffle(gameBoard);
    }

    public boolean isGameBoardSolvable(GameBoard gameBoard) {
        return PreGameAlgorithms.checkIfSolvable(gameBoard);
    }

    public ArrayList<Movement> possibleMoves(GameBoard gameBoard) {
        return InGameAlgorithms.checkPossibleMoves(gameBoard);
    }

    public GameBoard move(GameBoard gameBoard, Movement movement) {
        return InGameAlgorithms.makeMovement(gameBoard, movement);
    }

    public boolean isGameOver(GameBoard currentGameBoard, GameBoard solutionGameBoard) {
        return currentGameBoard.equals(solutionGameBoard);
    }

}
