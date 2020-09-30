package client;

import server.controllers.GameController;
import server.models.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        GameController gameController = new GameController();
        System.out.println("Hi, Welcome to the tile puzzle game.");

        boolean isPlaying = true;
        String input;
        String pattern = "^([1-9][\\d]*)$";
        Pattern r = Pattern.compile(pattern);

        do {                                                    // Client side verification
            System.out.println("Please enter a positive integer size for the board.");
            input = reader.readLine();
        } while (!r.matcher(input).find());

        Response response = gameController.initGame(input);     // try to initiate game

        while (response.getStatus().equals("Failed")) {         // Server side verification
            System.out.println(response.getResponseMessage());
            input = reader.readLine();
            response = gameController.initGame(input);
        }
        System.out.println(response.getResponseMessage() + '\n' + response.getGameBoard());

        while (isPlaying) {                                     // Game process
            input = reader.readLine();
            response = gameController.playerMove(input);
            System.out.println(response.getResponseMessage());
            if (response.isGameOver())                          // Player ended game/game won
                isPlaying = false;
            else if (response.getStatus().equals("Success"))    // Valid movement -> fetch data
                    System.out.println(response.getGameBoard());
        }

        reader.close();
    }
}
