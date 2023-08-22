package com.phonepe.calendar.models;

import com.sun.source.tree.Tree;

import java.util.*;

public class User {

    private String name;
    private String emailId;


    private Map<CalendarSlot, Status> statusMap;

    private Set<Shift> shifts;


    public User(String name, String emailId, Set<Shift> shifts) {
        this.name = name;
        this.emailId = emailId;
        this.shifts = new TreeSet<>(shifts);
        this.statusMap = new TreeMap<>(Comparator.comparingInt(CalendarSlot::getStart));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Map<CalendarSlot, Status> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<CalendarSlot, Status> statusMap) {
        this.statusMap = statusMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(emailId, user.emailId) && Objects.equals(statusMap, user.statusMap) && Objects.equals(shifts, user.shifts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailId, statusMap, shifts);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", statusMap=" + statusMap +
                ", shifts=" + shifts +
                '}';
    }
}
