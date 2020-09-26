package server;

import server.modules.GameBoard;
import java.io.IOException;

public final class GameController {

    private GameBoard gameBoardSolution;

    public boolean initGame(double boardSize) throws IOException {

        if (!isSizeInputValid(boardSize))
            return false;

        gameBoardSolution = new GameBoard((int) boardSize);
        return true;
        /*if (!isSizeInputValid(boardSize))
            line = br.readLine();*/


        /*for (int i = 0; i < strs.length; i++) {
            a[i] = Integer.parseInt(strs[i]);
        }
        int num = sc.nextInt();
        while (!isSizeInputValid(num)){
            num = sc.nextInt();
        }*/
    }




    private boolean isSizeInputValid(double boardSize){
        return boardSize > 0 && boardSize == Math.floor(boardSize);
    }
}
