package com.varunshourie;

import java.util.HashMap;
import java.util.Map;

public class MapProgram {

    public static void main(String[] args) {

        Map<String, String> languages = new HashMap<>();

        if(languages.containsKey("Java"))
            System.out.println("Java already exists.");
        else {
            languages.put("Java", "a compiled high-level object-oriented, platform independent language.");
            System.out.println("Java added successfully.");
        }

        languages.put("Python", "an interpreted, object-oriented, high-level programming language with dynamic semantics.");
        languages.put("Algol", "an algorithmic language");
        languages.put("BASIC", "Beginners All Purpose Symbolic Construction Code");
        languages.put("Lisp", "Therein lies madness.");

        // A particular key can only have one value - it can be overwritten.
        // System.out.println(languages.get("Java"));
        System.out.println(languages.put("Java", "This course is about Java."));
        System.out.println(languages.get("Java"));

        if(languages.containsKey("Java")) {
            System.out.println("Java is already in the map.");
        }
        else {
            languages.put("Java", "this course is about Java.");
        }

        System.out.println("==============================================================");

        // Successfully removes Lisp from the map.
        // languages.remove("Lisp");
        if(languages.remove("Algol", "an algorithmic language")) {
            System.out.println("Algol removed.");
        }
        else {
            System.out.println("Algol not removed, key/value pair not found.");
        }

        if (languages.replace("Lisp", "Therein lies madness.",
                "a functional programming language with imperative features.")) {
            System.out.println("Lisp replaced.");
        }
        else {
            System.out.println("Lisp was not replaced.");
        }

        // System.out.println(languages.replace("Scala", "this will not be added.")); // it doesn't exist on file.

        for(String key : languages.keySet()) {
            System.out.println(key + " : " + languages.get(key));
        }
    }
}
