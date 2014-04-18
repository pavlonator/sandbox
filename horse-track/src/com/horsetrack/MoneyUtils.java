package com.horsetrack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavlo Cherkashyn
 */
public class MoneyUtils {
    public static final int[] BILLS = {100, 20, 10, 5, 1};


    private static Map<Integer, Integer> splitAmountToBills(int amount) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        int amountUnprocessed = amount;
        for (int bill : BILLS) {
            int howManyBills = amountUnprocessed / bill;
            if (howManyBills > 0) {
                result.put(bill, howManyBills);
                amountUnprocessed -= bill * howManyBills;
            }
        }
        return result;
    }

    public static void putMoneyToInventory(int amount, Map<Integer, Integer>  inventory) {
        Map<Integer, Integer> bills = splitAmountToBills(amount);
        for (int bill : BILLS) {
            if(bills.containsKey(bill)){
                inventory.put(bill, inventory.get(bill) + bills.get(bill));
            }
        }
    }

    public static Map<Integer, Integer> dispenseAmount(Map<Integer, Integer> inventory, int amount) throws InsufficientFundsException {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        int amountUncovered = amount;

        for (int bill : BILLS) {
            int billsAvailable = inventory.get(bill);
            int billsNeeded = amountUncovered / bill;
            if (billsNeeded < billsAvailable) {
                result.put(bill, billsNeeded);
                amountUncovered -= billsNeeded * bill;
            } else {
                result.put(bill, billsAvailable);
                amountUncovered -= billsAvailable * bill;
            }
        }

        if (amountUncovered > 0) {
            throw new InsufficientFundsException(amount);
        } else {
            takeMoneyFromInventory(inventory, result);
        }
        return result;
    }

    private static void takeMoneyFromInventory(Map<Integer, Integer> inventory, Map<Integer, Integer> result) {
        for (int bill : BILLS) {
            if(result.containsKey(bill)){
                inventory.put(bill, inventory.get(bill) - result.get(bill));
            }
        }
    }
}
