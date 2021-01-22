package com.tunepruner;

import java.time.LocalTime;

public class Session {  private int sessionID;

    private LocalTime clockIn;
    private LocalTime clockOut;
    private int focusLevel = 0;
    private int relevanceLevel = 0;
    static int lastIdUsed = 0;


    public int getSessionID() {
        return sessionID;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public int getFocusLevel() {
        return focusLevel;
    }

    public int getRelevanceLevel() {
        return relevanceLevel;
    }


    public Session(LocalTime clockIn){
        lastIdUsed++;
        this.clockIn = clockIn;
    }

    public void setFocusLevel(int focusLevel) {
        this.focusLevel = focusLevel;
    }

    public void setRelevanceLevel(int relevanceLevel) {
        this.relevanceLevel = relevanceLevel;
    }


    public void clockOut(){
        //Set endTime
        //add to list
    }

    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }



}
