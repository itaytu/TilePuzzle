package server;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public final class GameController {


    public void initGame(int boardSize) throws IOException {
        if (!isSizeInputValid(boardSize))
            line = br.readLine();


        /*for (int i = 0; i < strs.length; i++) {
            a[i] = Integer.parseInt(strs[i]);
        }
        int num = sc.nextInt();
        while (!isSizeInputValid(num)){
            num = sc.nextInt();
        }*/
    }




    private boolean isSizeInputValid(int boardSize){
        return boardSize > 0;
    }
}
