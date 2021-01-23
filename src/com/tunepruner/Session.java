package com.tunepruner;

import java.time.LocalTime;

public class Session {
    private int sessionID;
    private String clockIn;
    private String clockOut;
    private int focusLevel = 0;
    private int relevanceLevel = 0;
    static int lastIdUsed = 1;

    public int getSessionID() {
        return sessionID;
    }

    public String getClockIn() {
        return clockIn;
    }

    public String getClockOut() {
        return clockOut;
    }

    public int getFocusLevel() {
        return focusLevel;
    }

    public int getRelevanceLevel() {
        return relevanceLevel;
    }


    public Session(LocalTime clockIn){
        sessionID = lastIdUsed++;
        this.clockIn = clockIn.toString();
    }

    public void setFocusLevel(int focusLevel) {
        this.focusLevel = focusLevel;
    }

    public void setRelevanceLevel(int relevanceLevel) {
        this.relevanceLevel = relevanceLevel;
    }


    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn.toString();
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut.toString();
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}
