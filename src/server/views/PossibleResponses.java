package server.views;

public class PossibleResponses {

    public static String getInitiateGame() {
        return "Hi, Welcome to the tile puzzle game.\n" + enterSizeMessage();
    }
    public static String getInvalidMoveMessage(StringBuilder possibleMoves) {
        return "Invalid move, Please enter a valid move from:\n" + possibleMoves;
    }

    public static String getInvalidSizeMessage() {
        return "Invalid board size\n" + enterSizeMessage();
    }

    public static String getGameQuitMessage() {
        return "Game was quited, goodbye!";
    }

    public static String getGameCreatedMessage() {
        return "Game board created";
    }

    public static String getMoveMadeMessage() {
        return "Movement was made";
    }

    public static String getGameFinishedMessage() {
        return "Game won! congratulations!";
    }

    private static String enterSizeMessage() {
        return "Please enter a positive integer size for the board";
    }
}
