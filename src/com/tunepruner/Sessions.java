package com.tunepruner;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private static List<Session> sessions = new ArrayList<>();

    public static void add(Session currentSession) {
        sessions.add(currentSession);
    }

    public static void printLastThreeSessions() {
        System.out.println("----------LIST OF SESSIONS--------------");
        for ( Session session : sessions ) {
            System.out.println("---------");
            System.out.println("SessionID: " + session.getSessionID());
            System.out.println("ClockIn: " + session.getClockIn());
            System.out.println("ClockOut: " + session.getClockOut());
            System.out.println("FocusLevel: " + session.getFocusLevel());
            System.out.println("RelevanceLevel: " + session.getRelevanceLevel());
        }

        //For (Session session : sessions)
        //Print
        //- "Session: " + sessionID
        //- startTime
        //- endTime
        //- focus level
        //- relevance
    }

    public static void adjustSession() {
        //Prompt sessionID
        //Prompt parameter
        //Prompt new value
    }

    public void manualAddSession() {
    }
}
