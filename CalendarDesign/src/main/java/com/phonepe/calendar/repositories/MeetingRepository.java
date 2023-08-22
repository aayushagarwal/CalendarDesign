package com.phonepe.calendar.repositories;

import com.phonepe.calendar.models.Meeting;
import com.phonepe.calendar.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingRepository {

    private final List<Meeting> meetingList;
    public MeetingRepository() {
        this.meetingList = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }

    public List<Meeting> getAllMeetings() {
        return meetingList;
    }

    public List<Meeting> getMeetingListForUser(User user) {
        return meetingList.stream()
                .filter(meeting -> meeting.getCreatedBy().getName().equalsIgnoreCase(user.getName()))
                .collect(Collectors.toList());
    }

    public void deleteMeeting(Meeting meeting) {
        meetingList.remove(meeting);
    }

}
