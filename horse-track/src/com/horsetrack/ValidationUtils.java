package com.horsetrack;

/**
 * @author Pavlo Cherkashyn
 */
public class ValidationUtils {
    public static boolean checkCommandIsNotEmpty(String line) {
        return !(line == null || line.trim().isEmpty());
    }

    public static void validateHorseNumber(HorseTrackGameState game, int horseNumber) throws ValidationException {
        if (!game.isHorseNumberAvailable(horseNumber)) {
            ValidationUtils.handleInvalidHorseNumber(horseNumber);
        }
    }

    public static int validateAmountValue(String line, String amountStr) throws ValidationException {
        int amount = 0;
        try {
            amount = Integer.parseInt(line.substring(1).trim());
        } catch (NumberFormatException nfe) {
            ValidationUtils.handleInvalidAmount(amountStr);
        }
        if (amount < 0) {
            ValidationUtils.handleInvalidAmount(amountStr);
        }
        return amount;
    }



    public static void handleInvalidAmount(String amountStr) throws ValidationException {
        throw new ValidationException(ValidationException.INVALID_BET + amountStr);
    }

    public static void handleInvalidHorseNumber(int horseNumber) throws ValidationException {
        throw new ValidationException(ValidationException.INVALID_HORSE_NUMBER + horseNumber);
    }

    public static void handleInvalidHorseNumber(String horseNumberStr) throws ValidationException {
        throw new ValidationException(ValidationException.INVALID_HORSE_NUMBER + horseNumberStr);
    }

    public static void handleInvalidCommand(String command) throws ValidationException {
        throw new ValidationException(ValidationException.INVALID_COMMAND + command);
    }
}
