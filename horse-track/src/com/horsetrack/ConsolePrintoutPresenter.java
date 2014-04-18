package com.horsetrack;

import java.util.Map;

/**
 * @author Pavlo Cherkashyn
 */
public class ConsolePrintoutPresenter implements Presenter {
    @Override
    public void showErrorMessage(String message) {
        System.err.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void listHorses(HorseTrackGameState state) {
        System.out.println("Horses:");
        int num = 1;
        for(HorseRecord horse: state.getHorses()){
            System.out.println(String.format("%d,%s,%d,%s", num, horse.getName(), horse.getOdds(), (state.getWinner()==num)?"won":"lost"));
            num++;
        }
    }

    @Override
    public void listInventory(HorseTrackGameState state) {
        System.out.println("Inventory:");
        for(int i = MoneyUtils.BILLS.length - 1; i >= 0; i--){
            int bill = MoneyUtils.BILLS[i];
            System.out.println( String.format("%d,%d", bill, state.getInventory().get(bill)));
        }
    }

    @Override
    public void listDispenseBills(String horseName, int amount, Map<Integer, Integer> bills) {
        System.out.println(String.format("Payout:%s,%d", horseName, amount));
        System.out.println("Dispensing:");
        for(int bill:MoneyUtils.BILLS){
            System.out.println( String.format("%d,%d", bill, bills.get(bill)));
        }
    }
}
