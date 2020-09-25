package client;

import server.GameController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        while (true){
            gameController.initGame();
        }
        sc.close();
    }
}
