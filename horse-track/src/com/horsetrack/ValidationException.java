package com.horsetrack;

/**
 * @author Pavlo Cherkashyn
 */
public class ValidationException extends Exception {

    public static final String INVALID_BET = "Invalid Bet: ";
    public static final String INVALID_HORSE_NUMBER = "Invalid Horse Number: ";
    public static final String INVALID_COMMAND = "Invalid Command: ";

    public ValidationException(String s) {
        super(s);
    }
}
