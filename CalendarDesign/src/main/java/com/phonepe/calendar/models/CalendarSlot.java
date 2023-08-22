package com.phonepe.calendar.models;


import java.util.Date;
import java.util.Objects;

public class CalendarSlot implements Comparable<CalendarSlot> {

    private int start;
    private int end;

    private Date dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarSlot that = (CalendarSlot) o;
        return start == that.start && end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "CalendarSlot{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public CalendarSlot(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public int compareTo(CalendarSlot o) {
        return this.getStart() - o.getStart();
    }
}
