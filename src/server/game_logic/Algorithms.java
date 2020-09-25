package server.game_logic;

import server.modules.GameBoard;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Algorithms {

    //Random initialization using Fisher–Yates algorithm modified for a 2d array
    protected static GameBoard fisherYatesAlgorithm(GameBoard gameBoard){
        int[][]  board = gameBoard.getBoard();

        int rowSize = board.length;
        int columnSize = board.length;

        int i = rowSize * columnSize - 1;
        while (i > 0) {
            int j = (int) (Math.random() * i);
            int xi = i % rowSize;
            int yi = i / rowSize;
            int xj = j % rowSize;
            int yj = j / rowSize;

            int tmp = board[xi][yi];
            board[xi][yi] = board[xj][yj];
            board[xj][yj] = tmp;
            --i;
        }

        gameBoard.setBoard(board);
        gameBoard.setBlankPosition(findBlankPosition(gameBoard.getBoard()));
        return gameBoard;
    }


    //Common Algorithm to check if tile puzzle is solvable
    protected static boolean checkIfSolvable(GameBoard gameBoard) {
        int[] flatBoard = Stream.of(gameBoard.getBoard())
                .flatMapToInt(IntStream::of)
                .toArray();
        int invCount = Sort.mergeSortAndCount(flatBoard, 0, flatBoard.length - 1);

        int length = gameBoard.getBoard().length;
        // If board is odd, return true if inversion count is even.
        if (length % 2 == 1)
            return (invCount % 2 == 0);

        else     // board is even
        {
            int blankRowParity = (length - 1) - gameBoard.getBlankPosition()[0] + 1;
            if (blankRowParity % 2 == 1)
                return (invCount % 2 == 0);
            else
                return (invCount % 2 == 1);
        }
    }



    //
    static class Sort {
        private static int mergeAndCount(int[] flatBoard, int l, int m, int r)
        {

            // Left subarray
            int[] left = Arrays.copyOfRange(flatBoard, l, m + 1);

            // Right subarray
            int[] right = Arrays.copyOfRange(flatBoard, m + 1, r + 1);

            int i = 0, j = 0, k = l, swaps = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j])
                    flatBoard[k++] = left[i++];
                else {
                    flatBoard[k++] = right[j++];
                    swaps += (m + 1) - (l + i);
                }
            }

            // Fill from the rest of the left subarray
            while (i < left.length)
                flatBoard[k++] = left[i++];

            // Fill from the rest of the right subarray
            while (j < right.length)
                flatBoard[k++] = right[j++];

            return swaps;
        }

        // Merge sort function
        private static int mergeSortAndCount(int[] arr, int l, int r)
        {
            // Keeps track of the inversion count at a
            // particular node of the recursion tree
            int count = 0;

            if (l < r) {
                int m = (l + r) / 2;

                // Total inversion count = left subarray count
                // + right subarray count + merge count

                // Left subarray count
                count += mergeSortAndCount(arr, l, m);

                // Right subarray count
                count += mergeSortAndCount(arr, m + 1, r);

                // Merge count
                count += mergeAndCount(arr, l, m, r);
            }

            return count;
        }
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
}
