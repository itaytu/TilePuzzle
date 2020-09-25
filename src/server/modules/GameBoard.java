package server.modules;

import java.util.Arrays;

public class GameBoard {
    private int[][] board;
    private int[] blankPosition = new int[2];

    public GameBoard(int size) {
        this(size, size);
    }

    public GameBoard(int rows, int cols) {
        this.board = new int[rows][cols];
        initBoard();
    }

/*    public GameBoard(int[][] board){
        this.board = board;
    }*/


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


    private void initBoard(){
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
        return Arrays.hashCode(board);
    }
}
