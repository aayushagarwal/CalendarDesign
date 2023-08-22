package com.phonepe.calendar.models;

import com.phonepe.calendar.exceptions.ShiftNotValidException;

import java.util.Objects;

// Only for users
public class Shift implements Comparable<Shift> {

    private int start; // Start from 0 to 23

    private int end; // end from 0 to 23

    //TODO : Add an enum for full busy

    public Shift(int start, int end) {
        this.start = start;
        this.end = end;
        if (!isValid()) {
            throw new ShiftNotValidException("Shift is not valid. Please enter correct shift");
        }
    }

    public boolean isValid() {
        if (start < 0 && start > 23)
            return false;

        return end >= 0 && end <= 23 && end >= start;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return start == shift.start && end == shift.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public int compareTo(Shift o) {
       return this.getStart() - o.getStart();
    }
}
