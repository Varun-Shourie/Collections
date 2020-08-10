package com.varunshourie;

import java.util.*;

/**
 * Author: Varun Shourie
 * Purpose: Review the List interface methods provided in the Collections library.
 * Summary: For the purpose of this exercise, we will create a booking system
 * for a restaurant where...
 *  - Each small table has four people in it.
 *  - Each medium table has eight people in it.
 *  - Each large table has twelve people in it.
 *
 *  The customer will be able to reserve the seats depending on the size
 *  of their party.
 *
 *  
 */
public class Main {

    private static List<Table> tableList = new ArrayList<Table>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        instantiateTables();

        boolean reservation1 = reserveTable();
        boolean reservation2 = reserveTable();
        boolean reservation3 = reserveTable();

        findOpenTables();
    }

    private static boolean instantiateTables() {
        Table tmpTable;

        for(int i = 0; i < 3; i++) {
            tmpTable = new Table((char) ('A' + i), 3);
            Collections.addAll(tableList, tmpTable);
        }

        for(int i = 3; i < 6; i++) {
            tmpTable = new Table((char) ('A' + i), 6);
            Collections.addAll(tableList, tmpTable);
        }

        for(int i = 6; i < 9; i++) {
            tmpTable = new Table((char) ('A' + i), 9);
            Collections.addAll(tableList, tmpTable);
        }

        return tableList.size() == 9;
    }

    private static boolean reserveTable() {
        System.out.println("Please enter the table name you want to reserve (A-I): ");
        String tableName = scanner.nextLine();

        int reservedTable = findTable(tableName);

        if(reservedTable == -1) {
            System.out.println("Invalid table name.");
            return false;
        }
        else {
            tableList.get(reservedTable).bookTable();
            return true;
        }
    }

    private static void findOpenTables() {

        for(Table t : tableList) {
            t.openSeats();
        }
    }

    private static int findTable(String tableName) {

        if(tableName.length() > 1) {
            System.out.println("Invalid table name. Please exit.");
        }
        else {
            char table = tableName.charAt(0);

            int loopCounter = 0;

            for(Table t : tableList) {

                if(t.getTableName() == table) {
                    return loopCounter;
                }

                loopCounter++;
            }

        }
        return -1;

    }
}
