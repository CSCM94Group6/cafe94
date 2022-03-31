package com.group6.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The Methods and Storage class contains the database for staff, customer, orders, and bookings. it also has the
 * database for the menu. in this class, all objects access the database, process data, and interact with one another.
 * @author Yusuf Dauda
 * @author Meg Symons
 * **/
public class MethodsAndStorage {
    // Questions and options to be displayed to the user.
    private static final String YES_NO = "Add another? 1 = yes, 2 = no: ";
    private static final String ERROR = "Invalid input, please read the instructions carefully and try again: ";
    private static final String SELECT_ITEMS = "Select your items by selecting the id,"
            + "for multiple items, separate id by commas: ";
    private static final String WAITER_ACTIONS = "Choose an option below:%n" +
            "1. place order%n" +
            "2. view my hours%n" +
            "3. approve bookings%n" +
            "4. approve deliveries%n-> ";
    private static final String DRIVER_ACTIONS = "Choose an option below:%n" +
            "1. view approved deliveries%n" +
            "2. view my hours%n-> ";
    private static final String CHEF_ACTIONS = "Choose an option below:%n" +
            "1. view pending orders%n" +
            "2. view my hours%n" +
            "3. mark orders as ready%n-> ";

    //placeholders for options selected by the user.
    private static final int placeOrder = 1;
    private static final int viewApprovedDeliveries = 1;
    private static final int viewPendingOrders = 1;
    private static final int viewHours = 2;
    private static final int approveBookings = 3;
    private final static int orderReady = 3;
    private static final int approveDelivery = 4;

    //database for staff, customer, and orders.
    private static final HashMap<Integer, Customer> CUSTOMERS = new HashMap<>();
    private static final HashMap<Integer, Staff> STAFF = new HashMap<>();
    private static final ArrayList<Order> ORDERS = new ArrayList<Order>();

    //database for menu
    private static final Menu ITEM_1 = new Menu("Rice", 5.50, 1);
    private static final Menu ITEM_2 = new Menu("Machboos", 6.50, 2);
    private static final Menu ITEM_3 = new Menu("Suki", 5.75, 3);
    private static final Menu ITEM_4 = new Menu("Steak", 8.50, 4);
    private static final Menu ITEM_5 = new Menu("Saksuka", 5.00, 5);
    private static final ArrayList<Menu> MAIN_MENU = new ArrayList<Menu>(Arrays.asList(
            ITEM_1, ITEM_2, ITEM_3, ITEM_4, ITEM_5));

    //database for tables
    private static final Table TABLE_OF_TWO = new Table(2,2);
    private static final Table TABLE_OF_FOUR = new Table(4,4);
    private static final Table TABLE_OF_EIGHT = new Table(8,8);
    private static final Table TABLE_OF_TEN = new Table(10,10);
    private static final ArrayList<Table> TABLES = new ArrayList<>(Arrays.asList(
            TABLE_OF_TWO, TABLE_OF_FOUR, TABLE_OF_EIGHT, TABLE_OF_TEN));

    //id for customer and staff inputs for console, and key for database entry
    private static int key = 1;
    private static int customerId = 0;
    private static int staffId = 0;

    /**
     * The <code>addCustomer()</code> method takes no arguments. It adds customer objects to the database, and
     * automatically assigns IDs to the customers. customer Id is even.
     */
    public static int addCustomer(String firstname, String lastname){
            Customer customer = new Customer(firstname, lastname, 0);
            while(key % 2 != 0) key++;
            CUSTOMERS.put(key, customer);
            CUSTOMERS.get(key).setId(key);
            int id = key;
            key++;
            return id;
    }

    /**
     * @return a collection of all customers in the database.
     */
    public static HashMap<Integer, Customer> showCustomers(){
        return CUSTOMERS;
    }

