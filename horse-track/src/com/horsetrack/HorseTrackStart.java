package com.horsetrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Pavlo Cherkashyn
 */
public class HorseTrackStart {
    public static void main(String[] args) {
        Presenter gamePresenter = new ConsolePrintoutPresenter();
        HorseTrackGameController gameController = new HorseTrackGameController(gamePresenter);
        try {
            runGameLoop(gameController, gamePresenter);
        } catch (IOException e) {
            System.err.println("problems with console input, exiting the application");
            e.printStackTrace();
        }
    }

    private static void runGameLoop(HorseTrackGameController gameController, Presenter presenter) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gameController.onGameStart();
        while (true) {
            try {
                String line = br.readLine();
                if(! ValidationUtils.checkCommandIsNotEmpty(line)) continue;

                line = line.trim().toLowerCase();

                if (isQuitCommand(line)) {
                    gameController.exit();
                    System.exit(0);
                } else if (isRestockCommand(line)) {
                    gameController.restockTheCashInventory();
                } else if (isWinnerSetupCommand(line)) {
                    gameController.performWinnerSetupCommand(line);
                } else if (isBetCommand(line)) {
                    gameController.performBetAttempt(line);
                } else {
                    ValidationUtils.handleInvalidCommand(line);
                }
            } catch (ValidationException ve) {
                presenter.showErrorMessage(ve.getMessage());
            }

        }
    }

    private static boolean isWinnerSetupCommand(String line) {
        return line.startsWith("w");
    }

    private static boolean isBetCommand(String line) {
        return Character.isDigit(line.charAt(0));
    }

    private static boolean isRestockCommand(String line) {
        return line.equals("r");
    }

    private static boolean isQuitCommand(String line) {
        return line.trim().equalsIgnoreCase("q");
    }


}
