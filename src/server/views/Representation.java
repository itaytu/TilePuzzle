package server.views;

import server.modules.GameBoard;
import server.modules.Movement;

import java.util.ArrayList;

public class Representation {

    public static StringBuilder boardWithAvailableMoves(GameBoard gameBoard, ArrayList<Movement> possibleMoves) {
        StringBuilder result = board(gameBoard);
        result.append(availableMoves(possibleMoves));
        return result;
    }

    public static StringBuilder board(GameBoard gameBoard){
        String blankValue = String.valueOf(gameBoard.getBoard().length * gameBoard.getBoard().length);
        StringBuilder result = new StringBuilder(gameBoard.toString());
        int index = result.indexOf(blankValue);
        result.replace(index, index + blankValue.length(), "_");
        return result;
    }

    public static StringBuilder availableMoves(ArrayList<Movement> possibleMoves) {
        StringBuilder result = new StringBuilder("Available moves: ");
        int i = 1;
        for (Movement move: possibleMoves) {
            switch (move){
                case UP:
                    result.append(i).append(". UP - 'U'\t"); i++;
                    break;
                case DOWN:
                    result.append(i).append(". DOWN - 'D'\t"); i++;
                    break;
                case LEFT:
                    result.append(i).append(". LEFT - 'L'\t"); i++;
                    break;
                case RIGHT:
                    result.append(i).append(". RIGHT - 'R'\t"); i++;
                    break;
            }
        }
        return result;
    }

}
