package com.phonepe.calendar.helpers;

import com.phonepe.calendar.models.CalendarSlot;
import com.phonepe.calendar.models.Meeting;
import com.phonepe.calendar.models.Shift;

import java.util.Set;
import java.util.TreeSet;

public class IntervalUtils {

    public static CalendarSlot findEmptyInterval(Set<CalendarSlot> intervals, int desiredDuration) {
//        intervals.sort(Comparator.comparingInt(CalendarSlot::getStart));

        int previousEndTime = 0;

        for (CalendarSlot interval : intervals) {
            if (interval.getStart() - previousEndTime >= desiredDuration) {
                return new CalendarSlot(previousEndTime, previousEndTime + desiredDuration);
            }
            previousEndTime = Math.max(previousEndTime, interval.getEnd());
        }
        if (previousEndTime == 24) {
            return new CalendarSlot(-1, -1);
        }

        return new CalendarSlot(previousEndTime, previousEndTime + desiredDuration);
    }

    public static Set<CalendarSlot> findEmptyIntervals(Set<Shift> shifts) {
//        intervals.sort(Comparator.comparingInt(CalendarSlot::getStart));
        Set<CalendarSlot> calendarSlots = new TreeSet<>();

        int previousEndTime = 0;

        for (Shift shift : shifts) {
            if (shift.getStart() >  previousEndTime) {
                calendarSlots.add(new CalendarSlot(previousEndTime, shift.getStart()));
            }
            previousEndTime = Math.max(previousEndTime, shift.getEnd());
        }

        calendarSlots.add(new CalendarSlot(previousEndTime, 24));
        return calendarSlots;
    }

    public static boolean isConflicting(Meeting meeting1, Meeting meeting2) {
        if (meeting1.getCalendarSlot().getEnd() > meeting2.getCalendarSlot().getStart())
            return true;
        if (meeting1.getCalendarSlot().getStart() < meeting2.getCalendarSlot().getEnd())
            return true;
        return false;
    }
}
