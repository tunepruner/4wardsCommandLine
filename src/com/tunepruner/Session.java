package com.tunepruner;

import javax.xml.bind.annotation.*;
import java.time.LocalTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {
    @XmlAttribute
    private int sessionID;
    @XmlAttribute
    private LocalTime clockIn;
    @XmlAttribute
    private LocalTime clockOut;
    @XmlAttribute
    private int focusLevel = 0;
    @XmlAttribute
    private int relevanceLevel = 0;
    static int lastIdUsed = 1;

    public Session(){

    }


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
        sessionID = lastIdUsed++;
        this.clockIn = clockIn;
    }

    public void setFocusLevel(int focusLevel) {
        this.focusLevel = focusLevel;
    }

    public void setRelevanceLevel(int relevanceLevel) {
        this.relevanceLevel = relevanceLevel;
    }


    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }




}
