package com.tunepruner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private static List<Session> sessions = new ArrayList<>();

    public static void add(Session currentSession) {
        sessions.add(currentSession);
    }

    public static Session get(int sessionID) {
        Session sessionToReturn = null;
        for ( Session session : sessions ) {
            if (session.getSessionID() == sessionID) {
                sessionToReturn = session;
            }
        }
        if (sessionToReturn == null)
            throw new IllegalArgumentException();
        else return sessionToReturn;
    }

    public static void printLastThreeSessions() {
        System.out.println("----------LAST THREE SESSIONS--------------");
        for ( Session session : sessions ) {
            System.out.println("---------");
            System.out.println("SessionID: " + session.getSessionID());
            System.out.println("ClockIn: " + session.getClockInTime() + "  --  " + session.getClockInDate());
            System.out.println("ClockOut: " + session.getClockOutTime() + "  --  " + session.getClockOutDate());
            System.out.println("FocusLevel: " + session.getFocusLevel());
            System.out.println("RelevanceLevel: " + session.getRelevanceLevel());
        }

    }

    public void manualAddSession() {

    }

    public static void persist() throws IOException {
        String fileName = "sessions.json";

        Gson gson = new Gson();

        Session[] sessionsArray = new Session[sessions.size()];
        for (int i = 0; i < sessions.size(); i++){
            sessionsArray[i] = sessions.get(i);
        }

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(sessionsArray, fileWriter);
        }

    }

    public static void readFromFile() throws IOException {
        String fileName = "sessions.json";

        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        } else if (file.length() != 0) {
            Gson gson = new Gson();

            try (FileReader fileReader = new FileReader(fileName);
                 JsonReader jsonReader = new JsonReader(fileReader);
            ) {

                Session[] sessionArray = gson.fromJson(jsonReader, Session[].class);

                for (int i = 0; i < sessionArray.length; i++) {
                    sessions.add(i, sessionArray[i]);
                }
            }
        }

        Session.lastIdUsed = sessions.size() + 1;
    }

    private List<Session> getSessions() {
        return sessions;
    }

    //TODO add method for removing sessions
    //TODO add method for manual adding sessions

}
