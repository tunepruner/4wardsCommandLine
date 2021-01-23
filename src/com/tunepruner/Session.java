package com.tunepruner;

import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
    private int sessionID;
    private String clockInTime;
    private String clockInDate;
    private String clockOutTime;
    private String clockOutDate;
    private int focusLevel = 0;
    private int relevanceLevel = 0;
    static int lastIdUsed = 1;

    public Session(){
        sessionID = lastIdUsed++;
        this.clockInTime = LocalTime.now().toString();
        this.clockInDate = LocalDate.now().toString();
    }

    public void setClockInDate(LocalDate date) {
        this.clockInDate = clockInDate;
    }

    public void setClockOutDate(LocalDate date) {
        this.clockOutDate = date.toString();
    }

    public String getClockInDate() {
        return clockInDate;
    }

    public String getClockOutDate() {
        return clockOutDate;
    }

    public int getSessionID() {
        return sessionID;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }

    public int getFocusLevel() {
        return focusLevel;
    }

    public int getRelevanceLevel() {
        return relevanceLevel;
    }

    public void setFocusLevel(int focusLevel) {
        this.focusLevel = focusLevel;
    }

    public void setRelevanceLevel(int relevanceLevel) {
        this.relevanceLevel = relevanceLevel;
    }

    public void setClockInTime(LocalTime clockInTime) {
        this.clockInTime = clockInTime.toString();
    }

    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime.toString();
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}
