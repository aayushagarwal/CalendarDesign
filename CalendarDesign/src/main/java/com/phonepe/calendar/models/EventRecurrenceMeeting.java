package com.phonepe.calendar.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EventRecurrenceMeeting extends Meeting {
    private boolean isRecurring;
    private EventRecurrence eventRecurrence;

    public EventRecurrenceMeeting(CalendarSlot calendarSlot, User createdBy, List<User> participants,
                                  boolean isRecurring, EventRecurrence eventRecurrence) {
        super(calendarSlot, createdBy, participants);
        this.isRecurring = isRecurring;
        this.eventRecurrence = eventRecurrence;
    }

}
