package com.phonepe.calendar.services;

import com.phonepe.calendar.helpers.IntervalUtils;
import com.phonepe.calendar.helpers.ValidationUtils;
import com.phonepe.calendar.exceptions.UserNotAllowedException;
import com.phonepe.calendar.models.*;
import com.phonepe.calendar.repositories.MeetingRepository;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingService {

    private final MeetingRepository meetingRepository;

    private final Lock lock = new ReentrantLock();

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public void createMeeting(Meeting meeting) {
        try {
            //TODO : to check later for concurrency
            lock.lock();
            CalendarSlot calendarSlot =  meeting.getCalendarSlot();
            meeting.getCreatedBy().getStatusMap().put(calendarSlot, Status.BUSY);
            List<User> participants = meeting.getParticipants();

            for (User user : participants) {
               if (!ValidationUtils.validateSlot(user.getShifts(), calendarSlot)) {
                   throw new RuntimeException("Meeting cannot be created outside user shift");
               }
               user.getStatusMap().put(calendarSlot, Status.BUSY);
            }

            meetingRepository.addMeeting(meeting);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            lock.unlock();
        }
    }

    public boolean deleteMeeting(Meeting meeting, User userBy) throws Exception {
        if (!meeting.getCreatedBy().getName().equalsIgnoreCase(userBy.getName())) {
            throw new UserNotAllowedException("User is not allowed to modify the meeting");
        }
        try {
            //TODO : to check later for concurrency
            lock.lock();
            CalendarSlot calendarSlot =  meeting.getCalendarSlot();
            meeting.getCreatedBy().getStatusMap().put(calendarSlot, Status.FREE);
            List<User> participants = meeting.getParticipants();
            for (User user : participants) {
                CalendarSlot userCalendarSlot =  meeting.getCalendarSlot();
                user.getStatusMap().put(userCalendarSlot, Status.FREE);
            }
            meetingRepository.deleteMeeting(meeting);
        } catch (Exception e) {
            return false;
        } finally {
            lock.unlock();
        }
        return true;
    }


    public List<Meeting> getMeetings(User user) {
        return meetingRepository.getMeetingListForUser(user);
    }

    public Set<Meeting> getConflictingmeetings(User user) {
        List<Meeting> meetings = meetingRepository.getMeetingListForUser(user);
        return getConflictingMeetings(meetings);
    }

    private Set<Meeting> getConflictingMeetings(List<Meeting> meetingList) {
        // Get all the meetings
        meetingList.sort(Comparator.comparingInt(o -> o.getCalendarSlot().getStart()));
        Set<Meeting> conflictingMeetings = new HashSet<>();
        for (int i = 0; i < meetingList.size() - 1; i++) {
            if (IntervalUtils.isConflicting(meetingList.get(i), meetingList.get(i + 1))) {
                conflictingMeetings.add(meetingList.get(i));
                conflictingMeetings.add(meetingList.get(i + 1));
            }
        }
        return conflictingMeetings;
    }
    //check all pairs

    public CalendarSlot getFavourableSlot(User organiser, List<User> participants, int duration) {

        // Algorithm : Get each user slot and check with duration
        // Condition 1: Those above slots should be free or else if busy then find next slot as long as it not free
        // Condition 2: Accumulate all the empty slots from all the users and return the emptiest one

        Set<CalendarSlot> calendarSlotOfParticipants = new TreeSet<>();
        participants.add(organiser);


        Set<Shift> allShifts = new TreeSet<>();

        for (User user : participants) {
            for (Map.Entry<CalendarSlot, Status> slots : user.getStatusMap().entrySet()) {
                if (slots.getValue() == Status.BUSY) {
                    calendarSlotOfParticipants.add(slots.getKey());
                }
            }
            allShifts.addAll(user.getShifts());
        }
        Set<CalendarSlot> calendarSlots = IntervalUtils.findEmptyIntervals(allShifts);
        calendarSlotOfParticipants.addAll(calendarSlots);


        return IntervalUtils.findEmptyInterval(calendarSlotOfParticipants, duration);
    }

}