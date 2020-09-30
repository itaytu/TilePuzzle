package server.game_logic;

import server.models.GameBoard;
import server.models.Movement;

import java.util.ArrayList;

public class InGameAlgorithms {

    protected static ArrayList<Movement> checkPossibleMoves(GameBoard gameBoard){
        ArrayList<Movement> possibleMoves = new ArrayList<>();
        int[] blankPosition = gameBoard.getBlankPosition();
        final int dimension = gameBoard.getBoard().length - 1;
        if (blankPosition[0] == 0)
            possibleMoves.add(Movement.UP);
        else if (blankPosition[0] == dimension)
            possibleMoves.add(Movement.DOWN);
        else {
            possibleMoves.add(Movement.UP);
            possibleMoves.add(Movement.DOWN);
        }
        if (blankPosition[1] == 0)
            possibleMoves.add(Movement.LEFT);
        else if (blankPosition[1] == dimension)
            possibleMoves.add(Movement.RIGHT);
        else {
            possibleMoves.add(Movement.LEFT);
            possibleMoves.add(Movement.RIGHT);
        }

        return possibleMoves;
    }


    protected static GameBoard makeMovement(GameBoard gameBoard, Movement movement) {
            int[] initialPosition = gameBoard.getBlankPosition();
            int rowMovement = movement.getMovement()[0];
            int colMovement = movement.getMovement()[1];
            int tileToSwap = gameBoard.getBoard()[initialPosition[0] + rowMovement][initialPosition[1] + colMovement];
            swapTiles(gameBoard, initialPosition, rowMovement, colMovement, tileToSwap);
            return gameBoard;
    }


    private static void swapTiles(GameBoard gameBoard, int[] initialPosition, int rowMovement, int colMovement, int tileToSwap) {
        int length = gameBoard.getBoard().length;
        int blankValue = length * length;
        int[][] board = gameBoard.getBoard();
        board[initialPosition[0] + rowMovement][initialPosition[1] + colMovement] = blankValue;
        board[initialPosition[0]][initialPosition[1]] = tileToSwap;
        gameBoard.setBoard(board);
        gameBoard.setBlankPosition(new int[]{initialPosition[0] + rowMovement, initialPosition[1] + colMovement});
    }
}
