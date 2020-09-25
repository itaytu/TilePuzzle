package server.game_logic;

import server.modules.GameBoard;

public class GameBoardActions {

    public GameBoard generateGameBoard(GameBoard gameBoard){
        do {
            gameBoard = randomGameBoardInitialization(gameBoard);
        }while (!isGameBoardSolvable(gameBoard));

        return gameBoard;
    }

    public GameBoard randomGameBoardInitialization(GameBoard gameBoard) {
       return Algorithms.fisherYatesAlgorithm(gameBoard);
    }

    public boolean isGameBoardSolvable(GameBoard gameBoard) {
        return Algorithms.checkIfSolvable(gameBoard);
    }
}