    /**
     * The <code>makeBooking()</code> method takes no arguments. It adds booking objects to the database only if
     * the date and time requested for a particular number of guests is available.
     */
    public static String makeBooking(String date, String time, int guests){
            String message = "Booking Complete";
            Booking booking = new Booking(guests, date, time);
            String key1 = booking.getDate() + booking.getTime() + "a";
            String key2 = booking.getDate() + booking.getTime() + "b";
            String key3 = booking.getDate() + booking.getTime() + "c";
            String key4 = booking.getDate() + booking.getTime() + "d";
            boolean contains2 = TABLE_OF_TWO.getBookings().containsKey(key1)
                    && TABLE_OF_TWO.getBookings().containsKey(key2)
                    && TABLE_OF_TWO.getBookings().containsKey(key3)
                    && TABLE_OF_TWO.getBookings().containsKey(key4);
            boolean contains4 = TABLE_OF_FOUR.getBookings().containsKey(key1)
                    && TABLE_OF_FOUR.getBookings().containsKey(key2)
                    && TABLE_OF_FOUR.getBookings().containsKey(key3)
                    && TABLE_OF_FOUR.getBookings().containsKey(key4);
            boolean contains8 = TABLE_OF_EIGHT.getBookings().containsKey(key1)
                    && TABLE_OF_EIGHT.getBookings().containsKey(key2);
            boolean contains10 = TABLE_OF_EIGHT.getBookings().containsKey(key1);
            if (booking.getNumOfGuests() < 3){
                if(TABLE_OF_TWO.isAvailable() && !contains2) TABLE_OF_TWO.addBooking(booking);
                else if (TABLE_OF_FOUR.isAvailable() && !contains4) TABLE_OF_FOUR.addBooking(booking);
                else{
                    if (contains2) {
                        message = "The time chosen for this day and number of guests is unavailable";
                    }
                }
            }
            else if (booking .getNumOfGuests() > 2
                    && booking.getNumOfGuests() < 5) {
                if(TABLE_OF_FOUR.isAvailable() && !contains4) TABLE_OF_FOUR.addBooking(booking);
                else if(TABLE_OF_EIGHT.isAvailable() && !contains8) TABLE_OF_EIGHT.addBooking(booking);
                else{
                    if (contains4) {
                        message = "The time chosen for this day and number of guests is unavailable";
                    }
                }
            }
            else if (booking.getNumOfGuests() > 4
                    && booking.getNumOfGuests() < 9){
                if(TABLE_OF_EIGHT.isAvailable() && !contains8) TABLE_OF_EIGHT.addBooking(booking);
                else if(TABLE_OF_TEN.isAvailable() && !contains10) TABLE_OF_TEN.addBooking(booking);
                else{
                    if (contains8) {
                        message = "The time chosen for this day and number of guests is unavailable";
                    }
                }
            }
            else if (booking.getNumOfGuests() == 9
                    || booking.getNumOfGuests() == 10){
                if (contains10) {
                    message = "The time chosen for this day and number of guests is unavailable";
                }
            } else {
                message = String.format("Sorry, no tables for %d guests, please reduce the number%n.",
                        booking.getNumOfGuests());
            }
            //we add the customer id to the booking here
            booking.setCustId(customerId);
        return message;
    }

    /**
     * @return a collection of all Tables in the database.
     */
    public static ArrayList<Table> showBookings(){
        return TABLES;
    }

    /**
     * The <code>placeOrder()</code> method takes one argument. It adds Order objects to the database as required,
     * and indicate if the orders are approved or not.
     * @param userId indicates who is placing the order. the user id determines what options would be made available
     *               to the user down the line.
     */
    public static String placeOrder(int userId, String selection, String type, boolean approved){
            Order order = new Order(userId);
            //allow the user select multiple items from the menu,
            //add all the items selected to the users order
            String[] items = selection.split(",");
            int count = 0;
            while(count < items.length) {
                order.addToOrder(MAIN_MENU.get(Integer.parseInt(items[count])));
                count++;
            }
            //have the user select take-away or delivery
            order.setType(type);
            order.setApproved(approved);
            ORDERS.add(order);
            return "Order Placed";

    }

    /**
     * @return a collection of all Orders in the database.
     */
    public static ArrayList<Order> showOrders(){
        return ORDERS;
    }

    public static String showMenu(){
        StringBuilder foodItems = new StringBuilder();
        //Show the user all items on the menu
        int count = 0;
        while (count < MAIN_MENU.size()) {
            foodItems.append(count).append(". ").append(MAIN_MENU.get(count));
            count ++;
        }
        return String.valueOf(foodItems);
    }

    /**
     * @return a collection of all Staff in the database.
     */
    public static HashMap<Integer, Staff> showStaff(){
        return STAFF;
    }

    /**
     * The <code>addCStaff()</code> method takes no arguments. It adds Staff objects to the database, and
     * automatically assigns IDs to the customers. Staff Id is odd.
     */
    public static int addStaff(String fname, String lname, String type){
        int id = 0;
        if (type.equalsIgnoreCase("waiter")) {
            Waiter waiter = new Waiter(fname, lname, 0);
            while(key % 2 != 1) key++;
            STAFF.put(key, waiter);
            STAFF.get(key).setId(key);
            id = key;
            key++;
        }
        else if (type.equalsIgnoreCase("driver")) {
            Driver driver = new Driver(fname, lname,0);
            while(key % 2 != 1) key++;
            STAFF.put(key, driver);
            STAFF.get(key).setId(key);
            id = key;
            key++;
        }
        else if (type.equalsIgnoreCase("chef")) {
            Chef chef = new Chef(fname, lname,0);
            while(key % 2 != 1) key++;
            STAFF.put(key, chef);
            STAFF.get(key).setId(key);
            id = key;
            key++;
        }
        return id;
    }

