package com.horsetrack;

import java.util.Map;

/**
 * @author Pavlo Cherkashyn
 */
public interface Presenter {
    public void showErrorMessage(String message);
    public void showMessage(String message);
    public void listHorses(HorseTrackGameState state);
    public void listInventory(HorseTrackGameState state);
    public void listDispenseBills(String horseName, int amount, Map<Integer, Integer> bills);
}
