package com.tunepruner;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sessions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sessions {
    @XmlElement(name = "session", type = Session.class)
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
            System.out.println("ClockIn: " + session.getClockIn());
            System.out.println("ClockOut: " + session.getClockOut());
            System.out.println("FocusLevel: " + session.getFocusLevel());
            System.out.println("RelevanceLevel: " + session.getRelevanceLevel());
        }

    }

    public void manualAddSession() {

    }

    public static void persist() {
        try {
            Sessions sessions = new Sessions();
            JAXBContext context = JAXBContext.newInstance(Sessions.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(sessions, sw);


            File f = new File("./programfiles/sessions.xml");
            marshaller.marshal(sessions, f);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public static void readFromFile() throws JAXBException, IOException {
        File theDir = new File("./programfiles");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        File f = new File("./programfiles/sessions.xml");
        if (!f.exists()) {
            f.createNewFile();
        }
        else if (f.length() != 0){
            JAXBContext jaxbContext = JAXBContext.newInstance(Sessions.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Sessions unmarshalledObject = (Sessions) unmarshaller.unmarshal(f);
            sessions = unmarshalledObject.getSessions();

            Session.lastIdUsed = sessions.size() + 1;
        }
    }

    private List<Session> getSessions() {
        return sessions;
    }

    //TODO add method for removing sessions
    //TODO add method for manual adding sessions

}
