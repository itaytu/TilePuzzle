package server.game_logic;

import server.modules.GameBoard;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class PreGameAlgorithms {

    //Random initialization using Fisher–Yates algorithm modified for a 2d array
    protected static GameBoard fisherYatesShuffle(GameBoard gameBoard){
        int length = gameBoard.getBoard().length;
        GameBoard resultGameBoard = new GameBoard(gameBoard);
        int[][] board = resultGameBoard.getBoard();

        int i = length * length - 1;
        while (i > 0) {
            int j = (int) (Math.random() * i);
            int xi = i % length;
            int yi = i / length;
            int xj = j % length;
            int yj = j / length;

            int tmp = board[xi][yi];
            board[xi][yi] = board[xj][yj];
            board[xj][yj] = tmp;
            --i;
        }

        resultGameBoard.setBlankPosition(findBlankPosition(board));
        return resultGameBoard;
    }

    //Common Algorithm to check if tile puzzle is solvable
    protected static boolean checkIfSolvable(GameBoard gameBoard) {
        int blankIterations = blankPositionFromGoal(gameBoard);
        int[] flatBoard = Stream.of(gameBoard.getBoard())
                .flatMapToInt(IntStream::of)
                .toArray();
        int invCount = InversionCountMethod.mergeSortAndCount(flatBoard, 0, flatBoard.length - 1);

        // If number of steps from current blank position to goal position are odd, return true if inversion count is odd.
        if (blankIterations % 2 == 1)
            return (invCount % 2 == 1);
        // If number of steps from current blank position to goal position are even, return true if inversion count is even.
        else
           return (invCount % 2 == 0);
    }

    // find Position of blank from bottom
    private static int[] findBlankPosition(int[][] board) {
        int size = board.length;
        int blankValue = size * size;
        int[] blankPos = {size-1, size-1};
        // start from bottom-right corner of matrix
        for (int i = size - 1; i >= 0; i--)
            for (int j = size - 1; j >= 0; j--)
                if (board[i][j] == blankValue)
                    return new int[]{i, j};
        return blankPos;
    }

    // count number of steps required from current blank position to goal blank position
    private static int blankPositionFromGoal(GameBoard gameBoard) {
        int[] blankCurrentPosition = gameBoard.getBlankPosition();
        int[] blankGoalPosition = {gameBoard.getBoard().length - 1, gameBoard.getBoard().length - 1};
        return (blankGoalPosition[0] - blankCurrentPosition[0]) + (blankGoalPosition[1] - blankCurrentPosition[1]);
    }
}
