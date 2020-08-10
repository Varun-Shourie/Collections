package com.varunshourie;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
        this.exits = new HashMap<String, Integer>();
        this.exits.put("Q", 0);
    }

    public void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    // We're creating a new hashmap and parsing out the exits from the existing map.
    // Any changes to the new map below will not affect the exits map in this class.
    // It's a form of very defensive programming.
    public Map<String, Integer> getExits() {
        return new HashMap<String, Integer>(exits);
    }
}
