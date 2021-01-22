package com.tunepruner;

import java.time.LocalTime;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    private static Session currentSession;//TODO remember to set this to null when clocking out!
    //TODO handle a "cancel" command.


    public static void main(String[] args) {
        prompt();
    }

    private static void prompt() {
        Scanner initialScanner = new Scanner(System.in);

        System.out.print("Type a command (\"in\", \"out\", or \"adjust\"):");
        String initialCommand = initialScanner.nextLine();
        parseClockInCommand(initialCommand);
    }

    private static void parseClockInCommand(String command) {
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

    private static void parseClockOutCommand(String command) {
        boolean alreadyClockedIn = currentSession != null;

        if (command.equals("out") && alreadyClockedIn) {
            currentSession.clockOut();
            promptAtClockOut();
        } else if (command.equals("out") && !alreadyClockedIn) {
            System.out.println("You're not clocked in yet!");
            prompt();
        } else
            parseListCommand(command);

    }


    private static void parseListCommand(String command) {
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

    private static void parseAdjustCommand(String command) {
        boolean alreadyClockedIn = currentSession != null;

        if (command.equals("adjust") && alreadyClockedIn) {
            System.out.println("Clock out before adjusting past sessions");
            prompt();
        } else if (command.equals("adjust") && !alreadyClockedIn)
            promptAtAdjust();
        prompt();
    }

    private static void promptAtAdjust() {

    }


    private static void promptAtClockOut() {
        Scanner scanner = new Scanner(System.in);

        while (currentSession.getFocusLevel() == 0) {
            try {
                System.out.print("Set focus level (1-10):");
                currentSession.setFocusLevel(parseInt(scanner.nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println("That's not valid. Type a number from 1 to 10.");
            }
        }

        while (currentSession.getRelevanceLevel() == 0) {
            try {
                System.out.print("Set focus level (1-10):");
                currentSession.setRelevanceLevel(parseInt(scanner.nextLine()));
                Sessions.add(currentSession);
                currentSession = null;
                prompt();
            } catch (IllegalArgumentException e) {
                System.out.println("That's not valid. Type a number from 1 to 10.");
            }
        }

    }
}

