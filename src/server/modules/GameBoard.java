package server.modules;

import java.util.Arrays;

public class GameBoard {
    private int[][] board;
    private int[] blankPosition = new int[2];

    public GameBoard(){}

    public GameBoard(int size) {
        this(size, size);
    }

    public GameBoard(int rows, int cols) {
        this.board = new int[rows][cols];
        initBoard();
    }

   public GameBoard(GameBoard gameBoard){
        this.board = new int[gameBoard.board.length][gameBoard.board.length];
        for(int i = 0; i < gameBoard.board.length; i++)
        {
            int[] aMatrix = gameBoard.board[i];
            int   aLength = aMatrix.length;
            this.board[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, this.board[i], 0, aLength);
        }
        this.blankPosition = new int[gameBoard.blankPosition.length];
        System.arraycopy(gameBoard.blankPosition, 0, this.blankPosition, 0, gameBoard.blankPosition.length);
    }


    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getBlankPosition() {
        return blankPosition;
    }

    public void setBlankPosition(int[] blankPosition) {
        this.blankPosition = blankPosition;
    }


    private void initBoard() {
        int length = this.board.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = i * length + j + 1;
            }
        }
        blankPosition[0] = length - 1;
        blankPosition[1] = length - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoard gameBoard = (GameBoard) o;
        return Arrays.deepEquals(board, gameBoard.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        StringBuilder result= new StringBuilder();
        if (this.board == null || this.board.length == 0)
            return "Empty GameBoard";
        for (int[] row: this.board)
            result.append(Arrays.toString(row)).append("\n");
        return result.toString();
    }
}
