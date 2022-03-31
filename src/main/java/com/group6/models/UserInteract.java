package com.group6.models;

import java.util.Scanner;

/**
 * The UserInteract class process inputs from the console. it ensures the application does not crash when incorrect
 * inputs are made.
 * @author Yusuf Dauda
 * @author Aldana
 * @version 1.0
 */
public class UserInteract {

    /**
     * The bookAtable() method is used to add bookings to the database
     * It collects the required data for a booking object.
     * @return a booking object
     */
    static Booking bookAtable() {
        int dateI = enterInteger("Date(format DDMM): ");
        int timeI = enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
        int guests = enterInteger("Number of guests(max 10): ");
        String date = Integer.toString(dateI);
        String time = Integer.toString(timeI);
        return new Booking(guests, date, time);
    }

    /**
     * the enterInteger() method takes a string as argument. It displays this string
     * to the console as a message and requires the user to enter an integer. the
     * input is checked to ensure that the correct data type is entered into the
     * console. A loop will continue to run until the correct input is registered.
     * @param message A string which will be displayed to the console.
     * @return an integer input by the user.
     */
    static int enterInteger(String message) {
        Scanner in = new Scanner(System.in);
        System.out.printf(message);
        while (!in.hasNextInt()) {
            System.out.printf("Please enter a number and ensure " + "it is less than %d: ", Integer.MAX_VALUE);
            in.nextLine();
        }
        int selection = in.nextInt();
        System.out.println();
        return selection;
    }

    /**
     * the enterString() method takes a string as argument. It displays this string
     * to the console as a message and requires the user to enter a String. the
     * input is checked to ensure that the correct data type is entered into the
     * console. A loop will continue to run until the correct input is registered.
     * @param message A string which will be displayed to the console.
     * @return a string input by the user.
     */
    public static String enterString(String message) {
        Scanner in = new Scanner(System.in);
        System.out.printf(message);
        while (!in.hasNext()) {
            System.out.println("Please enter a valid name: ");
            in.nextLine();
        }
        String selection = in.nextLine().toLowerCase().trim();
        //System.out.println();
        return selection;
    }

    /**
     * The optionRange() method returns a boolean. it checks to see if a given
     * value(reference) is within a set range (start, end).
     * @param reference the input from the user to be checked.
     * @param start the accepted lower bound.
     * @param end the accepted upper bound.
     * @return true if the reference is within the given range, false otherwise.
     */
    public static boolean optionRange(int reference, int start, int end) {
        boolean range = reference >= start && reference <= end;
        return range;
    }

    /**
     * The addCustomer() method takes no argument, it asks the user to
     * enter the first and last name. it then
     * uses these inputs to create a Customer object.
     * @return the new customer object.
     */
    public static Customer addCustomer() {
        String firstName = enterString("First Name: ");
        String lastName = enterString("Last Name: ");
        int id = 0;
        return new Customer(firstName, lastName, id);

    }

    /**
     * The addStaff() method takes no argument, it asks the user to
     * enter the first and last name. it then
     * uses these inputs to create a Customer object.
     * @return the new staff object.
     */
    public static Staff addStaff() {
        String firstName = enterString("First Name: ");
        String lastName = enterString("Last Name: ");
        String type = enterString("Enter Type: ");
        if (type.equalsIgnoreCase("waiter")) {
            Waiter waiter = new Waiter(firstName, lastName, 0);
            return waiter;
        }
        if (type.equalsIgnoreCase("driver")) {
            Driver driver = new Driver(firstName, lastName,0);
            return driver;
        }
        if (type.equalsIgnoreCase("chef")) {
            Chef chef = new Chef(firstName, lastName,0);
            return chef;
        }
        return new Staff();
    }
}
