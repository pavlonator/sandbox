package com.horsetrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pavlo Cherkashyn
 */
public class HorseTrackGameState {
    public void setWinner(int winner) {
        this.winner = winner;
    }

    private Map<Integer, Integer> inventory;
    private List<HorseRecord> horses;
    private int winner;


    public void initialize() {
        inventory = new HashMap<Integer, Integer>();
        inventory.put(100, 10);
        inventory.put(50, 10);
        inventory.put(20, 10);
        inventory.put(10, 10);
        inventory.put(5, 10);
        inventory.put(1, 10);
        horses = new ArrayList();
        horses.add(new HorseRecord("That Darn Gray Cat", 5));
        horses.add(new HorseRecord("Fort Utopia", 10));
        horses.add(new HorseRecord("Count Sheep", 9));
        horses.add(new HorseRecord("Ms Traitour", 4));
        horses.add(new HorseRecord("Real Princess", 3));
        horses.add(new HorseRecord("Pa Kettle", 5));
        horses.add(new HorseRecord("Gin Stinger", 6));
        winner = 1;

    }

    public boolean isHorseNumberAvailable(int horseNum) {
        return horseNum > 0 && horseNum <= horses.size();
    }

    public boolean isWinningHorseNumber(int horseNumber) {
        return horseNumber == winner;
    }

    public Map<Integer, Integer> getInventory() {
        return inventory;
    }

    public int getWinner() {
        return winner;
    }

    public List<HorseRecord> getHorses() {
        return horses;
    }
}
