package com.varunshourie;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {

    private final char tableName;
    private final int numberOfSeats;
    private ArrayList<Seat> seats;
    private Seat tmpSeat;

    public Table(char tableName, int numberOfSeats) {
        this.tableName = tableName;
        this.numberOfSeats = numberOfSeats;
        this.seats = new ArrayList<>();
        this.tmpSeat = new Seat(0);

        if(createSeats()) {
            System.out.println(this.numberOfSeats + " seats have been correctly created for table " + this.tableName);
        }
    }

    public boolean bookSeat(String seatName, boolean reserved) {
        String seatNo;

        if(seatName.length() == 3)
             seatNo = seatName.charAt(1) + "" + seatName.charAt(2);
        else
             seatNo = seatName.charAt(1) + "";

        int seatNumber = Integer.parseInt(seatNo);
        Seat requestedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);

        if(foundSeat >= 0) {
            return seats.get(foundSeat).reserve(reserved);
        }
        else {
            System.out.println("There is no seat available at " + seatName + ".");
            return false;
        }
    }

    public void bookTable() {
        for(Seat s : this.seats) {
            bookSeat(s.getSeatName(), true);
        }
    }

    public void unbookTable() {
        for(Seat s : this.seats) {
            bookSeat(s.getSeatName(), false);
        }
    }

    private boolean createSeats() {

        for(int i = 0; i < this.numberOfSeats; i++) {
            Seat seat = new Seat(i+1);

            Collections.addAll(seats, seat);
            System.out.println(seat.getSeatName() + " has been created.");
        }

        return seats.size() == numberOfSeats;
    }

    public char getTableName() {
        return this.tableName;
    }

    public ArrayList<Seat> openSeats() {
        ArrayList<Seat> openSeats = new ArrayList<>();

        System.out.print("Open seats for table " + this.tableName + ": ");

        int reservedSeats = 0;

        for(Seat s : seats) {
            if(!s.isReserved()) {
                reservedSeats++;
                System.out.print(s.getSeatName() + " ");
                openSeats.add(s);
            }
        }

        if(reservedSeats == 0)
            System.out.println("NONE");
        else System.out.println();

        return openSeats;
    }

    private class Seat implements Comparable<Seat> {

        private final int seatNumber;
        private final String seatName;
        private boolean reserved;

        public Seat(int seatNumber) {
            this.seatNumber = seatNumber;
            if(seatNumber > 0 && seatNumber < 13)
                this.seatName = getTableName() + "" + seatNumber;
            else
                this.seatName = getTableName() + "" + 0;
        }

        @Override
        public int compareTo(Seat seat) {
            int comparison = this.seatName.compareToIgnoreCase(seat.getSeatName());

            return Integer.compare(comparison, 0);
        }

        public String getSeatName() {
            return this.seatName;
        }

        public int getSeatNumber() {
            return this.seatNumber;
        }

        public boolean isReserved() {
            return this.reserved;
        }

        public boolean reserve(boolean reserved) {
            if((!this.reserved) && reserved) {
                this.reserved = reserved;
                return true;
            }
            else if(this.reserved && !reserved) {
                this.reserved = reserved;
                return true;
            }

            return false;
        }

    }
}
