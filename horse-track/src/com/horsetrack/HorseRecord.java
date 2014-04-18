package com.horsetrack;

/**
 * @author Pavlo Cherkashyn
 */
public class HorseRecord {
    private String name;
    private int odds;

    public HorseRecord(String name, int odds) {
        this.name = name;
        this.odds = odds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOdds(int odds) {
        this.odds = odds;
    }

    public String getName() {
        return name;
    }

    public int getOdds() {
        return odds;
    }
}