    /**
     * The <code>removeStaff()</code> method takes one argument. It deletes the staff with the id from the database.
     * @param StaffId the id of the staff to be removed.
     * @return the staff that has been removed.
     */
    public static String removeStaff(int StaffId){
        return String.valueOf(STAFF.remove(staffId));
    }

    /**
     * provides an interface in the console to interact with user when user is a waiter.
     * @param id staff id.
     */
    public static String waiterStuff(int id, String selection, int waiterChoice){
        String message = "";
        if (waiterChoice == placeOrder) return placeOrder(id, selection, "Eat-in", true);
        else if (waiterChoice == viewHours) return message = String.format("you have %dhrs remaining.%n%n ",
                STAFF.get(id).hoursRemaining());
        else if (waiterChoice == approveBookings) {
            StringBuilder del = new StringBuilder();
            for (Table t: showBookings()){
                for (Booking b: t.getBookings().values()){
                    if(!b.isApproved()) del.append(t);
                }
            }
            return String.valueOf(del);
        }
        else if (waiterChoice == approveDelivery){
            StringBuilder del = new StringBuilder();
            for (Order o: ORDERS){
                if (o.getType().equalsIgnoreCase("delivery") && !o.isApproved()){
                    del.append(o);
                }
            }
            return String.valueOf(del);
        }
        else if (waiterChoice == 5) {
            STAFF.get(id).approveDelivery();
            StringBuilder del = new StringBuilder();
            for (Order o: ORDERS){
                if (o.getType().equalsIgnoreCase("delivery") && o.isApproved()){
                    del.append(o);
                }
            }
            return String.valueOf(del);
        }
        else if (waiterChoice == 6) {
            STAFF.get(id).approveBooking();
            StringBuilder del = new StringBuilder();
            for (Table t: showBookings()){
                for (Booking b: t.getBookings().values()){
                    if (b.isApproved()){
                        del.append(b);
                    }
                }
            }
            return String.valueOf(del);
        }

        return message;
    }

    /**
     * provides an interface in the console to interact with user when user is a driver.
     * @param id staff id.
     */
    public static String driverStuff(int id, int driverChoice){
        if (driverChoice == viewApprovedDeliveries) {
            StringBuilder del = new StringBuilder();
            for (Order o: showOrders()){
                if (o.isApproved() && o.getType().equalsIgnoreCase("delivery")){
                    del.append(o);
                }
            }
            return String.valueOf(del);
        }
        else if (driverChoice == viewHours) return String.format("you have %dhrs remaining.%n%n ",
                STAFF.get(id).hoursRemaining());
        return "";
    }

    /**
     * provides an interface in the console to interact with user when user is a chef.
     * @param id staff id.
     */
    public static String chefStuff(int id, int chefChoice, String approve){
        String message = "";
        StringBuilder collection = new StringBuilder();
        //System.out.printf("Hello %S, where do we start?%n", STAFF.get(id).getFirstName());
        if (chefChoice == viewPendingOrders){
            for (Order o: showOrders()){
                if (!o.isReady()) collection.append(o);
            }
            return message = collection.toString();
        }
        else if (chefChoice == viewHours) return message = String.format("you have %dhrs remaining.%n%n ",
                STAFF.get(id).hoursRemaining());
        else if (chefChoice == orderReady){
            collection.append(String.format("Pending orders:%n"));
            for (Order o: showOrders()){
                if (!o.isReady()){
                    collection.append(showOrders().indexOf(o)).append(". ").append(o);
                }
            }
            return message = collection.toString();
        }
        else if (chefChoice == 4) {
            String[] items = approve.split(",");
            int count = 0;
            while(count < items.length){
                if(Integer.parseInt(items[count]) < ORDERS.size()
                        && Integer.parseInt(items[count]) >= 0){
                    ORDERS.get(Integer.parseInt(items[count])).setReady(true);
                    count++;
                    if (count == items.length) message = "Orders marked as ready";
                } else {
                    message = String.format("Order number %s does not exist in the database%n", items[count]);
                    count = items.length;
                }
            }
        }
        return message;
    }

}
