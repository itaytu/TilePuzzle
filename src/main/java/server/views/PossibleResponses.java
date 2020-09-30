package server.views;

public class PossibleResponses {

    public static String getInvalidMoveMessage(StringBuilder possibleMoves) {
        return "Invalid move, Please enter a valid move from:\n" + possibleMoves;
    }

    public static String getInvalidSizeMessage() {
        return "Invalid board size.\n" + enterSizeMessage();
    }

    public static String getGameQuitMessage() {
        return "Game was quited, goodbye!";
    }

    public static String getGameCreatedMessage() {
        return "Game board created.\n" + getGameInstructions();
    }

    public static String getMoveMadeMessage() {
        return "Movement was made.";
    }

    public static String getGameFinishedMessage() {
        return "Game won! congratulations!";
    }

    private static String enterSizeMessage() {
        return "Please enter a positive integer for the board size.";
    }

    private static String getGameInstructions() {
        return "Instructions: Each round choose a possible move from the available moves for that round.\n" +
                "The general moves are: U - up, D - down, L - left, R - right\n" +
                "You can exit the game at any stage by pressing Q\n" +
                "The game is done either when quitting or by winning the game";
    }
}
