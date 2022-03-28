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
    public static void addCustomer(){
        boolean finished = false;
        do {
            System.out.println("lets get you registered");
            while(key % 2 != 0) key++;
            CUSTOMERS.put(key, UserInteract.addCustomer());
            CUSTOMERS.get(key).setId(key);
            key++;
            int customerOption = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(customerOption,1,2)) {
                customerOption = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(customerOption,1,2);
            }
            if (customerOption == 2) finished = true;
        } while(!finished);
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
    public static void makeBooking(){
        boolean finBooking = false;
        do {
            Booking booking = UserInteract.bookAtable();
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
                    while (contains2) {
                        System.out.println("Please select another day or time:");
                        int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
                        int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
                        booking.setDate(Integer.toString(dateI));
                        booking.setTime(Integer.toString(timeI));
                        key1 = booking.getDate() + booking.getTime() + "a";
                        key2 = booking.getDate() + booking.getTime() + "b";
                        key3 = booking.getDate() + booking.getTime() + "c";
                        key4 = booking.getDate() + booking.getTime() + "d";
                        contains2 = TABLE_OF_TWO.getBookings().containsKey(key1)
                                && TABLE_OF_TWO.getBookings().containsKey(key2)
                                && TABLE_OF_TWO.getBookings().containsKey(key3)
                                && TABLE_OF_TWO.getBookings().containsKey(key4);
                    }
                    TABLE_OF_TWO.addBooking(booking);
                }
            }
            else if (booking .getNumOfGuests() > 2
                    && booking.getNumOfGuests() < 5) {
                if(TABLE_OF_FOUR.isAvailable() && !contains4) TABLE_OF_FOUR.addBooking(booking);
                else if(TABLE_OF_EIGHT.isAvailable() && !contains8) TABLE_OF_EIGHT.addBooking(booking);
                else{
                    while (contains4) {
                        System.out.println("Please select another day or time:");
                        int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
                        int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
                        booking.setDate(Integer.toString(dateI));
                        booking.setTime(Integer.toString(timeI));
                        key1 = booking.getDate() + booking.getTime() + "a";
                        key2 = booking.getDate() + booking.getTime() + "b";
                        key3 = booking.getDate() + booking.getTime() + "c";
                        key4 = booking.getDate() + booking.getTime() + "d";
                        contains4 = TABLE_OF_FOUR.getBookings().containsKey(key1)
                                && TABLE_OF_FOUR.getBookings().containsKey(key2)
                                && TABLE_OF_FOUR.getBookings().containsKey(key3)
                                && TABLE_OF_FOUR.getBookings().containsKey(key4);
                    }
                    TABLE_OF_FOUR.addBooking(booking);
                }
            }
            else if (booking.getNumOfGuests() > 4
                    && booking.getNumOfGuests() < 9){
                if(TABLE_OF_EIGHT.isAvailable() && !contains8) TABLE_OF_EIGHT.addBooking(booking);
                else if(TABLE_OF_TEN.isAvailable() && !contains10) TABLE_OF_TEN.addBooking(booking);
                else{
                    while (contains8) {
                        System.out.println("Please select another day or time:");
                        int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
                        int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
                        booking.setDate(Integer.toString(dateI));
                        booking.setTime(Integer.toString(timeI));
                        key1 = booking.getDate() + booking.getTime() + "a";
                        key2 = booking.getDate() + booking.getTime() + "b";
                        contains8 = TABLE_OF_EIGHT.getBookings().containsKey(key1)
                                && TABLE_OF_EIGHT.getBookings().containsKey(key2);

                    }
                    TABLE_OF_EIGHT.addBooking(booking);
                }
            }
            else if (booking.getNumOfGuests() == 9
                    || booking.getNumOfGuests() == 10){
                while (contains10) {
                    System.out.println("Please select another day or time:");
                    int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
                    int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
                    booking.setDate(Integer.toString(dateI));
                    booking.setTime(Integer.toString(timeI));
                    key1 = booking.getDate() + booking.getTime() + "a";
                    contains10 = TABLE_OF_TEN.getBookings().containsKey(key1);
                }
                TABLE_OF_TEN.addBooking(booking);
            } else {
                System.out.format("Sorry, no tables for %d guests, please reduce the number%n.",
                        booking.getNumOfGuests());
                finBooking = true;
                //continue;
            }
            //we add the customer id to the booking here
            booking.setCustId(customerId);
            //option to book more tables as required
            int moreBookings = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(moreBookings,1,2)) {
                moreBookings = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(moreBookings,1,2);
            }
            if (moreBookings == 2) {
                finBooking = true;
                //finished = true;
            }
        }while(!finBooking);
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
    public static void placeOrder(int userId){
        boolean finOrder;
        do {
            Order order = new Order(userId);
            //Show the user all items on the menu
            int count = 0;
            System.out.println("Our menu Items: ");
            while (count < MAIN_MENU.size()) {
                System.out.println(count);
                System.out.print(MAIN_MENU.get(count));
                count ++;
            }
            //allow the user select multiple items from the menu,
            //add all the items selected to the users order
            String selection = UserInteract.enterString(SELECT_ITEMS);
            String[] items = selection.split(",");
            count = 0;
            while(count < items.length) {
                order.addToOrder(MAIN_MENU.get(Integer.parseInt(items[count])));
                count++;
            }
            //have the user select take-away or delivery
            if (userId % 2 == 0){
                int taOrDel = UserInteract.enterInteger("1 for Takeaway or 2 for delivery?: ");
                while (!UserInteract.optionRange(taOrDel,1,2)) {
                    taOrDel = UserInteract.enterInteger(ERROR);
                    UserInteract.optionRange(taOrDel,1,2);
                }
                if (taOrDel == 1) order.setType("Takeaway");
                else {
                    order.setType("Delivery");
                    order.setApproved(false);
                }
            }
            //add the order to the database
            ORDERS.add(order);
            System.out.println();
            finOrder = true;
            //finished = true;
        } while(!finOrder);
    }

    /**
     * @return a collection of all Orders in the database.
     */
    public static ArrayList<Order> showOrders(){
        return ORDERS;
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
    public static void addStaff(){
        boolean finished = false;
        do {
            System.out.println("lets get you registered");
            while(key % 2 != 1) key++;
            STAFF.put(key, UserInteract.addStaff());
            STAFF.get(key).setId(key);
            key++;
            int staffOption = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(staffOption,1,2)) {
                staffOption = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(staffOption,1,2);
            }
            if (staffOption == 2) finished = true;
        } while(!finished);
    }

    /**
     * The <code>removeStaff()</code> method takes one argument. It deletes the staff with the id from the database.
     * @param StaffId the id of the staff to be removed.
     * @return the staff that has been removed.
     */
    public static Staff removeStaff(int StaffId){
        Staff staff = new Staff();
        if (STAFF.containsKey(staffId)) {
            staff = STAFF.remove(staffId);
        }
        return staff;
    }

    /**
     * provides an interface in the console to interact with user when user is a waiter.
     * @param id staff id.
     */
    public static void waiterStuff(int id){
        System.out.printf("Hello %S, where do we start?%n", STAFF.get(id).getFirstName());
        int waiterChoice = UserInteract.enterInteger(WAITER_ACTIONS);
        while (!UserInteract.optionRange(waiterChoice, 1, 4)){
            waiterChoice = UserInteract.enterInteger(ERROR);
            UserInteract.optionRange(waiterChoice, 1, 4);
        }
        if (waiterChoice == placeOrder) placeOrder(id);
        if (waiterChoice == viewHours) System.out.printf("you have %dhrs remaining.%n%n ", STAFF.get(id).hoursRemaining());
        if (waiterChoice == approveBookings) {
            for (Table t: showBookings()) System.out.println(t);
            int yesOrN0 = UserInteract.enterInteger("Would you like to approve these bookings:%n" +
                    "Enter 1. yes, or 2. no: ");
            while(!UserInteract.optionRange(yesOrN0, 1, 2)){
                yesOrN0 = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(yesOrN0,1,2);
            }
            if(yesOrN0 == 1) STAFF.get(id).approveBooking();
        }
        if (waiterChoice == approveDelivery){
            for (Order o: ORDERS){
                if (o.getType().equalsIgnoreCase("delivery")){
                    System.out.println(o);
                }
            }
            int yesOrN0 = UserInteract.enterInteger("Would you like to approve these deliveries:%n" +
                    "Enter 1. yes, or 2. no: ");
            while(!UserInteract.optionRange(yesOrN0, 1, 2)){
                yesOrN0 = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(yesOrN0,1,2);
            }
            if(yesOrN0 == 1) STAFF.get(id).approveDelivery();
        }
    }

    /**
     * provides an interface in the console to interact with user when user is a driver.
     * @param id staff id.
     */
    public static void driverStuff(int id){
        System.out.printf("Hello %S, where do we start?%n", STAFF.get(id).getFirstName());
        int driverChoice = UserInteract.enterInteger(DRIVER_ACTIONS);
        while (!UserInteract.optionRange(driverChoice, 1, 2)){
            driverChoice = UserInteract.enterInteger(ERROR);
            UserInteract.optionRange(driverChoice, 1, 2);
        }
        if (driverChoice == viewApprovedDeliveries) {
            for (Order o: showOrders()){
                if (o.isApproved()){
                    System.out.println(o);
                }
            }
        }
        if (driverChoice == viewHours) System.out.printf("you have %dhrs remaining.%n%n ",
                STAFF.get(id).hoursRemaining());
    }

    /**
     * provides an interface in the console to interact with user when user is a chef.
     * @param id staff id.
     */
    public static void chefStuff(int id){
        System.out.printf("Hello %S, where do we start?%n", STAFF.get(id).getFirstName());
        int chefChoice = UserInteract.enterInteger(CHEF_ACTIONS);
        while (!UserInteract.optionRange(chefChoice, 1, 3)){
            chefChoice = UserInteract.enterInteger(ERROR);
            UserInteract.optionRange(chefChoice, 1, 3);
        }
        if (chefChoice == viewPendingOrders){
            for (Order o: showOrders()){
                if (!o.isReady()) System.out.println(o);
            }
        }
        if (chefChoice == viewHours) System.out.printf("you have %dhrs remaining.%n%n ",
                STAFF.get(id).hoursRemaining());
        if (chefChoice == orderReady){
            System.out.println("Pending orders: ");
            for (Order o: showOrders()){
                if (!o.isReady()){
                    System.out.println(showOrders().indexOf(o) + ". ");
                    System.out.println(o);
                    System.out.println();
                }
            }
            String approve = UserInteract.enterString("Select the number corresponding " +
                    "to the order you wish to approve, separate multiple orders by a comma: ");
            String[] items = approve.split(",");
            int count = 0;
            while(count < items.length){
                if(Integer.parseInt(items[count]) < ORDERS.size()
                        && Integer.parseInt(items[count]) >= 0){
                    ORDERS.get(Integer.parseInt(items[count])).setReady(true);
                    count++;
                } else {
                    System.out.printf("Order number %s does not exist in the database%n", items[count]);
                    count = items.length;
                }
            }

        }

    }

}
