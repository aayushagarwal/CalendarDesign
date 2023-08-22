package com.phonepe.calendar.models;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class Meeting {

    private CalendarSlot calendarSlot;
    private User createdBy;

    private final AtomicInteger id = new AtomicInteger(0);

    private List<User> participants;

    public Meeting(CalendarSlot calendarSlot, User createdBy, List<User> participants) {
        this.calendarSlot = calendarSlot;
        this.createdBy = createdBy;
        this.participants = participants;
        id.incrementAndGet();
    }


    @Override
    public String toString() {
        return "Meeting{" +
                "calendarSlot=" + calendarSlot +
                ", createdBy=" + createdBy +
                ", id=" + id +
                ", participants=" + participants +
                '}';
    }

    public CalendarSlot getCalendarSlot() {
        return calendarSlot;
    }

    public void setCalendarSlot(CalendarSlot calendarSlot) {
        this.calendarSlot = calendarSlot;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }


    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(calendarSlot, meeting.calendarSlot) && Objects.equals(createdBy, meeting.createdBy) && Objects.equals(participants, meeting.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarSlot, createdBy, participants);
    }
}
