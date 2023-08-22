package com.phonepe.calendar;

import com.phonepe.calendar.models.*;
import com.phonepe.calendar.repositories.MeetingRepository;
import com.phonepe.calendar.repositories.UserRepository;
import com.phonepe.calendar.services.MeetingService;
import com.phonepe.calendar.services.UserService;

import java.util.*;

public class Driver {

    public static void main(String[] args) {

        Shift shift1 = new Shift(1, 10);
        Shift shift2 = new Shift(1, 10);
        Shift shift3 = new Shift(1, 10);


        User user1 = new User("A", "abc@", new TreeSet<>(List.of(shift1)));
        User user2 = new User("B", "df@", new TreeSet<>(List.of(shift2)));
        User user3 = new User("C", "fdsf@", new TreeSet<>(List.of(shift3)));


        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);


        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        MeetingRepository meetingRepository = new MeetingRepository();
        MeetingService meetingService = new MeetingService(meetingRepository);

        CalendarSlot calendarSlot1 = new CalendarSlot(2, 5);
        CalendarSlot calendarSlot2 = new CalendarSlot(7, 8);
        CalendarSlot calendarSlot3 = new CalendarSlot(8, 10);

        Meeting meeting1 = new Meeting(calendarSlot1, user1, Arrays.asList(user2, user3));
        Meeting meeting2 = new Meeting(calendarSlot2, user1, Arrays.asList(user2, user3));
        Meeting meeting3 = new Meeting(calendarSlot3, user1, Arrays.asList(user2, user3));


        char[] charArray = ("" + 34433).toCharArray();
        List<Integer> digits = new ArrayList<>();
        digits.add(0);
        for (char c : charArray) {
            digits.add(c - 48);
        }
        System.out.println(digits);
        meetingService.createMeeting(meeting1);
        meetingService.createMeeting(meeting2);
        meetingService.createMeeting(meeting3);

        System.out.println(meetingService.getMeetings(user1));




        System.out.println(meetingService.getMeetings(user1));

        System.out.println("Conflicting meetings " + meetingService.getConflictingmeetings(user1));

        try {
            System.out.println(meetingService.deleteMeeting(meeting1, user1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        meetingService.createMeeting(meeting1);

        CalendarSlot calendarSlot = meetingService.getFavourableSlot(user1, new ArrayList<>(Arrays.asList(user2, user3)), 1);
        if (calendarSlot.getStart() == -1) {
            System.out.println("No favourable slot");
        } else {
            System.out.println("Favourable slot is " + calendarSlot);
        }


        calendarSlot = meetingService.getFavourableSlot(user1, new ArrayList<>(Arrays.asList(user2, user3)), 2);
        if (calendarSlot.getStart() == -1) {
            System.out.println("No favourable slot");
        } else {
            System.out.println("Favourable slot is " + calendarSlot);
        }

        EventRecurrence eventRecurrence = new EventRecurrence(CalendarFrequency.WEEKLY, 6, Arrays.asList(Day.MONDAY));

        EventRecurrenceMeeting eventRecurrenceMeeting =
                new EventRecurrenceMeeting(calendarSlot3, user1, Arrays.asList(user2, user3), true, eventRecurrence);

        meetingService.createMeeting(eventRecurrenceMeeting);

        System.out.println(meetingService.getMeetings(user1));
    }

}
