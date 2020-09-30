package server.models;

import java.util.Objects;

public class Response {
    private String status;
    private String responseMessage;
    private StringBuilder gameBoardRepresentation;

    private boolean isGameOver;

    public Response(StringBuilder gameBoard, String status, String responseMessage, boolean isGameOver){
        if (gameBoard == null)
            this.gameBoardRepresentation = new StringBuilder();
        else
            this.gameBoardRepresentation = gameBoard;
        this.status = status;
        this.responseMessage = responseMessage;
        this.isGameOver = isGameOver;
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
        return new StringBuilder(gameBoardRepresentation);
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        if (response.gameBoardRepresentation == null && this.gameBoardRepresentation != null) return false;
        if (response.gameBoardRepresentation != null && this.gameBoardRepresentation == null) return false;
        if (response.gameBoardRepresentation == null)
            return isGameOver == response.isGameOver &&
                    Objects.equals(status, response.status) &&
                    Objects.equals(responseMessage, response.responseMessage);
        return isGameOver == response.isGameOver &&
                Objects.equals(status, response.status) &&
                Objects.equals(responseMessage, response.responseMessage) &&
                Objects.equals(gameBoardRepresentation.toString(), response.gameBoardRepresentation.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, responseMessage, gameBoardRepresentation, isGameOver);
    }
}
