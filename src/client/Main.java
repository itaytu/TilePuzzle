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
        double boardSize;
        System.out.println("Hi, Welcome to the tile puzzle game:\n" +
                "The game is simple: Enter a positive size for the tile board.\n" +
                "every turn choose a move you would like to do from the available moves: U - up, D - down, L - left, R - right.\n" +
                "The game will end either when the tile puzzle is solved or by pressing Q to quit the game.");
        while (true){
            do {
                boardSize = sc.nextDouble();
            }while (!gameController.initGame(boardSize));

            break;
        }
        sc.close();
    }
}
