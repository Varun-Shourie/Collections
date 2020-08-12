package com.varunshourie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /*
    Change the program to allow players to type full words, or phrases, then move to the correct location based upon
    their input.

    The player should be able to type commands such as "Go West", "Run South", or just "East" and the program will move
    to the appropriate location if there is one. As at present, an attempt to move in an invalid direction should print
    a message and remain in the same place.

    Single letter commands (N, W, S, E, Q) should still be available.

     */

    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java."));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick" +
                  "building"));
        locations.put(2, new Location(2, "You are at the top of the hill."));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring."));
        locations.put(4, new Location(4, "You are in a valley beside a stream."));
        locations.put(5, new Location(5, "You are in the forest."));

        Map<String, String> synonyms = new HashMap<>();

        locations.get(1).addExit("W", 2);
        synonyms.put("WEST", "W");
        locations.get(1).addExit("E", 3);
        synonyms.put("EAST", "E");
        locations.get(1).addExit("S", 4);
        synonyms.put("SOUTH", "S");
        locations.get(1).addExit("N", 5);
        synonyms.put("NORTH", "N");
        synonyms.put("QUIT", "Q");

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        int loc = 1;
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");
            for(String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            String[] instructions = direction.split(" ");

            boolean locFound = false;

            for(String s : instructions) {
                if(synonyms.containsKey(s)) {
                    loc = exits.get(synonyms.get(s));
                    locFound = true;
                }
                else if(exits.containsKey(s)) {
                    loc = exits.get(s);
                    locFound = true;
                }
            }

            if(!locFound) {
                System.out.println("You cannot go in that direction.");
            }

            if(!locations.containsKey(loc)) {
                System.out.println("You cannot go in that direction.");
            }
        }
    }
}
