package com.phonepe.calendar.models;

import java.util.List;
import java.util.Objects;

public class EventRecurrence {

    private CalendarFrequency frequency;
    private int count;
    private List<Day> daysOfTheWeek;

    public EventRecurrence(CalendarFrequency frequency, int count, List<Day> days) {
        this.frequency = frequency;
        this.count = count;
        this.daysOfTheWeek = days;
    }

    public CalendarFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(CalendarFrequency frequency) {
        this.frequency = frequency;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Day> getDays() {
        return daysOfTheWeek;
    }

    public void setDays(List<Day> days) {
        this.daysOfTheWeek = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRecurrence that = (EventRecurrence) o;
        return count == that.count && frequency == that.frequency && Objects.equals(daysOfTheWeek, that.daysOfTheWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, count, daysOfTheWeek);
    }

    @Override
    public String toString() {
        return "EventRecurrence{" +
                "frequency=" + frequency +
                ", count=" + count +
                ", days=" + daysOfTheWeek +
                '}';
    }
}
