package com.phonepe.calendar.helpers;

import com.phonepe.calendar.models.CalendarSlot;
import com.phonepe.calendar.models.Shift;

import java.util.Set;

public class ValidationUtils {

    public static boolean validateSlot(Set<Shift> shiftSlots, CalendarSlot calendarSlot) {

        for (Shift shift : shiftSlots) {
            if (calendarSlot.getStart() < shift.getStart() || calendarSlot.getEnd() > shift.getEnd())
                return false;
        }
        return true;
    }
}
