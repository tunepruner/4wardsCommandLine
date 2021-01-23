package com.tunepruner;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    private static Session currentSession;
    //TODO handle a "cancel" command.


    public static void main(String[] args) throws JAXBException, IOException {
        Sessions.readFromFile();
        prompt();
    }

    private static void prompt() throws IOException {
        Sessions.persist();
        Scanner initialScanner = new Scanner(System.in);

        System.out.print("Type a command (\"in\", \"out\", or \"adjust\"):");
        String initialCommand = initialScanner.nextLine();
        parseClockInCommand(initialCommand);
    }

    private static void parseClockInCommand(String command) throws IOException {
        boolean alreadyClockedIn = currentSession != null;

        if (command.equals("in") && !alreadyClockedIn) {
            currentSession = new Session(LocalTime.now());
            prompt();
        } else if (command.equals("in") && alreadyClockedIn) {
            System.out.println("You're already clocked in!");
            prompt();
        } else
            parseClockOutCommand(command);
    }

    private static void parseClockOutCommand(String command) throws IOException {
        boolean alreadyClockedIn = currentSession != null;

        if (command.equals("out") && alreadyClockedIn) {
            currentSession.setClockOut(LocalTime.now());
            promptAtClockOut();
        } else if (command.equals("out") && !alreadyClockedIn) {
            System.out.println("You're not clocked in yet!");
            prompt();
        } else
            parseListCommand(command);

    }


    private static void parseListCommand(String command) throws IOException {
        boolean alreadyClockedIn = currentSession != null;

        if (command.equals("list") && alreadyClockedIn) {
            System.out.println("Clock out before viewing past sessions.");
            prompt();
        } else if (command.equals("list") && !alreadyClockedIn) {
            Sessions.printLastThreeSessions();
            prompt();
        } else
            parseAdjustCommand(command);


    }

    private static void parseAdjustCommand(String command) throws IOException {
        boolean alreadyClockedIn = currentSession != null;

        if (command.equals("adjust") && alreadyClockedIn) {
            System.out.println("Clock out before adjusting past sessions");
            prompt();
        } else if (command.equals("adjust") && !alreadyClockedIn)
            promptAtAdjust();
    }

    private static void promptAtAdjust() {
        Scanner scanner = new Scanner(System.in);

        Session sessionToAdjust = null;

        while (sessionToAdjust == null) {
            try {
                System.out.print("What session? (enter session ID#): ");
                sessionToAdjust = Sessions.get(parseInt(scanner.nextLine()));
                promptAdjustParameter(sessionToAdjust);
            } catch (IllegalArgumentException e) {
                System.out.println("\"There is no session with that ID!");
            }
        }

    }

    private static void promptAdjustParameter(Session sessionToAdjust) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which parameter (\"in\", \"out\", \"focus\", or \"relevance\"): ");

        String parameterToAdjust = scanner.nextLine();

        if (parameterToAdjust.equals("in")) {
            promptAdjustClockIn(sessionToAdjust);
        } else if (parameterToAdjust.equals("out")) {
            promptAdjustClockOut(sessionToAdjust);
        } else if (parameterToAdjust.equals("focus")) {
            promptAdjustFocus(sessionToAdjust);
        } else if (parameterToAdjust.equals("relevance")) {
            promptAdjustRelevance(sessionToAdjust);
        } else
            System.out.println("That's not a valid parameter name. (\"in\", \"out\", \"focus\", or \"relevance\"): ");


    }

    private static void promptAdjustClockIn(Session sessionToAdjust) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What new value(example: 08:20:45)?: ");

        LocalTime newClockIn = null;

        while (newClockIn == null) {
            try {
                String newClockInBeforeParse = scanner.nextLine() + ".000000000";
                newClockIn = LocalTime.parse(newClockInBeforeParse);
                sessionToAdjust.setClockIn(newClockIn);
                prompt();
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Invalid! (example: 08:20:45)");
            }
        }
    }


    private static void promptAdjustClockOut(Session sessionToAdjust) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What new value(example: 08:20:45)?: ");

        LocalTime newClockOut = null;

        while (newClockOut == null) {
            try {
                String newClockOutBeforeParse = scanner.nextLine() + ".000000000";
                newClockOut = LocalTime.parse(newClockOutBeforeParse);
                sessionToAdjust.setClockOut(newClockOut);
                prompt();
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Invalid! (example: 08:20:45)");
            }
        }
    }

    private static void promptAdjustFocus(Session sessionToAdjust) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What new value(1-10)?: ");

        int newFocusLevel = 0;

        while (newFocusLevel == 0) {
            try {
                newFocusLevel = validateLevel(Integer.parseInt(scanner.nextLine()));
                sessionToAdjust.setFocusLevel(newFocusLevel);
                prompt();
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Invalid! (example: 08:20:45)");
            }
        }

    }

    private static void promptAdjustRelevance(Session sessionToAdjust) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What new value(1-10)?: ");

        int newRelevanceLevel = 0;

        while (newRelevanceLevel == 0) {
            try {
                newRelevanceLevel = validateLevel(Integer.parseInt(scanner.nextLine()));
                sessionToAdjust.setRelevanceLevel(newRelevanceLevel);
                prompt();
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Invalid! (example: 08:20:45)");
            }
        }
    }

    private static void promptAtClockOut() {
        Scanner scanner = new Scanner(System.in);

        while (currentSession.getFocusLevel() == 0) {
            try {
                System.out.print("Set focus level (1-10):");
                int focusLevel = validateLevel(parseInt(scanner.nextLine()));
                currentSession.setFocusLevel(focusLevel);
            } catch (IllegalArgumentException e) {
                System.out.println("That's not valid. Type a number from 1 to 10.");
            }
        }

        while (currentSession != null && currentSession.getRelevanceLevel() == 0) {
            try {
                System.out.print("Set relevance level (1-10):");
                int relevanceLevel = validateLevel(parseInt(scanner.nextLine()));
                currentSession.setRelevanceLevel(relevanceLevel);
                Sessions.add(currentSession);
                currentSession = null;
                prompt();
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("That's not valid. Type a number from 1 to 10.");
            }
        }

    }

    public static int validateLevel(int levelToCheck){
        if (levelToCheck > 0 && levelToCheck > 10) throw new IllegalArgumentException();
        else return levelToCheck;
    }

//TODO remove I/O exception from signatures where possible
}

