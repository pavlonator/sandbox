package com.horsetrack;

/**
 * @author Pavlo Cherkashyn
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(int payoutAmount) {
        super("Insufficient Funds:"+payoutAmount);
    }
}
