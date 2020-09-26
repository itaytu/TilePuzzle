package server.views;

import server.modules.GameBoard;
import server.modules.Movement;

import java.util.ArrayList;

public class BoardRepresentation {

    public StringBuilder gameBoardRepresentation(GameBoard gameBoard, ArrayList<Movement> possibleMoves) {
        StringBuilder result = new StringBuilder(gameBoard.toString());
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
        result.append('\n');
        return result;
    }
}
