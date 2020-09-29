package server.modules;

public class Response {
    private String status;
    private String responseMessage;
    private StringBuilder gameBoardRepresentation;

    private boolean isGameOver;

    public Response(StringBuilder gameBoard, String status, String responseMessage){
        this.gameBoardRepresentation = gameBoard;
        this.status = status;
        this.responseMessage = responseMessage;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public StringBuilder getGameBoard() {
        return gameBoardRepresentation;
    }

    public String getStatus() {
        return status;
    }
}
