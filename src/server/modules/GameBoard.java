package server.modules;

public class GameBoard {
    private int[][] board;

    public GameBoard(int size) {
        this(size, size);
    }

    public GameBoard(int rows, int cols) {
        this.board = new int[rows][cols];
    }


    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
