package client;

import server.GameController;
import server.modules.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        GameController gameController = new GameController();
        boolean isPlaying = true;
        String input;
        String pattern = "^([1-9][\\d]*)$";
        Pattern r = Pattern.compile(pattern);
        // Client side verification
        do {
            input = reader.readLine();
        } while (!r.matcher(input).find());
        // Server side verification
        Response response = gameController.initGame(input);     // Game initiated
        while (response.getStatus().equals("Failed")) {
            System.out.println(response.getResponseMessage());
            input = reader.readLine();
            response = gameController.initGame(input);
        }
        System.out.println(response.getResponseMessage() + '\n' + response.getGameBoard());
        while (isPlaying) {
            input = reader.readLine();
            response = gameController.playerMove(input);
            // Player ended game
            if (response.isGameOver()){
                isPlaying = false;
                System.out.println(response.getResponseMessage());
            }
            else {
                // Invalid movement
                if (response.getStatus().equals("Failed"))
                    System.out.println(response.getResponseMessage());
                // Valid movement
                else
                    System.out.println(response.getResponseMessage() + '\n' + response.getGameBoard());
            }
        }
        reader.close();
    }
}
